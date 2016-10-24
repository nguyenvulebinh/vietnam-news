# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#libs
import json
import os
import hashlib

#libs local
from web_simple import *
from libs_support import *
from database import *

class web(web_simple):
    
    # phuong thuc khoi tao
    def __init__(self, url, image_url, title, content, labels):
        # khoi tao cha 
        web_simple.__init__(self, url, image_url, title)
        # cai dat thuoc tinh
        self.content = content
        self.labels = labels

    # MANHTIEN ///////////
    # phuong thuc khoi tao
    def __init__(self, url, image_url, title, content, labels, date):
        # khoi tao cha 
        web_simple.__init__(self, url, image_url, title)
        # cai dat thuoc tinh
        self.content = content
        self.labels = labels
        self.date = date
        self.code = hashlib.sha1(self.url).hexdigest()
    # MANHTIEN ///////////

    def get_content(self):
        return self.content
    
    def get_labels(self):
        return self.labels
    
    def get_json(self):
        return json.dumps(self.__dict__, indent=4, separators=(',', ': '), ensure_ascii=False)

        
    def write_to_file(self, path):
        
        # Them ki tu / neu chua co
        if path[-1:] != '/':
            path += '/'
        # tao thu muc con: ten thu muc = nhan 
        path += self.labels+'/'
        if not os.path.exists(path):
            os.makedirs(path)
        
        # tao ten file theo ham bam
        path_file = path + hashlib.sha1(self.url).hexdigest()
        
        # Kiem tra xem file nay da dc tai ve hay chua 
        if os.path.exists(path_file):
            return 
            
        # ghi file
        file_temp = open(path_file, "w+")
        try:
            file_temp.write (self.get_json() )
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
            #Exucute SQL Command
            db.cursor.execute(sql)
            # Commit changes in database
            db.db.commit()
        except TypeError as e:
            print('---------------')
            print('Error when push to DB : %s') %sql
            print(e)
            print('---------------')
            # Rollback in case there is any error
            db.db.rollback()


    def isAlreadyExistInDatabase(self, db):
        labels = self.nomalizeString(self.labels)
        code = self.nomalizeString(self.code)

        sql = "SELECT `id` \
                FROM `magazine`.`magazine` \
                WHERE `type` = '%s' AND `code` = '%s' ;" %(labels, code)

        try:
            #Exucute SQL Command
            db.cursor.execute(sql)
            data = db.cursor.fetchall()
            if len(data) > 0 :
                return True
            return False

        except TypeError as e:
            print('---------------')
            print('Error when push to DB : %s') %sql
            print(e)
            print('---------------')
            return true;



    def nomalizeString(self,str):
        # Remove all single quote
        str = str.replace("'","")
        return str
# 
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
    x = web("url", "img", "tit","content con ", "hoho/hihi","123455")
    db = database()
    #x.insert_to_db(db)

    test = x.isAlreadyExistInDatabase(db)
    if test:
        print('Already exists')
    else:
        print('Not exists')

    #s = json.dumps(x.__dict__, indent=4, separators=(',', ':'))
    # x.write_to_file('Data')
    # print (x.get_json())


    