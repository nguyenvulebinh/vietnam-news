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


class web(web_simple):
    
    # phuong thuc khoi tao
    def __init__(self, url, image_url, title, content, labels):
        # khoi tao cha 
        web_simple.__init__(self, url, image_url, title)
        # cai dat thuoc tinh
        self.content = content.encode('utf8',  "ignore")
        self.labels = labels.encode('utf8',  "ignore")
        
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
    x = web("url", "img", "tit","content con ", "hoho/hihi")
    #s = json.dumps(x.__dict__, indent=4, separators=(',', ':'))
    x.write_to_file('Data')
    print (x.get_json())
    