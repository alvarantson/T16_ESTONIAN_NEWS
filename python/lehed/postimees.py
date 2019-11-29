class Postimees:
    _SECTIONS = {81, 127, 296, 674, 517, 123, 2413, 3371, 2187, 3447, 124, 3127, 2325, 313, 855, 2125, 115,
                 80, 2993, 2901, 2349, 2429, 2329, 384, 246, 383, 382, 381, 380, 4776, 4777, 2375, 3013, 556}

    def __init__(self, state=None):
        if state:
            raise NotImplementedError()
        self.latest = {S: None for S in self._SECTIONS}

    async def search(self, conn):
        done = set()
        for section in self._SECTIONS - done:
            resp = await (await conn.get(f'https://services.postimees.ee/rest/v1/sections/{section}/articles?limit=100'
                                         + ("&end=" + self.latest[section] if self.latest[section] else ""))).json()
            if len(resp) < 100:
                done.add(section)
            for raw in resp:
                if not raw['isPremium']:
                    yield {
                        'page': 'postimees',
                        'id': raw['id'],
                        'published': raw['datePublished'],
                        'headline': raw['headline'],
                        'facebook_engagement': raw['meta']['facebook']['engagement']
                        if 'meta' in raw and 'facebook' in raw['meta'] else None,
                        'comments_count': raw['meta']['commentCount'],
                        'read_count': raw['meta']['readCount'],
                        'authors': {author['name'] for author in raw['authors']},
                        'sections': {section['id'] for section in raw['sections']},
                        'article': f"https://postimees.ee/{raw['id']}"
                    }

            self.latest[section] = raw['datePublished']

    @staticmethod
    def parse_html(soup):
        return ' '.join(item.p.text for item in soup.find_all(class_='article-body__item--htmlElement') if item and item.p and item.p.text)
