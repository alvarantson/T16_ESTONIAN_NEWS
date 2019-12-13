from multiprocessing import Process, Manager

from estnltk import Text
import numpy as np
import pandas as pd
import csv

data = pd.read_csv("../../data/postimees.txt", sep="\t", names=['website', 'id', 'datetime', 'title', 'share_count',
                                                             'comment_count', 'read_count', 'author', 'section',
                                                             'content'])
data = data.drop_duplicates(subset=['id'])

def findWords(d, i):
    mitmes = i
    for text in data.title[i:i + 17834]:
        if mitmes % 100 == 0:
            print(str(mitmes) + '. artikkel')
        text = Text(str(text)).analyse('morphology')
        words = text.morph_analysis.groupby(['lemma'])
        for s, n in words.count.items():
            word = s[0]
            if word in d:
                d[word] = d[word] + n
            else:
                d[word] = n
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
    w = csv.writer(open("../../data/title_word_amount_all.csv", "w", encoding="utf-8"))
    for key, val in d.items():
        w.writerow([key, val])

