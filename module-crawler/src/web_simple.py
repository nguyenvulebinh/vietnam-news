# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class web_simple:
    """
    Thong tin co ban cua 1 trang web
    """
    
    def __init__(self, url, image_url, title):
        self.image_url = image_url.encode('utf8',  "ignore")
        self.url = url.encode('utf8',  "ignore")
        self.title = title.encode('utf8',  "ignore")

    def get_title(self):
        return self.title
    
    def get_url(self):
        return self.url
    
    def get_image_url(self):
        return self.image_url
    