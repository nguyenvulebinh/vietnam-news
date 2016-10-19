# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
    
from bs4 import BeautifulSoup
import sys  
reload(sys)  
sys.setdefaultencoding('utf8')

from web import *
from libs_support import *
from rss_parser import *

G_DOMAIN = ""

class web_crawler():
    
    def __init__(self, domain):
        # ds cac trang web co trong ds crawler
        self.list_title = {} 
        
        # current_domain la domain se duoc crawl khi goi ham get_list_web
        if domain != None: self.current_domain = domain
        else : self.current_domain = 'dantri.com.vn'
        
        #khoi tao cac title cho cac domain 
        self.list_title[domain] = self.get_titles_of(domain)
       
        
    def set_current_domain(self, domain):
        self.current_domain = domain 
        
    def get_titles(self):
        titles = None
        if self.current_domain not in self.list_title : 
            # thuc hien ham lay title
            titles = self.get_titles_of(self.current_domain)
            # luu lai ds title da lay duoc 
            self.list_title[self.current_domain] = titles
        else: 
            titles = self.list_title[self.current_domain]
        return titles
        
    def get_titles_of(self, domain):
        titles = None
        if domain in self.list_title : 
            titles = self.list_title[domain]
        else:
            try:
                titles = []
                
                # Thuc hien get title web neu chua co
                raw_content = get_content('http://'+domain)
                soup = BeautifulSoup(raw_content, 'html.parser')
                
                # doc cac tiltle link
                div_menu_class = None
                if domain == 'kenh14.vn': 
                    div_menu_class = 'kt-main-menu'
                elif  domain == 'dantri.com.vn': 
                    div_menu_class = 'mgauto wid1004'
                elif  domain == 'vtv.vn':
                    div_menu_class = 'menu_chinh'
                    
                if div_menu_class != None: 
                    for div_menu in soup.find_all('div', div_menu_class):
                        for title_element in div_menu.find_all('li'):
                            for a_tag in title_element.find_all('a', href = True):
                                if(a_tag['href'] == '/' or a_tag['href'][0:1] != '/'): continue
                                titles.append(a_tag['href'][1:])
                
            except Exception, e:
                print '[Exception - get title web] '+url1
                print '[Exception - get title web] '+str(e)
                  
        return titles


    def get_list_web(self, max_len):
        try:
            list_web = []
            for title in self.get_titles():
                
                url_title = 'http://'+self.current_domain+'/'+title
                print(url_title)
                content = get_content(url_title)
                if content is None: return list_web
                soup = BeautifulSoup(content, 'html.parser')

                if self.current_domain == "dantri.com.vn":  
                    
                    #Lay cac the bai biet
                    div_tags = []
                    div_tags.extend(soup.find_all("div", {'data-boxtype' : 'zonenewsposition'}))
                    div_tags.extend(soup.find_all("div", {'data-boxtype' : 'timelineposition'}))

                    # Lay tin bai dac biet
                    for div_tag in div_tags:

                        try:
                            #Kiem tra thong tin 
                            a_tag = div_tag.a
                            if ((not a_tag.has_attr('title')) or  (not a_tag.has_attr('href'))
                                    or (a_tag.img == None) ): continue
                            if not a_tag.img.has_attr('src') : continue

                            #Lay thong tin 
                            title = a_tag["title"]
                            url1 = "http://"+self.current_domain + a_tag["href"]
                            image_url = a_tag.img["src"]
                            content_paper = get_content_paper(url1); # tai va boc tach noi dung bai viet

                        except Exception, e:
                            print '[Exception - get info in item rss] '+url1
                            print '[Exception - get info in item rss] '+str(e)
                            continue

                        # Kiem tra gioi han so luong web 
                        if list_web.__len__() > max_len:
                            return list_web

                        #Tao doi tuong 
                        if( content_paper['content_text']!=None and content_paper['labels']!= None) :
                            element = web(url1, image_url, title, content_paper['content_text'], content_paper['labels'])
                            list_web.append(element) 
                            
                            
                elif self.current_domain == "kenh14.vn":     
                    
                    #Lay cac the bai biet
                    contain_tags = []
                    list_url_crawled = []
                    contain_tags.extend(soup.find_all("div", {'data-marked-zoneid':"k14_channel_b9"}))
                    contain_tags.extend(soup.find_all("div", 'slimScrollDiv'))
                    contain_tags.extend(soup.find_all("div", 'kcnwtn-hot-news'))
                    contain_tags.extend(soup.find_all("div", 'kcnw-list-news-wrapper'))


                    # Lay tin bai dac biet
                    for div_tag in contain_tags:
                        for a_tag in div_tag.find_all('a', href = True):
                            try:
                                #Kiem tra thong tin 
                                if ((not a_tag.has_attr('title')) or  (not a_tag.has_attr('href'))
                                    or (a_tag.img == None) ): continue
                                if not a_tag.img.has_attr('src') : continue

                                #Lay thong tin 
                                title = a_tag["title"]
                                url1 = "http://"+self.current_domain + a_tag["href"]
                                image_url = a_tag.img["src"]
                                
                                # Kiem tra trang nay xem da crawl hay chua 
                                if url1 not in list_url_crawled:
                                    
                                    content_paper = get_content_paper(url1); # tai va boc tach noi dung bai viet
                                    list_url_crawled.append(url1)
                                    
                                    # Kiem tra gioi han so luong web 
                                    if list_web.__len__() > max_len:
                                        return list_web

                                    #Tao doi tuong 
                                    if( content_paper['content_text']!=None and content_paper['labels']!= None) :
                                        element = web(url1, image_url, title, content_paper['content_text'], content_paper['labels'])
                                        list_web.append(element) 
                                
                            except Exception, e:
                                print '[Exception - get info in item rss] '+url1
                                print '[Exception - get info in item rss] '+str(e)
                                continue

           
                
            return list_web

        except Exception, e:
            print(str(e))
            return None

    

if __name__ == "__main__":   
    
    rss_page_links = ["http://vietbao.vn/vn/rss", 'http://vnexpress.net/rss']
    web_mannual_page_links = ["dantri.com.vn","kenh14.vn" 
        # ,"vtv.vn"  
    ]
    
    # Cac trang co rss
#    for link_rss in rss_page_links :
#        parser = rss_parser(link_rss)
#        for web in  parser.get_list_web(1000):
#            web.write_to_file('Data')
    
    # Cac trang ko co rss
    for domain in web_mannual_page_links :
        web_crawler_instance = web_crawler(domain)
        for web_x in web_crawler_instance.get_list_web(1000):
            web_x.write_to_file('Data')
