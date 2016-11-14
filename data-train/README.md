# vietnam-news
Viet Nam News

Module: data-train

- Muc dich:
	+ Chua tap data dulieu mau trong nhieu ngay crawl lien tuc 
	+ Lam du lieu mau cho tap hoc

- Noi dung: 
	+ Các folder chứa các bài báo có dạng : tên báo / sublabel1 / sublabel2/ ... / hash_code_url
	+ Các chủ đề để phân loại chỉ cần dựa vào sublabel1. (Khi học thì chỉ cần duyệt qua tất cả các file làm 1 tập thống nhất, trường labels có thể đọc từ nội dung file ra.) 
	+ Tên file = mã băm của url bài viết để tránh trùng lặp 
	+ Nội dung file ở dạng json.
	+ vd : 
	{
	    "url": "http://vtv.vn/the-gioi/phe-noi-day-syria-tu-choi-roi-aleppo-20161019091857056.htm",
	    "content": "\r\nVTV.vn - Liên quan đến việc Nga tạo điều kiện cho những lực lượng nổi dậy ở Syria rời đi và tách khỏi các nhóm cực đoan, các lực lượng này đã tuyên bố từ chối rút khỏi Aleppo.\r\nViệc rút khỏi thành phố Aleppo được coi là một sự đầu hàng trước quân Chính phủ Syria. Vì vậy, các lực lượng nổi dậy cương quyết bác đề nghị của Nga. Theo một đại diện của lực lượng này, ở Aleppo không có bất cứ nhóm nào liên quan tới mạng lưới khủng bố al-Qaeda.Trong một diễn biến khác, đài truyền hình RT của Nga đưa tin, bất chấp thời gian ngừng bắn nhân đạo, phe nổi dậy ở Syria đã nã pháo vào phía Tây Aleppo làm 3 người chết, 27 người bị thương, trong đó có trẻ em.VTV.vn - Cuộc tham vấn đẩy nhanh tiến trình hòa bình tại Syria do LHQ chủ trì ngày 17/10 đã không đạt được kết quả đáng kể.VTV.vn - Lực lượng đối lập tại Syria được Thổ Nhĩ Kỳ hậu thuẫn đã chiếm thị trấn Dabiq ở miền Bắc Syria ngày 16/10 từ tổ chức khủng bố Nhà nước Hồi giáo (IS) tự xưng.VTV.vn - Đêm 15/10, Nga, Mỹ và các bên tham dự hội nghị về Syria ở Lausanne (Thụy Sĩ) đã quyết định kéo dài những cuộc tiếp xúc trong bối cảnh hòa đàm chưa đạt được thỏa thuận.",
	    "labels": "vtv/Thế giới",
	    "image_url": "",
	    "title": "Phe nổi dậy Syria từ chối rời Aleppo"
	}
	 

