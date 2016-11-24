#!/usr/local/bin/python
# coding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# libs
import json
import os
import re

# libs local
from web_simple import *
from libs_support import *
from labels_general import *
from database import *


class web(web_simple, label_general):
    """
        Doi tuong thao tac chinh voi 1 trang web 
    """

    # phuong thuc khoi tao
    def __init__(self, url, image_url, title, content, labels, date=None):

        # khoi tao cha
        web_simple.__init__(self, url, image_url, title, date)
        label_general.__init__(self, labels)

        # cai dat thuoc tinh
        self.content = content
        self.labels = labels

    def get_content(self):
        """ lay noi dung trang """
        return self.content

    def get_labels(self, level=-1):
        if level == 1:
            # lst_sub_label = self.get_domain() + "/unknow"
            regex = "([^/]+/[^/]+)"
            sublabel = re.search(regex, self.labels)
            if(sublabel != None):
                return sublabel.group(0)

        return self.labels

    def get_json(self):
        return json.dumps(self.__dict__, indent=4, separators=(',', ': '), ensure_ascii=False)

    def write_to_file(self, path):

        # Them ki tu / neu chua co
        if path[-1:] != '/':
            path += '/'
        # tao thu muc con: ten thu muc = nhan
        path += self.labels + '/'
        if not os.path.exists(path):
            os.makedirs(path)

        # tao ten file theo ham bam
        path_file = path + self.code + ".json"

        # Kiem tra xem file nay da dc tai ve hay chua
        if os.path.exists(path_file):
            return

        # ghi file
        file_temp = open(path_file, "w+")
        try:
            file_temp.write(self.get_json())
        except Exception as e:
            print (str(e))
            file_temp.close()
            os.remove(path_file)
            print self.title + "False\n\n"

    def insert_to_db(self, db):
        # Nomarlize before inserting to Database
        title = self.nomalizeString(self.title)
        labels = self.nomalizeString(self.labels)
        content = self.nomalizeString(self.content)
        image_url = self.nomalizeString(self.image_url)
        url = self.nomalizeString(self.url)
        code = self.nomalizeString(self.code)

        sql = "INSERT INTO `magazine`.`magazine` (`title`,`type`, `content`, `image`, `link`, `date`, `code`)  \
                VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');" % \
            (title, labels, content, image_url, url, ' ', code)
        # print('SQL : %s') %sql
        try:
            # Exucute SQL Command
            db.cursor.execute(sql)
            # Commit changes in database
            db.db.commit()
        except TypeError as e:
            print('---------------')
            print('Error when push to DB : %s') % sql
            print(e)
            print('---------------')
            # Rollback in case there is any error
            db.db.rollback()

    def isAlreadyExistInDatabase(self, db):
        labels = self.nomalizeString(self.labels)
        code = self.nomalizeString(self.code)

        sql = "SELECT `id` \
                FROM `magazine`.`magazine` \
                WHERE `type` = '%s' AND `code` = '%s' ;" % (labels, code)

        try:
            # Exucute SQL Command
            db.cursor.execute(sql)
            data = db.cursor.fetchall()
            if len(data) > 0:
                return True
            return False

        except TypeError as e:
            print '---------------'
            print 'Error when push to DB : %s' % sql
            print e
            print '---------------'
        return False

    def nomalizeString(self, str):
        # Remove all single quote
        str = str.replace("'", "")
        return str


#
#
#
#
#
#
#
#
# test class
if __name__ == '__main__':
    print('ffdfdfdf')
    x = web("http://ahihi.com/ngungu", "img", "tit", "content con ",
            "vnexpress/Du lịch", "123455")
    print (x.get_domain())
    print (x.get_labels())
    print (x.get_labels(1))
    print x.get_label_general()

    # db = database()
    # #x.insert_to_db(db)
    #
    # test = x.isAlreadyExistInDatabase(db)
    # if test:
    #     print('Already exists')
    # else:
    #     print('Not exists')
    #
    # #s = json.dumps(x.__dict__, indent=4, separators=(',', ':'))
    # # x.write_to_file('Data')
    # # print (x.get_json())
