# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import requests
from bs4 import BeautifulSoup
import sys  
reload(sys)  
sys.setdefaultencoding('UTF-8')

def get_content(url):
    
    """
    Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
    Accept-Encoding:gzip, deflate, sdch
    Accept-Language:en-US,en;q=0.8,vi;q=0.6
    Connection:keep-alive
    Cookie:__ltmc=225808911; __ltmb=225808911.202893004; __ltma=225808911.202893004.204252493; _gat=1; __RC=4; __R=1; _ga=GA1.3.938565844.1476219934; __IP=20217561; __UF=-1; __uif=__ui%3A-1%7C__uid%3A877575904920217840%7C__create%3A1475759049; __tb=0; _a3rd1467367343=0-9
    Host:dantri.com.vn
    Referer:http://dantri.com.vn/su-kien.htm
    Upgrade-Insecure-Requests:1
    User-Agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36
    """
    
    domains = url.split('/')
    if (domains.__len__() >= 3): domain = domains[2]
        
    headers = dict()
    headers['Accept'] = 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'
    headers['Accept-Encoding'] = 'gzip, deflate, sdch'
    headers['Accept-Language'] = 'en-US,en;q=0.8,vi;q=0.6'
    headers['Connection'] = 'keep-alive'
    headers['Host'] = domain
    headers['Referer'] = url
    headers['Upgrade-Insecure-Requests'] = '1'
    headers[
        'User-Agent'] = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36'
    try:
        
        r = requests.get(url, headers=headers, timeout=10)
        r.encoding  = 'utf-8' 
        r.close()
        return r.text#.encode('utf-8', 'inorge')
    except Exception, e:
        print('Exception'+ str(e))
        return None


def get_content_paper(url):
    
    content_paper = {}
    content_paper["labels"] = labels = None
    content_paper["content_text"] = content_text =None
    
    try:
        raw_content = get_content(url)
        soup = BeautifulSoup(raw_content, 'html.parser')
        
        if url.find("http://dantri.com.vn/") == 0:    
            
            for element in soup.find_all(id="ctl00_IDContent_Tin_Chi_Tiet"):

                #lay thong tin nhan bai bao 
                labels = ""
                for info_label in element.find_all('li',{'itemprop':'itemListElement'}):
                    label = info_label.a.span.text.encode('utf8',  "ignore")
                    labels += (label+"/")

                labels = labels[:-1]

                #lay noi dung bai bao
                content_text = ""
                for content in element.find_all('h2','fon33 mt1 sapo'):
                    for a_tag in content.find_all('a'):
                        a_tag.extract()
                    content_text += content.text.encode('utf8',  "ignore")

                for content in element.find_all('div',id="divNewsContent"):
                    for raw in content.find_all('p'):
                        content_text += raw.text.encode('utf8',  "ignore")
                
        if url.find("vnexpress.net") != -1:  
              
            #lay thong tin nhan bai bao
            labels = "vnexpress"
            divs_headermain = soup.find_all(id="header_main")
            if(divs_headermain!=None and divs_headermain.__len__() > 0) :
                div_headermain = divs_headermain[0]
                for labels_element in div_headermain.find_all('div', 'block_breakumb_left'):
                    for label in labels_element.find_all('a',href = True):
                        labels += ("/"+label.text)
            
            #lay noi dung bai bao
            content_text = ""
            divs_bodymain = soup.find_all(id="left_calculator")
            if(divs_bodymain!=None and divs_bodymain.__len__() > 0) : 
                div_bodymain = divs_bodymain[0]
                for div_text in div_bodymain.find_all('div', 'fck_detail width_common block_ads_connect'):
                    for p_element in div_text.find_all('p', 'Normal'):
                        content_text += p_element.text
               
        elif url.find("vietbao.vn") != -1:  
            
            labels = "vietbao"
            content_text = ""

            for div_container in soup.find_all('div', id="container") :
                #lay thong tin nhan bai bao
                for ul_tag in div_container.find_all('ul', 'path-way-2'):
                    for label in ul_tag.find_all('a','text', href = True):
                        labels += ("/"+label.text)
                            
                #lay noi dung bai bao
                for div_content in div_container.find_all('div', id="vb-content-detailbox"):
                    for p_element in div_content.find_all(['p', 'b']):
                        # xoa bo moi the div trong p
                        for div in p_element.find_all('div'):
                            div.extract()
                        # ghi lai cac gia tri text
                        content_text += p_element.text
                    break # chi xu ly the co id="vb-content-detailbox"

                break # chi xu ly the co id="container" dau tien 
                
        elif url.find("kenh14.vn") != -1:  
            
            labels = "kenh14"
            content_text = ""

            for div_container in soup.find_all('div', 'kenh14-detail') :
                #lay thong tin nhan bai bao
                for sub_menu in div_container.find_all('div', "kbw-submenu"):
                    for li_tag in sub_menu.find_all('li', 'kbwsli active'):
                        for label in li_tag.find_all('a', href = True):
                            if(label['href'] == '/'): continue # bo qua nhan = trang chu
                            labels += ("/"+label.text)
                            break
                        break
                    break #chi lay noi dung cua submenu dau tien
                            
                #lay noi dung bai bao
                for div_content in div_container.find_all('div', 'klw-new-content'):
                    for h2_element in div_content.find_all('h2',['knc-sapo','knd-sapo']):
                        # ghi lai cac gia tri text
                        content_text += h2_element.text
                    for div_knd in div_container.find_all('div', ['knc-content','knd-content']):
                        for p_element in div_knd.find_all('p'):
                            # xoa bo moi the div trong p
                            for div in p_element.find_all('div'): div.extract()
                            # ghi lai cac gia tri text
                            content_text += p_element.text

                    break # chi xu ly the co class="kcccw-news-detail" dau tien
            
        elif url.find("vtv.vn") != -1:  
            
            labels = "vtv"
            content_text = ""

            for div_container in soup.find_all('div', 'main') :
                #lay thong tin nhan bai bao
                for div_tag in div_container.find_all('div', "submenu"):
                    for label in div_tag.find_all('a', href = True):
                        labels += ("/"+label.text)
                        break # lay the a dau tien 
                            
                #lay noi dung bai bao
                for div_detail in div_container.find_all('div', 'contentleft'):
                    for h2_element in div_detail.find_all('h2','news-sapo'):
                        # ghi lai cac gia tri text
                        content_text += h2_element.text
                    for div_knd in div_detail.find_all('div', {"data-field":"body"}):
                        for p_element in div_knd.find_all('p'):
                            # xoa bo moi the b trong p
                            for div in p_element.find_all('b'): div.extract()
                            # ghi lai cac gia tri text
                            content_text += p_element.text
                    
                break # chi xu ly the co class="main" dau tien 
            
        content_paper["labels"] = labels      
        content_paper["content_text"] = content_text   
        
    except Exception, e:
        print("[Exception - get content paper] "+url)
        print("[Exception - get content paper] "+str(e))
        
    return content_paper


# 
#
#
#
#
#
#
#
#
if __name__ == "__main__":
    print "Hello World"
    content_paper = get_content_paper("http://kenh14.vn/truoc-gary-thanh-vien-nay-cung-de-lai-nhieu-tiec-nuoi-khi-chia-tay-running-man-2016102716283767.chn")
    print content_paper["labels"]
    print  content_paper["content_text"] 
    