#!/usr/bin/env python
# -*- coding: utf-8 -*-
import math


# Class này mô tả 1 vector của văn bản
class VectorDoc:
    # similarity # Độ tương đồng với vector cần phân loại
    def __init__(self, lable, listWords, hashTfIdf):
        self.lable = lable
        self.listWords = listWords
        self.hashTfIdf = hashTfIdf
        self.similarity = 0;

    # Tính độ tương đồng
    def caculateSimilarity(self, vector, hash):
        # Dùng hashmap
        denominator1 = 0
        denominator2 = 0
        numerator = 0
        for i in range(len(self.listWords)):
            try:
                tfIdfSelf = float(self.hashTfIdf.get(self.listWords[i]));
            except:
                tfIdfSelf = 0

            try:
                x = vector.hashTfIdf
                tfIdfVector =float(x.get(self.listWords[i]))
            except:
                tfIdfVector = 0

            numerator = numerator + tfIdfSelf * tfIdfVector
            denominator1 = denominator1 + math.pow(tfIdfSelf, 2)
            denominator2 = denominator2 + math.pow(tfIdfVector, 2)

        denominator = math.sqrt(denominator1) * math.sqrt(denominator2)

        if denominator == 0:
            self.similarity = 0;
        else:
            self.similarity = numerator / denominator;
        return self.similarity;

    def __getattribute__(self, name):
        return object.__getattribute__(self, name)
