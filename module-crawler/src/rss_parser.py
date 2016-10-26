# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

from bs4 import BeautifulSoup   # external lib
import sys  
reload(sys)  
sys.setdefaultencoding('utf-8')

from libs_support import *
from web import *

class rss_parser():
    """
    Lop crawl du lieu cac trang bao cung cap rss vd: http://vnexpress.net/rss
    """
    
    # Khoi tao list_rss_link = link chua rss web cung cap vd: http://vnexpress.net/rss
    def __init__(self, link_web_rss):
        self.link_web_rss = link_web_rss
        self.links_rss = None
    
    # Lay tat ca cac rss title web server cung cap 
    def get_links_rss(self):
        
        # tra ve gia tri neu da ton tai 
        if(self.links_rss != None): return self.links_rss
        
        # Lay noi dung neu chua co
        raw_content = get_content(self.link_web_rss)
        soup = BeautifulSoup(raw_content.encode('utf-8', 'inorge'), 'html.parser')

        self.links_rss = {}
        if  (self.link_web_rss.find('vnexpress.net') != -1 
            or self.link_web_rss.find('dantri.com.vn') != -1) :
            
            domain = ""
            
            # Lay khoi div rss
            if self.link_web_rss.find('vnexpress.net') != -1 :
                domain = "vnexpress.net"
                divrss_tags = soup.find_all('div', id="rsspage")
            elif self.link_web_rss.find('dantri.com.vn') != -1:
                domain = "dantri.com.vn"
                divrss_tags = soup.find_all('div', id="rss")
                
            if(divrss_tags == None or divrss_tags.__len__() == 0): 
                return self.links_rss
            else: divrss_tag = divrss_tags[0]
                
            # Duyet qua cac the a -> link
            for a_tag in divrss_tag.find_all('a', href=True):
                if (a_tag["href"].find ('http://') == -1 ):
                    self.links_rss["http://"+domain+a_tag["href"]] = True
                else :
                    self.links_rss[a_tag["href"]] = True
                
                
        elif  self.link_web_rss.find('vietbao.vn') != -1:
            
            domain = "vietbao.vn"
            
            # Lay khoi div rss
            table_rss_tags = soup.find_all('table', 'vivavietnamrss')
            if(table_rss_tags == None or table_rss_tags.__len__() == 0): 
                return self.links_rss
            else: table_rss_tag = table_rss_tags[0]
                
            # Duyet qua cac the tr -> a[0] -> link
            for tr_tag in table_rss_tag.find_all('tr'):
                for a_tag in tr_tag.find_all('a', href=True):
                    if (a_tag["href"].find ('http://') == -1 ):
                        self.links_rss["http://"+domain+a_tag["href"]] = True
                    else :
                        self.links_rss[a_tag["href"]] = True
                    break   # chi lay the a dau tien 
        
        return self.links_rss
    
    # Lay tat ca cac noi dung web  
    def get_list_web(self,  max_len):
        try:
            list_webs = []
            
            # Chia deu so bai viet (max_len) cho cac chu de co so luong ~ nhau  
            count_title = self.get_links_rss().__len__()  # dung de tinh ti le gioi han sl bai 
            count_max_web = 0   # gia tri so web toi da cho title hien tai
            i_current_title = 0 # chi so cua title hien tai
            b_next_title = False
            
            # lay cac link rss
            for url in self.get_links_rss():
                
                # cai dat cac bien gioi han so luong bai viet
                b_next_title = False
                i_current_title += 1 
                count_max_web = (i_current_title*1.0 / count_title)*max_len
                print (list_webs.__len__(), ' - ', count_max_web)
                
                print ('[Crawling] '+url)
                content = get_content(url)
                if content is None: return list_webs
                soup = BeautifulSoup(content, 'html.parser' )
                if soup == None: continue
                if (url.find("vnexpress.net") != -1 or  url.find("vietbao.vn") != -1
                    or   url.find("dantri.com.vn") != -1):
                    
                    # Lay tin bai dac biet
                    for item in soup.find_all('item'):
                        
                        try:
                            #Lay thong tin 
                            url1 = item.link.text
                            title = item.title.text
                            CData = BeautifulSoup(item.description.text, 'html.parser') 
                            #MANHTIEN ////////////
                            date = item.pubdate.text
                            #MANHTIEN ////////////
                            if(CData.img!=None): image_url = CData.img['src']
                            content_paper = get_content_paper(url1); # tai va boc tach noi dung bai viet
                        
                        except Exception, e:
                            print '[Exception - get info in item rss] '+url1
                            print '[Exception - get info in item rss] '+str(e)
                            continue

                        #Kiem tra gioi han so luong bai bao
                        if list_webs.__len__() >= count_max_web:
                            b_next_title = True
                            break
                        
                        #Tao doi tuong 
                        if(url1 != None and title != None and content_paper['content_text']!=None
                            and content_paper['labels']!= None) :
                            #MANHTIEN ////////////
                            element = web(url1, image_url, title, content_paper['content_text'], content_paper['labels'], date)
                            #MANHTIEN ////////////
                            list_webs.append(element) 
                
            return  list_webs

        except Exception, e:
            print('[Exception - get list_webs rssparser] '+str(e))
            return list_webs
        

# 
#
#
#
#
#
#
#
#
# local test
if __name__ == "__main__":    
    parser = rss_parser('http://vnexpress.net/rss/')
    webs = parser.get_list_web(100)
    for web in  webs:
        web.write_to_file('Data')
        #print (web.get_json())
