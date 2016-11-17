
import os
import requests
import json

from web import *
from libs_support import *
from rss_parser import *
from database import *

class Solr_helper:

    """ Ho tro He thong tu dong cap nhat du lieu - su dung post.jar de tu dong cap nhat du lieu moi vao he thong  theo
    tung khoang thoi gian nhat dinh """

    def __init__(self, db_name = "btl-tktdtt", domain = "localhost", port = 8983, solr_home = "."):
        self.server_db_name = db_name
        self.server_port = port
        self.server_domain = domain
        self.server_db_name = db_name

        #default
        self.set_solr_home(solr_home)

    # Cai dat cua solr
    def set_post_tool(self, path_tool):
        self.server_post_tool = path_tool
    def set_solr_home(self, path_home):
        if(path_home.endswith("/")): path_home = path_home[:-1]
        self.server_solr_home = path_home
        self.server_post_tool = path_home +"/example/exampledocs/post.jar"

    # update du lieu json web vao he thong
    def update_use_tool(self, path_file_json_data, type_update="text/json"):
        # use java tool
        cmd_update_data = "java -Dtype={2} -Durl=http://{0}:{1}/solr/{3}/update -jar {5} {4}" \
            .format(self.server_domain, self.server_port, type_update, self.server_db_name, path_file_json_data,
                    self.server_post_tool)
        print (cmd_update_data)
        # os.system(cmd_update_data)

    # update du lieu json web vao he thong
    def update(self, data_json):
        # post paterm: curl 'http://localhost:8983/solr/testBTL/update/json/docs' -H 'Content-type:application/json' -d '[{},{}]'
        # use Data with Index Handlers (DIH) Http post
        url = "http://{0}:{1}/solr/{2}/update/json/docs" \
            .format(self.server_domain, self.server_port, self.server_db_name)
        headers = dict()
        headers['Content-type'] = 'application/json'
        try:
            r = requests.post(url=url,data=data_json,headers=headers)
            r.close()
            return r.text  # .encode('utf-8', 'inorge')
        except Exception, e:
            print('Exception' + str(e))
            return None

    def reload(self):
        # post paterm: curl "http://localhost:8983/solr/admin/cores?action=RELOAD&core=mycore"
        # use Data with Index Handlers (DIH) Http post
        url = "http://{0}:{1}/solr/admin/cores?action=RELOAD&core={2}" .format(self.server_domain, self.server_port,self.server_db_name)
        try:
            r = requests.post(url=url)
            r.close()
            return r.text  # .encode('utf-8', 'inorge')
        except Exception, e:
            print('Exception' + str(e))
            return None

def crawl_data():
    max_count_web = 500
    rss_page_links = [
        # "http://vietbao.vn/vn/rss",
        # "http://vnexpress.net/rss",
        # "http://dantri.com.vn/rss",
        # "http://vtv.vn/rss",
        "http://techtalk.vn/"
    ]
    web_mannual_page_links = [
        # "vtv.vn"  ,
        "kenh14.vn"
    ]

    # Cai dat bo loc crawl web
    # Web_filter.set_last_time("2016-10-26, 22:20:08+07:00")  # Bai viet moi hon ke tu thoi diem xxx
    # Web_filter.set_limit_time("2016-10-26, 22:20:08+07:00", "2016-10-26, 23:20:08+07:00")  # Bai viet trong khoang tg
    Web_filter.set_max_count_web_each_domain(10000)  # moi domain khong vuot qua 1000
    Web_filter.set_max_count_web_each_sublabel(100)  # moi label trong 1 domain k vuot qua 100

    # Cac trang co rss
    data = "["
    for link_rss in rss_page_links:
        parser = rss_parser(link_rss)
        webs = parser.get_list_web()
        for web_x in  webs:
            data += (web_x.get_json()+",")
            # web_x.write_to_file('/mnt/01CDF1ECE3AB4280/DH/NAM_5/Ki_1/TimkiemTrinhDien/BTL/vietnam-news/data-train')

    if data.__len__() > 1:
        data = data[:-1]+"]"
        solr = Solr_helper(db_name="testBTL")
        solr.set_solr_home("/mnt/01CDF1ECE3AB4280/DH/NAM_5/Ki_1/TimkiemTrinhDien/BTL/solr-6.2.1")

        print (solr.update(data))
        print (solr.reload())




if __name__ =="__main__":
    solr =  Solr_helper( db_name = "testBTL")
    solr.set_solr_home("/mnt/01CDF1ECE3AB4280/DH/NAM_5/Ki_1/TimkiemTrinhDien/BTL/solr-6.2.1")
    # # solr.update("/mnt/01CDF1ECE3AB4280/DH/NAM_5/Ki_1/TimkiemTrinhDien/BTL/vietnam-news/data-train/techtalk/Cong\ nghe/31fa871c7d521106e28c45f567a63445c33e1186.json")
    #
    # data_test = []
    # data_test.append({
    #     "code": "55421c7d521106e28c45f567a63445c33e118744446",
    #     "title": "test dddd vcc c dsf" ,
    #     "url": "http://techtalk.vn/van-de-da-ngon-ngu-trong-angularjs.html",
    #     "labels": "techtalk/Cong nghe",
    #     "content": "tset content ",
    #     "image_url": "",
    #     "date": "2016-11-14, 12:00:02+00:00"
    # })
    # data_test.append({
    #     "code": "12345651717ebecaeb1c179522eff5dcc19c86ce8",
    #     "title": "test title ",
    #     "url": "http://techtalk.vn/tim-hieu-ve-middleware-trong-expressjs.html",
    #     "labels": "techtalk/Cong nghe",
    #     "content": "test ddddd content ",
    #     "image_url": "",
    #     "date": "2016-11-13, 01:00:14+00:00"
    # })
    crawl_data()
    # data_json = (json.dumps(data_test,indent=4, separators=(',', ': '), ensure_ascii=False))
    # solr.update(data_json)
    # print (solr.reload())

