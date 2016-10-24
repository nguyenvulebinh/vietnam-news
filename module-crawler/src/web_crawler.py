# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
    
from bs4 import BeautifulSoup
import sys  
reload(sys)  
sys.setdefaultencoding('UTF-8')

from web import *
from libs_support import *
from rss_parser import *


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
                                if (a_tag['href'][1:] not in titles):
                                    titles.append(a_tag['href'][1:])
                
            except Exception, e:
                print '[Exception - get title web] '+url1
                print '[Exception - get title web] '+str(e)
                  
        return titles


    def get_list_web(self, max_len):

        list_web = []   # ds cac doi tuong web tra ve
        try:
            titles = self.get_titles()  # ds title cua 1 trang bao 
            
            # Chia deu so bai viet (max_len) cho cac chu de co so luong ~ nhau  
            count_title = titles.__len__()  # dung de tinh ti le gioi han sl bai 
            count_max_web = 0   # gia tri so web toi da cho title hien tai
            i_current_title = 0 # chi so cua title hien tai
            b_next_title = False
            
            for title in titles:
                
                # cai dat cac bien gioi han so luong bai viet
                b_next_title = False
                i_current_title += 1 
                count_max_web = (i_current_title*1.0 / count_title)*max_len
                print (list_web.__len__(), '/', count_max_web)

                # lay thong tin bai viet
                url_title = 'http://'+self.current_domain+'/'+title
                print('[Crawling] '+url_title)
                
                # lay danh sach bai viet moi tren trang nhat
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
                        if list_web.__len__() > count_max_web:
                            b_next_title = True
                            break

                        #Tao doi tuong 
                        if( content_paper['content_text']!=None and content_paper['labels']!= None) :
                            element = web(url1, image_url, title, content_paper['content_text'], content_paper['labels'])
                            list_web.append(element) 
                            
                            
                elif (self.current_domain == "kenh14.vn"
                        or self.current_domain == "vtv.vn" ):     
                    
                    #Lay cac the bai biet
                    contain_tags = []
                    list_url_crawled = []
                    
                    if self.current_domain == "kenh14.vn":
                        contain_tags.extend(soup.find_all("div", {'data-marked-zoneid':"k14_channel_b9"}))
                        contain_tags.extend(soup.find_all("div", 'slimScrollDiv'))
                        contain_tags.extend(soup.find_all("div", 'kcnwtn-hot-news'))
                        contain_tags.extend(soup.find_all("div", 'kcnw-list-news-wrapper'))
                    
                    elif self.current_domain == "vtv.vn":
                        contain_tags.extend(soup.find_all("div", "noibat1"))
                        contain_tags.extend(soup.find_all("div", 'noibat2'))
                        contain_tags.extend(soup.find_all("div", 'cate_right1'))
                        contain_tags.extend(soup.find_all("div", 'cate_right2'))
                        contain_tags.extend(soup.find_all("div", 'list_cate'))

                    # Lay tin bai dac biet
                    for div_tag in contain_tags:
                        if b_next_title == True: break
                        for a_tag in div_tag.find_all('a', href = True):
                            try:
                                #Kiem tra thong tin 
                                if ((not a_tag.has_attr('title')) or  (not a_tag.has_attr('href'))):
                                    continue
                                
                                #Lay thong tin 
                                title = a_tag["title"]
                                url1 = "http://"+self.current_domain + a_tag["href"]
                                image_url = ""
                                if  (a_tag.img != None): 
                                    if a_tag.img.has_attr('src') :
                                        image_url = a_tag.img["src"]    
                                
                                # Kiem tra trang nay xem da crawl hay chua 
                                if url1 not in list_url_crawled:
                                    
                                    content_paper = get_content_paper(url1); # tai va boc tach noi dung bai viet
                                    list_url_crawled.append(url1)
                                    
                                    # Kiem tra gioi han so luong web 
                                    if list_web.__len__() > count_max_web:
                                        b_next_title = True
                                        break

                                    #Tao doi tuong 
                                    if( content_paper['content_text']!=None and content_paper['labels']!= None) :
                                        element = web(url1, image_url, title, content_paper['content_text'], content_paper['labels'])
                                        list_web.append(element) 
                                
                            except Exception, e:
                                print '[Exception - list web ] '+url1
                                print '[Exception - list web ] '+str(e)
                                
        except Exception, e:
            print(str(e))
            
        return list_web

    

if __name__ == "__main__":   
    
    max_count_web = 200000
    rss_page_links = [
        "http://vietbao.vn/vn/rss", 
        "http://vnexpress.net/rss",
        "http://dantri.com.vn/rss"
    ]
    web_mannual_page_links = [
        "vtv.vn"  ,
        "kenh14.vn" 
    ]
    
    max_count_web_domain = max_count_web/(rss_page_links.__len__() + web_mannual_page_links.__len__()) 
    
    
    # Cac trang ko co rss
    for domain in web_mannual_page_links :
        web_crawler_instance = web_crawler(domain)
        for web_x in web_crawler_instance.get_list_web(max_count_web_domain):
            web_x.write_to_file('Data')
            
    # Cac trang co rss
    for link_rss in rss_page_links :
        parser = rss_parser(link_rss)
        for web_x in  parser.get_list_web(max_count_web_domain):
            web_x.write_to_file('Data')
    
