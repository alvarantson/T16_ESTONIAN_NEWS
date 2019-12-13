from multiprocessing import Process, Manager

from estnltk import Text
import numpy as np
import pandas as pd
import csv

from nltk.collocations import BigramCollocationFinder
from nltk import word_tokenize

data = pd.read_csv("../data/postimees.txt", sep="\t", names=['website', 'id', 'datetime', 'title', 'share_count',
                                                             'comment_count', 'read_count', 'author', 'section',
                                                             'content'])
data = data.drop_duplicates(subset=['id'])
data = data.drop(columns=['website', 'section', 'content'])

data['comment_count'] = pd.to_numeric(data['comment_count'],errors='coerce')
data['read_count'] = pd.to_numeric(data['read_count'],errors='coerce')
data['share_count'] = pd.to_numeric(data['share_count'],errors='coerce')

def findWords(d, i):
    mitmes = i
    for index, row in data[i:i + 17834].iterrows():
        if mitmes % 100 == 0:
            print(str(mitmes) + '. artikkel')
        finder = BigramCollocationFinder.from_words(word_tokenize(str(row.title)))
        for word, n in finder.ngram_fd.items():
            if word in d:
                d[word] = [d[word][0] + row.read_count, d[word][1] + 1]
            else:
                d[word] = [row.read_count, 1]
        mitmes += 1


if __name__ == '__main__':
    paral = 12

    manager = Manager()

    d = manager.dict()

    threads = []

    nr = 0

    for i in range(0, paral):
        p = Process(target=findWords, args=(d, nr))
        threads.append(p)
        p.start()
        nr += 17835

    for thread in threads:
        thread.join()

    print(d)
    w = csv.writer(open("../../data/bigram_read_count.csv", "w", encoding="utf-8"))
    for key, val in d.items():
        w.writerow([key, val[0], val[1]])