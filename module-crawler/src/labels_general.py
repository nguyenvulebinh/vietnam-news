#!/usr/local/bin/python
# coding: utf-8

# g_map_label_domain
# Dinh nghia bien toan cuc anh xa thong tin cac nhan cua cac trang bao
# Chuyen hoa ve dang dictionary
g_map_label_domain = {
    "Khoa học - Công nghệ":[
        "vietbao/Khoa Học",
        "Dân trí/Khoa học - Công nghệ",
        "Dân trí/Sức mạnh số",
        "vietbao/Công Nghệ", 
        "techtalk/Cong nghe",
        "vtv/Công nghệ", 
        "vnexpress/Khoa học",
        "vnexpress/Số hóa"
    ],
    "Kinh tế":[
        "vietbao/Kinh Tế",
        "vtv/Kinh tế",
        "vnexpress/Kinh doanh",
        "Dân trí/Kinh doanh"
    ],
    "Giáo dục":[
        "Dân trí/Giáo dục - Khuyến học",
        "vtv/Giáo dục",
        "vietbao/Giáo Dục",
        "vnexpress/Giáo dục"
    ],
    "Du lịch":[
        "vietbao/Du Lịch",
        "vietbao/Khám Phá Việt Nam",
        "vnexpress/Du lịch"
    ],
    "Thời sự":[
        "vietbao/Phóng Sự",
        "vietbao/Thế Giới",
        "vnexpress/Thế giới",
        "vtv/Truyền hình",
        "vtv/Trong nước",
        "vtv/Thế giới",
        "vtv/Việt Nam và thế giới", 
        "vnexpress/Thời sự",
        "Dân trí/Thế giới"
    ],
    "Việc làm":[
        "Dân trí/Việc làm"
    ],
    "Xe":[
        "vietbao/Xe",
        "Dân trí/Ô tô - Xe máy",
        "vnexpress/Xe"
    ],
    "Pháp luật":[
        "vietbao/An Ninh - Pháp Luật",
        "Dân trí/Pháp luật",
        "vnexpress/Pháp luật"
    ],
    "Độc giả":[
        "Dân trí/Bạn đọc",
        "Dân trí/Diễn đàn",
        "vtv/Góc khán giả"
    ], 
    "Thể thao":[
        "Dân trí/Thể thao",
        "vietbao/Bóng Đá",
        "vnexpress/Thể thao"
    ],
    "Sức khỏe":[
        "vietbao/Sức Khỏe",
        "Dân trí/Sức khỏe",
        "vtv/Sức khỏe",
        "vnexpress/Sức khỏe"
    ],
    "Giải trí":[
        "vietbao/Game",
        "vietbao/Media",
        "vietbao/Thế Giới Giải Trí",
        "vietbao/Cười",
        "vnexpress/Giải trí",
        "Dân trí/Giải trí",
        "vnexpress/Cười",
        "vtv/Văn hóa - Giải trí",
        "Dân trí/Chuyện lạ"

    ],
    "Đời sống - Xã hội": [
        "vietbao/Nhà Đất",
        "vietbao/Blog Hay",
        "vietbao/Đời Sống - Gia Đình",
        "vietbao/Xã Hội",
        "vietbao/Văn Hóa",
        "vietbao/Sống Đẹp",
        "vietbao/Nhịp Sống Trẻ",
        "vietbao/Người Việt Bốn Phương",
        "Dân trí/Tình yêu - Giới tính",
        "Dân trí/Xã hội",
        "Dân trí/Nhịp sống trẻ",
        "vnexpress/Tâm sự",
        "vnexpress/Gia đình",
        "vnexpress/Cộng đồng",
        "Dân trí/Tấm lòng nhân ái",
        "Dân trí/Văn hóa",
        "Dân trí/Đời sống",
        "vtv/Đời sống"
    ],
    "Tin khác":[
        "vnexpress",
        "Dân trí",
        "vietbao",
        "techtalk",
        "vtv"
    ]
}

# g_map_domain_label
# Dinh nghia bien toan cuc nhan cua cac trang bao -> nhan chung
# Chuyen hoa ve dang dictionary
g_map_domain_label = {}
for v in g_map_label_domain:
    for key in g_map_label_domain[v]:
        g_map_domain_label[key] = v

# Lop nhan - xac dinh nhan chung cho cac trang bao
class label_general():
    """Dinh nghia tap nhan chung cho cac trang web"""

    label_general = ""

    def __init__(self, label):
            if label in g_map_domain_label:
                self.label_general = g_map_domain_label[label] 

    def get_label_general(self):
        """ Lay thong tin nhan chung """
        return self.label_general 

# if __name__ == "__main__":
#     print ('helo world')
#     test = label_general("vnexpress/Pháp luật")
#     print test.get_label_general()