#!/usr/bin/env python
# -*- coding: utf-8 -*-

import codecs
import os

from dominate.tags import h1

from vectordoc import VectorDoc
import subprocess

from os import listdir
from os.path import isfile, join

MARKS = ['Bạn_đọc',
        'Chuyện_lạ',
         'Diễn_đàn',
         'Đời_sống',
         'Giải_trí',
         'Giáo_dục',
         'Khoa_học',
         'Kinh_doanh',
         'Nhịp_sống_trẻ',
         'Pháp_luật',
         'Sức_khỏe',
         'Sức_mạnh_số',
         'Thế_giới',
         'Thể_thao',
         'Tình_yêu',
         'Văn_hóa',
         'Việc_làm',
         'Xã_hội',
         'Xe'
         ]

TOP_CHOOSE = 30;  # Chọn 100 kết quả tốt nhất, và tìm nhãn


# Load thư viện
def loadDictionary():
    words_vi = "words_vi.txt"
    with codecs.open(words_vi, "r", "utf-8") as f:
        list_words = f.readlines()
    return list_words


# Tách từ của 1 văn bản
def segmentWord(pathFile):
    os.system("sh shell-vitk.sh " + pathFile)


# Tạo vector cần phân loại
def createVectorClassify(hashIdfs, pathFile):

    p = subprocess.Popen(['sh', 'shell-vitk.sh', pathFile], stdout=subprocess.PIPE,
                         stderr=subprocess.PIPE)
    out, err = p.communicate()
    wordRaws = out.split()  # danh sách từ thô, đang có các từ lặp nhau
    listWords = []  # danh sách từ, bỏ từ trùnglặp
    tf = []
    tfIdf = []
    hashTfIdf = {}
    # Lặp từ lấy
    for i in range(len(wordRaws)):
        try:
            index = listWords.index(wordRaws[i])
        except:
            index = -1;

        if index != -1:  # Nếu  từ đã có trong list
            tf[index] = tf[index] + 1
        else:
            listWords.append(wordRaws[i])
            tf.append(1);

    for i in range(len(tf)):
        try:
            idf = float(hashIdfs.get(listWords[i]))
        except:
            idf = 0
        hashTfIdf[listWords[i]] = tf[i] * idf
    return VectorDoc(None, listWords, hashTfIdf)
    # Lặp tinh cho văn bản học



# Tạo tất cả vector trong tập học
def createVectorsFromTranning(hashIdfs):
    pathTf = '/home/thangld/PycharmProject/vietnam-news/module-learn/bag_word/tf.txt';

    fo = open(pathTf, "r")

    numberDoc = fo.readline()

    listVector = []  # danh sách các vector trong tập train


    # Đọc các dòng trong file này
    # Cứ 3 dòng thì là 1 vector
    while True:
        numberWords = fo.readline()
        if numberWords == '':
            break
        numberWords = int(numberWords);

        # Danh sách từ
        words = fo.readline()
        if words == '':
            break
        wordsSplit = words.split()
        listWords = wordsSplit[0:len(wordsSplit) - 1]
        label = wordsSplit[len(wordsSplit) - 1]

        tf = fo.readline().split()
        if tf == '':
            break

        hashTfIdf = {}


        # Lặp các từ và tính tfIdf
        for i in range(0, numberWords):
            try:
                idf = float(hashIdfs.get(listWords[i]))
            except:
                idf = 0

            hashTfIdf[listWords[i]] = idf * int(tf[i])
        listVector.append(VectorDoc(label, listWords, hashTfIdf))

    return listVector;


# Trả về nhãn cho văn bản

def classify(vectorClassify, listVector, hashIdfs):
    indexs = []

    for i in range(len(listVector)):
        listVector[i].caculateSimilarity(vectorClassify, hashIdfs);

    # Săp xêp theo do tuong dong giam gian

    listVector.sort(key=lambda x: x.similarity, reverse=True)
    for list in listVector:
        print list.similarity;

    counter  = [0]* len(MARKS)

    # Chọn label trong top
    for i in range(TOP_CHOOSE):
        label = listVector[i].lable;
        for i in range(len(MARKS)):
            if (label == MARKS[i]):
                counter[i] = counter[i] + 1;
                break;

    i =   counter.index(max(counter))
    marks = MARKS[i]
    print marks
    return marks;


# Chuyển nội dung file idf thành hashmap
def getHashMapIdf():
    pathIdf = "/home/thangld/PycharmProject/vietnam-news/module-learn/bag_word/idf.txt"
    # Đọc văn bản idf.
    idfContents = []
    with open(pathIdf, 'r') as f:
        for line in f:
            for word in line.split():
                idfContents.append(word)

    # Số lượng từ
    numberDic = int(idfContents[0])
    # Dùng hashmap cho văn bản tf-idf
    hashIdf = {}
    for i in range(0, numberDic):
        hashIdf[idfContents[i + 1]] = idfContents[int(numberDic) + i + 1]

    return hashIdf;


def main(pathFile):
    hashIdfs = getHashMapIdf();
    vectorClassify = createVectorClassify(hashIdfs, pathFile)
    listVectorTranning = createVectorsFromTranning(hashIdfs);
    return classify(vectorClassify, listVectorTranning, hashIdfs);


pathFile = 'article-clone/word_segment.txt'
main(pathFile);