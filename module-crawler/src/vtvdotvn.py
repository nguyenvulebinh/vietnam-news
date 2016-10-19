from bs4 import BeautifulSoup
import hashlib
import os
import requests

from web import *
from libs_support import *

class VtvArticle:
    def __init__(self, title, link):
        self.title = title
        self.link = link

    def write_to_file(self, path):
        file_temp = open(path + hashlib.sha1(self.link).hexdigest(), "w+")
        try:
            file_temp.write(self.link.encode('utf8') + "\n")
            file_temp.write(self.title.encode('utf8') + "\n")
            print self.title + "\nWriting......................................"
            content = get_content(self.link)
            if content is not None:
                soup = BeautifulSoup(content, 'html.parser')
                file_temp.write(soup.find_all("h2", "news-sapo")[0].text.encode('utf8') + "\n")
                file_temp.write(soup.find_all("div", attrs={"data-field": "body"})[0].text.encode('utf8') + " ")
                print self.title + "Done\n\n"
            else:
                file_temp.close()
                os.remove(path + hashlib.sha1(self.link).hexdigest())
                print self.title + "False\n\n"
        except Exception as e:
            file_temp.close()
            os.remove(path + hashlib.sha1(self.link).hexdigest())
            print self.title + "False\n\n"


def get_vtv_article_from_tag(tag):
    try:
        if tag["data-id"] != "":
            title = tag["title"]
            link = "http://vtv.vn" + tag["href"]
            return VtvArticle(title, link)
    except KeyError:
        return None


def get_list_article(url, list_articles, max_len):
    try:
        if list_articles.__len__() > max_len:
            return None
        content = get_content(url)
        if content is None:
            return None
        soup = BeautifulSoup(content, 'html.parser')
        if list_articles.__len__() == 0:
            # Lay tin bai dac biet
            for tag in soup.find_all("div", "noibat1")[0].findChildren():
                temp = get_vtv_article_from_tag(tag)
                if temp is not None:
                    list_articles.append(temp)

            for tag in soup.find_all("div", "noibat2")[0].findChildren():
                temp = get_vtv_article_from_tag(tag)
                if temp is not None:
                    list_articles.append(temp)

            for tag in soup.find_all("div", "cate_right1")[0].findChildren():
                temp = get_vtv_article_from_tag(tag)
                if temp is not None:
                    list_articles.append(temp)

            for tag in soup.find_all("div", "cate_right2")[0].findChildren():
                temp = get_vtv_article_from_tag(tag)
                if temp is not None:
                    list_articles.append(temp)

        for tag in soup.find_all("div", "list_cate")[0].findChildren():
            temp = get_vtv_article_from_tag(tag)
            if temp is not None:
                list_articles.append(temp)

        next_url = soup.find_all("a", "active")[0].nextSibling["href"].encode('utf8')
        get_list_article("http://vtv.vn" + next_url, list_articles, max_len)
    except KeyError:
        return None


list_title = ["trong-nuoc.htm"
#    ,
#    "the-gioi.htm", "kinh-te.htm", "truyen-hinh.htm", "viet-nam-va-the-gioi.htm",
#    "van-hoa-giai-tri.htm", "giao-duc.htm", "cong-nghe.htm", "doi-song.htm"
    ]
# list_title = ["/suc-khoe.htm"]
# list_title = ["/the-thao.htm"]

for title in list_title:
    list_articless = []
    get_list_article("http://vtv.vn/" + title, list_articless, 100)
    try:
        os.mkdir("DataCrawl/" + title)
    except Exception:
        None
    for article in list_articless:
        article.write_to_file("DataCrawl/" + title + "/")
