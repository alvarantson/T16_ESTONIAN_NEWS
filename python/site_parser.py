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
            print(f'\r{self.count} artiklit', end='')
            if not self.queue.empty():
                r = self.queue.get()
                r['article'] = self.pages[r['page']].parse_html(bs4.BeautifulSoup(r['article'], 'lxml'))
                with open(r['page']+'.txt', 'a', encoding='utf8') as csvf:
                    csv.DictWriter(csvf, fieldnames=r.keys(), delimiter='\t').writerow(r)
                self.count += 1