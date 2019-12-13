from multiprocessing import Process, Manager

from estnltk import Text
import numpy as np
import pandas as pd
import csv

data = pd.read_csv("../../data/postimees.txt", sep="\t", names=['website', 'id', 'datetime', 'title', 'share_count',
                                                             'comment_count', 'read_count', 'author', 'section',
                                                             'content'])

data = data.drop_duplicates(subset=['id'])
data = data.drop(columns=['website', 'section', 'content'])

#array = []

for i, row in data.iterrows():
    text = Text(str(row.title)).analyse('morphology')
    lemmas = text.morph_analysis.lemma
    new = ""
    for s in lemmas:
        new += str(s[0]) + " "
    print(str(i) + ": " + new)
    data.iloc[i, 'title'] = new


#print(array)

data.to_csv('../../data/postimees_lemma_title.txt',  sep="\t")
