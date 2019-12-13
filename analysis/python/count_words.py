from multiprocessing import Process, Manager

from estnltk import Text
import numpy as np
import pandas as pd
import csv

data = pd.read_csv("../../data/postimees.txt", sep="\t", names=['website', 'id', 'datetime', 'title', 'share_count',
                                                             'comment_count', 'read_count', 'author', 'section',
                                                             'content'])
data = data.drop_duplicates(subset=['id'])

data['idx'] = data.index


def count_words(row):
    print(row.idx)
    text = str(row.title).split(" ")
    return len(text)

def fun(chunk):
    data['word_count'] = chunk.apply(lambda i: count_words(i), axis=1)


if __name__ == '__main__':

    data['word_count'] = data.apply(lambda i: count_words(i), axis=1)

    #paral = 12

    #threads = []

    #nr = 0

    #for i in range(0, paral):
    #    p = Process(target=fun, args=(data[nr:nr+17834],))
    #    threads.append(p)
    #    p.start()
    #    nr += 17835

    #for thread in threads:
        #thread.join()

    w = csv.writer(open("../../data/word_count_title.csv", "w", encoding="utf-8"))
    for index, row in data.iterrows():
        w.writerow([row['id'], row['datetime'], row['title'], row['content'], row['share_count'],
                    row['comment_count'], row['read_count'], row['author'], row['word_count']])

