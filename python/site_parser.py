from lehed import postimees
import time
import multiprocessing as mp
import bs4
import csv

class Parser(mp.Process):
    def __init__(self, queue, throughput):
        self.queue = queue
        self.throughput = throughput
        self.count = 0
        self.pages = {'postimees': postimees.Postimees}
        super().__init__()

    def run(self):
        while True:

            if not self.queue.empty():
                print('\rparsing', end='')
                r = self.queue.get()
                r['article'] = self.pages[r['page']].parse_html(bs4.BeautifulSoup(r['article'], 'lxml'))
                with open('../data/' +  r['page']+'.txt', 'a', encoding='utf8', newline='') as csvf:
                    csv.DictWriter(csvf, fieldnames=r.keys(), delimiter='\t').writerow(r)
                self.count += 1
                print(f'\r{self.count}: {r["published"]} - {r["headline"]}')