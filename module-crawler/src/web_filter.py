
from web import *

class Web_filter(object):

    """Lop tinh - gom cac phuong thuc tinh de loc doi tuong web truoc khi crawl noi dung ve """

    def __init__(self):
        None

    # thoi gian cuoi cung de 1 trang web dc chap nhan crawl ve, cac trang web publish sau thoi diem nay se dc chap nhan
    check_last_time = None
    # so web toi da 1 sublabel
    check_max_count_web_each_sublabel = None
    # so web toi da 1 domain
    check_max_count_web_each_domain = None

    # Cai dat thoi gian cua bai viet cuoi cung voi dinh dang: 2014-07-29, 22:45:00+07:00
    @staticmethod
    def set_last_time(date_time_string):
        try:
            # Parse thu tg - ko parse dc thi thoi
            Web_filter.check_last_time = dateutil_parser.parse(date_time_string)
        except:
            None
    @staticmethod
    def set_max_count_web_each_sublabel(number):
        Web_filter.check_max_count_web_each_sublabel = number

    @staticmethod
    def set_max_count_web_each_domain(number):
        Web_filter.check_max_count_web_each_domain = number

    @staticmethod
    def remove_all_check():
        Web_filter.check_last_time = None
        Web_filter.check_max_count_web_each_sublabel = None
        Web_filter.check_max_count_web_each_domain = None

    @staticmethod
    def check(web_x, count_web_each_sublabel = 100000, count_web_each_domain = 100000):
        b_ret = True
        try:
            if b_ret == True and Web_filter.check_last_time != None:
                b_ret = web_x.get_date_obj() > Web_filter.check_last_time

            if b_ret == True and Web_filter.check_max_count_web_each_sublabel != None:
                b_ret = count_web_each_sublabel < Web_filter.check_max_count_web_each_sublabel

            if b_ret == True and Web_filter.check_max_count_web_each_domain != None:
                b_ret = count_web_each_domain < Web_filter.check_max_count_web_each_domain

        except Exception,e :
            print ("[Exception - check filter]"+str(e))
            b_ret = False

        return b_ret

    # set_last_time = staticmethod(set_last_time)
    # set_max_count_web_each_sublabel = staticmethod(set_max_count_web_each_sublabel)
    # set_max_count_web_each_domain = staticmethod(set_max_count_web_each_domain)
    # remove_all_check = staticmethod(remove_all_check)
    # check = staticmethod(check)

#
#
#
#
#
#
if __name__ == '__main__':
    Web_filter.set_max_count_web_each_domain(100)
    hy= web("", "", "","","", "")
    print hy.get_json()
    print Web_filter.check(hy)