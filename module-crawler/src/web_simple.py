# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import hashlib
import re

from dateutil import parser as dateutil_parser  # install: pip install python-dateutil

class web_simple:
    """
    Thong tin co ban cua 1 trang web
    """
    
    def __init__(self, url, image_url, title, date):
        self.image_url = image_url
        self.url = url
        self.title = title
        self.code = hashlib.sha1(self.url).hexdigest()
        domain = re.search('(?<=http://)[^/]+', url)
        if domain != None:
            self.domain = domain.group(0)
        else:
            self.domain = "Unknow"

        date_tmp = ""
        if date != None and date.__len__() > 0:
            try:
                date_x = dateutil_parser.parse(date)
                date_tmp = date_x.date().__str__() + ", " + date_x.timetz().__str__()
            except Exception, e:
                print "[Exception - parse date when init web_simple object]" + str(e)
                date_tmp = ""
        self.date = date_tmp


    def get_title(self):
        return self.title
    
    def get_url(self):
        return self.url
    
    def get_image_url(self):
        return self.image_url

    def get_date_obj(self):
        return dateutil_parser.parse(self.date)

    def get_date_string(self):
        return self.date

    def get_domain(self):
        return self.domain