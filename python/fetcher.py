from lehed.postimees import Postimees
import multiprocessing as mp
from site_parser import Parser
import aiohttp
import asyncio
import time

class ConnectionWrapper:
    def __init__(self):
        self.session = aiohttp.ClientSession()

    async def close(self):
        await self.session.close()

    async def get(self, url):
        retry = 0
        while True:
            resp = await self.session.get(url)
            if resp.status == 200:
                return resp
            if retry > 4:
                return
            retry += 1

async def fetch_article(article, queue, conn):
    raw = await conn.get(article['article'])
    if not raw:
        return
    article['article'] = await raw.text()
    queue.put(article, False)



async def fetch_articles(site, queue, conn):
    async for article in site.search(conn):
        asyncio.create_task(fetch_article(article, queue, conn))
        if RATE_PER_SITE:
            await asyncio.sleep(1/RATE_PER_SITE)

if __name__ == '__main__':
    RATE_PER_SITE = 10  # ära pane 0
    PARSERS = 1

    mgr = mp.Manager()
    queue = mgr.Queue()
    throughputs = [mgr.Value('I', 0) for _ in range(PARSERS)]
    parsers = [Parser(queue, throughputs[i]) for i in range(PARSERS)]
    [parser.start() for parser in parsers]
    conn = ConnectionWrapper()
    sites = [Postimees()]

    loop = asyncio.get_event_loop()
    for site in sites:
        loop.create_task(fetch_articles(site, queue, conn))
    loop.run_forever()
