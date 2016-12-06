package nb.cblink.vnnews.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import nb.cblink.vnnews.model.FeedTopic;
import nb.cblink.vnnews.model.News;

/**
 * Xoa khi demo xong
 * Created by nguyenbinh on 17/11/2016.
 */


public class TestListData {

    public static void getData(ArrayList<FeedTopic> listTopic) {
        try {
            JSONObject jsonObj = new JSONObject(data).getJSONObject("Dân trí");
            Iterator<String> map = jsonObj.keys();
            while (map.hasNext()) {
                FeedTopic topic = new FeedTopic();
                String key = map.next();
                topic.setNameTopic(key);
                JSONObject topicObj = jsonObj.getJSONObject(key);
                Iterator<String> mapNews = topicObj.keys();
                while (mapNews.hasNext()) {
                    News news;
                    try {
                        news = new News();
                        String keyNews = mapNews.next();

                        JSONObject newsObj = topicObj.getJSONObject(keyNews);
                        news.setNewsTitle(newsObj.getString("title"));
                        news.setNewsUrl(newsObj.getString("url"));
                        news.setImageUrl(newsObj.getString("image_url").replace("zoom/80_50/", ""));
                        news.setContent(newsObj.getString("content").replace("\n", "").trim());
                        news.setTime(newsObj.getString("date"));
                        news.setPaperName("Dân trí");
                        if (topic.getListNews().size() == 0) news.setFirstNews(true);
                        else news.setFirstNews(false);
                        topic.addNews(news);
                    } catch (Exception e) {
                    }
                }
                listTopic.add(topic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    static String data = "{\n" +
            "  \"Dân trí\" : {\n" +
            "    \"Thể thao \" : {\n" +
            "      \"3f3cc6a73cc865a0a7f56d26dd1c2f49aac294f7\" : {\n" +
            "        \"code\" : \"3f3cc6a73cc865a0a7f56d26dd1c2f49aac294f7\",\n" +
            "        \"content\" : \"\\nDân trí Bao nhiêu chiếc gương ô tô đã vỡ? Quá nhiều, từ những hành động vô ý va quẹt đến cả cố ý đập phá. \",\n" +
            "        \"date\" : \"2016-11-13, 17:06:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/l-i-xin-l-i-l-m-v-g-ng-t-1479031584132.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giáo dục - Khuyến học \",\n" +
            "        \"title\" : \"Câu chuyện đẹp sau chiếc gương ô tô vỡ\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giao-duc-khuyen-hoc/cau-chuyen-dep-sau-chiec-guong-o-to-vo-20161113170810241.htm\"\n" +
            "      },\n" +
            "      \"cd0b0baa3ad2cb56ca35d82337e4c725ddc6c309\" : {\n" +
            "        \"code\" : \"cd0b0baa3ad2cb56ca35d82337e4c725ddc6c309\",\n" +
            "        \"content\" : \"\\nDân trí “Chúng ta dùng các mĩ từ để ghi nhận công lao của các thầy cô chưa đủ. Hãy giảm các mĩ từ đi, để nghe những tâm sự rất thật lòng của các thầy cô ở huyện đảo, về khó khăn đang gặp phải. Tôi muốn chính các thầy cô chia sẻ ở đây, người thật, việc thật, để chúng tôi không phải nghe qua trung gian nữa”.\\nTrên đây là yêu cầu của Bộ trưởng Bộ GD&ĐT Phùng Xuân Nhạ tại buổi gặp mặt tuyên dương giáo viên tiêu biểu xuất sắc đang công tác tại các huyện đảo, xã đảo năm 2016 vào sáng 13/11.Phải chấm bài dưới cột đèn đườngTrong 42 giáo viên được tuyên dương năm nay, có 25 cô giáo và 17 thầy giáo. \",\n" +
            "        \"date\" : \"2016-11-13, 18:09:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/9c6c55dba2/2016/11/13/10-1479031497879.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giáo dục - Khuyến học \",\n" +
            "        \"title\" : \"Bộ trưởng Giáo dục: “Tôi muốn nghe tâm sự thật lòng của các thầy cô về khó khăn”\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giao-duc-khuyen-hoc/bo-truong-giao-duc-toi-muon-nghe-tam-su-that-long-cua-cac-thay-co-ve-kho-khan-20161113180912559.htm\"\n" +
            "      },\n" +
            "      \"4e308c4d53516d91bddea2eeafbf766b44b11bf6\" : {\n" +
            "        \"code\" : \"4e308c4d53516d91bddea2eeafbf766b44b11bf6\",\n" +
            "        \"content\" : \"\\nDân trí Nhiều siêu mẫu đình đám \\\"ép\\\" mình theo một chế độ tập luyện hàng ngày rất nghiêm túc và có phần \\\"khắc nghiệt\\\" để sở hữu cơ bụng săn chắc ấn tượng.\\n Siêu mẫu hai con người Hà Lan Doutzen Kroes tới phòng tập mỗi ngày, cô thường tập nhảy dây, chạy và boxing. Người đẹp chú trọng chế độ ăn uống không quá nhiều tinh bột, đường, cô thích ăn sữa chua, trái cây và các thanh vitamins tổng hợp để giữ dáng Candice Swanepoel tập thể dục với huấn luyện viên riêng. Các bài tập boxing và bài tập cho cơ bụng được cô chú trọng nhiều. Người đẹp thường tới phòng tập 3 lần/tuần. \",\n" +
            "        \"date\" : \"2016-11-13, 17:41:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/do9-1479034088600.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Những chân dài sở hữu cơ bụng săn chắc đáng kinh ngạc\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/nhung-chan-dai-so-huu-co-bung-san-chac-dang-kinh-ngac-20161113175033584.htm\"\n" +
            "      },\n" +
            "      \"0b216642da70a945f6b28f421a95d41c35c548b8\" : {\n" +
            "        \"code\" : \"0b216642da70a945f6b28f421a95d41c35c548b8\",\n" +
            "        \"content\" : \"\\r\\n    Việc Donald Trump, một người thiếu kinh nghiệm chính trị, đắc cử Tổng thống Mỹ đã khiến nhiều người lo sợ. Tuy nhiên, có một số lý do cho thấy ông Trump có thể là một Tổng thống Mỹ thành công.\\nBiết mình biết ngườiThứ nhất, để đi tới chiếc ghế Tổng thống, với tư cách là một doanh nhân, thay vì chính trị gia, Trump chắc hẳn là một người thông minh. Thứ hai, ông biết rõ một điều mà chúng ta ai cũng rõ. Đa phần những gì ông hứa nếu đắc cử chả có ý nghĩa gì, và đó là lý do ông có thể không thực hiện lời hứa, giáo sư Richard W. Painter thuộc trường luật Đại học Minnesota bình luận trên báo NY Times .Ngoài ra, theo nhà bình luận Sandhu của Lancashire Eveningpost, khi làm Tổng thống, \",\n" +
            "        \"date\" : \"2016-11-13, 21:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161112170625-1112g1-1479030766177.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Vì sao Trump sẽ là Tổng thống Mỹ thành công?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/vi-sao-trump-se-la-tong-thong-my-thanh-cong-20161113165841355.htm\"\n" +
            "      },\n" +
            "      \"16a2bb3c0b9df104661967bd20f112700045bb3a\" : {\n" +
            "        \"code\" : \"16a2bb3c0b9df104661967bd20f112700045bb3a\",\n" +
            "        \"content\" : \"\\r\\n    Những bài tập trận tiêu diệt khủng bố, thả dù thiết bị quân sự hay điều khiển máy bay không người lái đều được thực hiện thành công bởi binh lính của Nga, Serbia và Belarus trong cuộc tập trận “Anh em Slavic 2016” tại Serbia.\\nCuộc tập trận \\\"Anh em Slavic 2016\\\" đã kết thúc tốt đẹp vào hôm 9-11, sau khi có sự tham gia của khoảng 250 binh lính Nga, bao gồm lực lượng đặc biệt và lính dù, 50 quân nhân Belarus cùng với 450 binh sĩ từ lực lượng vũ trang Serbia.\",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/5827a1edc3618870528b458b-1-1479032295463.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Binh lính Nga - Serbia - Belarus tập trận '\\\"Anh em Slavic 2016\\\"\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/binh-linh-nga-serbia-belarus-tap-tran-anh-em-slavic-2016-20161113173102834.htm\"\n" +
            "      },\n" +
            "      \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\" : {\n" +
            "        \"code\" : \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\",\n" +
            "        \"content\" : \"\\r\\n    Các tàu chiến của Nga được triển khai trong vùng biển Syria đã bắt đầu tấn công các vị trí của nhóm khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại cực lớn cho phiến quân.\\nAl-Jafa, một chuyên gia về các vấn đề chiến lược hôm 12-11 tiết lộ, tên lửa hành trình Kalibr của Nga đã tấn công thành trì của những kẻ khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại nặng nề cho phiến quân. \",\n" +
            "        \"date\" : \"2016-11-13, 19:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/hai-quan-nga-bat-dau-tan-cong-aleppo-1479031687081.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Tàu chiến Nga bắt đầu tấn công căn cứ của phiến quân ở Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/tau-chien-nga-bat-dau-tan-cong-can-cu-cua-phien-quan-o-aleppo-20161113171219445.htm\"\n" +
            "      },\n" +
            "      \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\" : {\n" +
            "        \"code\" : \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\",\n" +
            "        \"content\" : \"\\nDân trí Tháng Mười chưa cười đã tối, ở Trung Âu là thời khắc đổi sang giờ mùa đông, 3h sáng chủ nhật vặn ngược đồng hồ thành 2h. Xa Hà Nội cả vạn cây số, thời khắc này ca khúc “Có phải em mùa Thu Hà Nội\\\" sao mà quá gợi nhớ gợi thương...\\r\\nMới “tháng Tám mùa Thu lá khởi vàng chưa nhỉ”, thoắt cái đã qua Quốc khánh 2/9 và rồi tháng 10 ập tới... cây cối nơi nơi đồng loạt gọi nhau trút lá, trải thảm vàng ... mời gọi!Rất nhanh đồng loạt lá đổi màu chiều còn xanh, sáng hôm sau đã ngả vàng rực rỡ. \",\n" +
            "        \"date\" : \"2016-11-13, 15:50:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/tn-lavang1-1479024789428.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ostrava mùa Xanh - Vàng - Đỏ - Trắng\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ostrava-mua-xanh-vang-do-trang-20161113155102516.htm\"\n" +
            "      },\n" +
            "      \"7c13bd3214e8c8facf69d93ffbed3d1e49e0f56a\" : {\n" +
            "        \"code\" : \"7c13bd3214e8c8facf69d93ffbed3d1e49e0f56a\",\n" +
            "        \"content\" : \"\\nDân trí Mối quan hệ giữa ngôi sao gạo cội Jon Voight và con gái Angelina Jolie không thực sự suôn sẻ những năm gần đây.\\n Nam diễn viên Jon Voight dự công chiếu phim Fantastic Beasts and Where To Find Them tại New York cách đây ít ngày, ông hiển nhiên được hỏi về vụ ly dị gây xôn xao của con gái Angelina Jolie \\\"Cảm ơn mọi người đã quan tâm, tôi mong rằng mọi việc sẽ ổn thỏa,\\\" nam diễn viên 77 tuổi thể hiện sự hi vọng của ông trong mối quan hệ giữa con gái và chồng cũ Brad Pitt Mối quan hệ giữa ngôi sao gạo cội Jon Voight và con gái Angelina Jolie không thực sự suôn sẻ những năm gần đây sau khi Jon chia sẻ trên truyền hình rằng ông nghĩ con gái có vấn đề thần kinh. \",\n" +
            "        \"date\" : \"2016-11-13, 17:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/jo3-1479032072067-crop-1479032115560.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Bố Angelina Jolie lần đầu lên tiếng về vụ ly dị của con gái\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/bo-angelina-jolie-lan-dau-len-tieng-ve-vu-ly-di-cua-con-gai-20161113171754384.htm\"\n" +
            "      },\n" +
            "      \"878787b6cb7a34cb3328404039104572df8d9691\" : {\n" +
            "        \"code\" : \"878787b6cb7a34cb3328404039104572df8d9691\",\n" +
            "        \"content\" : \"\\nDân trí Chloe Khan bất chấp mọi lời chỉ trích vì bộ ngực quá khổ...\\r\\n Sao truyền hình thực tế nổi tiếng nước Anh Chloe Khan liên tục tung lên trang cá nhân những hình ảnh nóng bỏng Chloe Khan bất chấp mọi lời chỉ trích vì bộ ngực quá khổ - cô vẫn tự tin khoe thân hình \\\"khác thường\\\" trong những bức hình tự sướng\\\" \",\n" +
            "        \"date\" : \"2016-11-13, 17:55:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/ch5-1479007030351-crop-1479007087483.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Người đẹp siêu vòng một liên tục khoe thân\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/nguoi-dep-sieu-vong-mot-lien-tuc-khoe-than-2016111310231562.htm\"\n" +
            "      },\n" +
            "      \"52bf69459554f1aaed8b6456f787f39e50d8094a\" : {\n" +
            "        \"code\" : \"52bf69459554f1aaed8b6456f787f39e50d8094a\",\n" +
            "        \"content\" : \"\\nDân trí Một chiếc xe tải chở cam bị lật đổ trên tuyến đường cao tốc thuộc khu vực nông thôn ở tỉnh Giang Tây. Người dân sống xung quanh đó nhanh chóng chạy tới \\\"hôi của\\\" trong khi tài xế lái xe chỉ biết ôm mặt khóc giữa đường vì bất lực.\\r\\nNgười dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đườngChiếc xe tải đang lưu thông trên một tuyến đường cao tốc ở Nghi Xuân, Giang Tây, Trung Quốc, thì gặp nạn và bị lật đổ. Vụ tai nạn khiến toàn bộ số cam trên xe cũng đổ xuống đường.Chứng kiến cảnh tượng trên, người dân sinh sống xung quanh đó đổ xô tới hiện trường để hôi của. Một vài người đàn ông còn mang theo những túi bao tải to \",\n" +
            "        \"date\" : \"2016-11-13, 15:15:50+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/c05a76d21c/2016/11/13/img20161113135708875-20a47.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"Người dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/nguoi-dan-lao-den-hoi-cua-tai-xe-cho-xe-cam-om-mat-khoc-giua-duong-2016111315151166.htm\"\n" +
            "      },\n" +
            "      \"bca87d293300281efd8694f991b348dbec99a924\" : {\n" +
            "        \"code\" : \"bca87d293300281efd8694f991b348dbec99a924\",\n" +
            "        \"content\" : \"\\r\\n    Sau khi chia tay người chồng kém 6 tuổi và khoảng thời gian khó khăn trên đất Mỹ, nữ diễn viên Minh Thư vẫn giữ được tinh thần lạc quan và nhan sắc đáng ngưỡng mộ.\\n Diễn viên Minh Thư nổi danh từ sau vai Hạnh trong bộ phim “Gái nhảy. Hình tượng một cô gái khôn ngoan, \",\n" +
            "        \"date\" : \"2016-11-13, 18:28:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161107110700-dsc-6555ava-1478944877391.JPG\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Hình ảnh nóng bỏng mắt của Minh Thư 'Gái nhảy'\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/hinh-anh-nong-bong-mat-cua-minh-thu-gai-nhay-2016111217040928.htm\"\n" +
            "      },\n" +
            "      \"e1c757c270abb8ec0ab37495af7a564db627ea6f\" : {\n" +
            "        \"code\" : \"e1c757c270abb8ec0ab37495af7a564db627ea6f\",\n" +
            "        \"content\" : \"\\nDân trí Nhằm bảo tồn và phát huy những giá trị độc đáo của văn hóa dân tộc Cơtu, nhiều đơn vị trường học ở huyện miền núi Tây Giang (Quảng Nam) đã bắt đầu đưa múa trống chiêng rồi múa tung tung za zá vào trường học.\\r\\nBao đời nay, điệu múa tung tung za zá được xem là một trong những nét văn hóa đặc trưng của đồng bào Cơtu vùng cao Quảng Nam. “Vũ điệu dâng trời” này đã trải qua bao thăng trầm của thời gian và vẫn mang trong mình nét quyến rũ, huyền bí của con người và thiên nhiên Nam Trường Sơn. Ngày xưa, người Cơtu từ già cho đến trẻ ai cũng biết múa vũ điệu này.Theo thời gian và tác động của cuộc sống hiện đại, vũ điệu tung tung za zá dần mai một. \",\n" +
            "        \"date\" : \"2016-11-13, 16:59:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/photo-0-1479032586855.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giáo dục - Khuyến học \",\n" +
            "        \"title\" : \"Quảng Nam: Đưa điệu múa Cơtu vào trường học\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giao-duc-khuyen-hoc/quang-nam-dua-dieu-mua-cotu-vao-truong-hoc-20161113170107383.htm\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"Giải trí\" : {\n" +
            "      \"4e308c4d53516d91bddea2eeafbf766b44b11bf6\" : {\n" +
            "        \"code\" : \"4e308c4d53516d91bddea2eeafbf766b44b11bf6\",\n" +
            "        \"content\" : \"\\nDân trí Nhiều siêu mẫu đình đám \\\"ép\\\" mình theo một chế độ tập luyện hàng ngày rất nghiêm túc và có phần \\\"khắc nghiệt\\\" để sở hữu cơ bụng săn chắc ấn tượng.\\n Siêu mẫu hai con người Hà Lan Doutzen Kroes tới phòng tập mỗi ngày, cô thường tập nhảy dây, chạy và boxing. Người đẹp chú trọng chế độ ăn uống không quá nhiều tinh bột, đường, cô thích ăn sữa chua, trái cây và các thanh vitamins tổng hợp để giữ dáng Candice Swanepoel tập thể dục với huấn luyện viên riêng. Các bài tập boxing và bài tập cho cơ bụng được cô chú trọng nhiều. Người đẹp thường tới phòng tập 3 lần/tuần. \",\n" +
            "        \"date\" : \"2016-11-13, 17:41:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/do9-1479034088600.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Những chân dài sở hữu cơ bụng săn chắc đáng kinh ngạc\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/nhung-chan-dai-so-huu-co-bung-san-chac-dang-kinh-ngac-20161113175033584.htm\"\n" +
            "      },\n" +
            "      \"7c13bd3214e8c8facf69d93ffbed3d1e49e0f56a\" : {\n" +
            "        \"code\" : \"7c13bd3214e8c8facf69d93ffbed3d1e49e0f56a\",\n" +
            "        \"content\" : \"\\nDân trí Mối quan hệ giữa ngôi sao gạo cội Jon Voight và con gái Angelina Jolie không thực sự suôn sẻ những năm gần đây.\\n Nam diễn viên Jon Voight dự công chiếu phim Fantastic Beasts and Where To Find Them tại New York cách đây ít ngày, ông hiển nhiên được hỏi về vụ ly dị gây xôn xao của con gái Angelina Jolie \\\"Cảm ơn mọi người đã quan tâm, tôi mong rằng mọi việc sẽ ổn thỏa,\\\" nam diễn viên 77 tuổi thể hiện sự hi vọng của ông trong mối quan hệ giữa con gái và chồng cũ Brad Pitt Mối quan hệ giữa ngôi sao gạo cội Jon Voight và con gái Angelina Jolie không thực sự suôn sẻ những năm gần đây sau khi Jon chia sẻ trên truyền hình rằng ông nghĩ con gái có vấn đề thần kinh. \",\n" +
            "        \"date\" : \"2016-11-13, 17:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/jo3-1479032072067-crop-1479032115560.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Bố Angelina Jolie lần đầu lên tiếng về vụ ly dị của con gái\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/bo-angelina-jolie-lan-dau-len-tieng-ve-vu-ly-di-cua-con-gai-20161113171754384.htm\"\n" +
            "      },\n" +
            "      \"0b216642da70a945f6b28f421a95d41c35c548b8\" : {\n" +
            "        \"code\" : \"0b216642da70a945f6b28f421a95d41c35c548b8\",\n" +
            "        \"content\" : \"\\r\\n    Việc Donald Trump, một người thiếu kinh nghiệm chính trị, đắc cử Tổng thống Mỹ đã khiến nhiều người lo sợ. Tuy nhiên, có một số lý do cho thấy ông Trump có thể là một Tổng thống Mỹ thành công.\\nBiết mình biết ngườiThứ nhất, để đi tới chiếc ghế Tổng thống, với tư cách là một doanh nhân, thay vì chính trị gia, Trump chắc hẳn là một người thông minh. Thứ hai, ông biết rõ một điều mà chúng ta ai cũng rõ. Đa phần những gì ông hứa nếu đắc cử chả có ý nghĩa gì, và đó là lý do ông có thể không thực hiện lời hứa, giáo sư Richard W. Painter thuộc trường luật Đại học Minnesota bình luận trên báo NY Times .Ngoài ra, theo nhà bình luận Sandhu của Lancashire Eveningpost, khi làm Tổng thống, \",\n" +
            "        \"date\" : \"2016-11-13, 21:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161112170625-1112g1-1479030766177.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Vì sao Trump sẽ là Tổng thống Mỹ thành công?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/vi-sao-trump-se-la-tong-thong-my-thanh-cong-20161113165841355.htm\"\n" +
            "      },\n" +
            "      \"16a2bb3c0b9df104661967bd20f112700045bb3a\" : {\n" +
            "        \"code\" : \"16a2bb3c0b9df104661967bd20f112700045bb3a\",\n" +
            "        \"content\" : \"\\r\\n    Những bài tập trận tiêu diệt khủng bố, thả dù thiết bị quân sự hay điều khiển máy bay không người lái đều được thực hiện thành công bởi binh lính của Nga, Serbia và Belarus trong cuộc tập trận “Anh em Slavic 2016” tại Serbia.\\nCuộc tập trận \\\"Anh em Slavic 2016\\\" đã kết thúc tốt đẹp vào hôm 9-11, sau khi có sự tham gia của khoảng 250 binh lính Nga, bao gồm lực lượng đặc biệt và lính dù, 50 quân nhân Belarus cùng với 450 binh sĩ từ lực lượng vũ trang Serbia.\",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/5827a1edc3618870528b458b-1-1479032295463.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Binh lính Nga - Serbia - Belarus tập trận '\\\"Anh em Slavic 2016\\\"\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/binh-linh-nga-serbia-belarus-tap-tran-anh-em-slavic-2016-20161113173102834.htm\"\n" +
            "      },\n" +
            "      \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\" : {\n" +
            "        \"code\" : \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\",\n" +
            "        \"content\" : \"\\r\\n    Các tàu chiến của Nga được triển khai trong vùng biển Syria đã bắt đầu tấn công các vị trí của nhóm khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại cực lớn cho phiến quân.\\nAl-Jafa, một chuyên gia về các vấn đề chiến lược hôm 12-11 tiết lộ, tên lửa hành trình Kalibr của Nga đã tấn công thành trì của những kẻ khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại nặng nề cho phiến quân. \",\n" +
            "        \"date\" : \"2016-11-13, 19:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/hai-quan-nga-bat-dau-tan-cong-aleppo-1479031687081.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Tàu chiến Nga bắt đầu tấn công căn cứ của phiến quân ở Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/tau-chien-nga-bat-dau-tan-cong-can-cu-cua-phien-quan-o-aleppo-20161113171219445.htm\"\n" +
            "      },\n" +
            "      \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\" : {\n" +
            "        \"code\" : \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\",\n" +
            "        \"content\" : \"\\nDân trí Tháng Mười chưa cười đã tối, ở Trung Âu là thời khắc đổi sang giờ mùa đông, 3h sáng chủ nhật vặn ngược đồng hồ thành 2h. Xa Hà Nội cả vạn cây số, thời khắc này ca khúc “Có phải em mùa Thu Hà Nội\\\" sao mà quá gợi nhớ gợi thương...\\r\\nMới “tháng Tám mùa Thu lá khởi vàng chưa nhỉ”, thoắt cái đã qua Quốc khánh 2/9 và rồi tháng 10 ập tới... cây cối nơi nơi đồng loạt gọi nhau trút lá, trải thảm vàng ... mời gọi!Rất nhanh đồng loạt lá đổi màu chiều còn xanh, sáng hôm sau đã ngả vàng rực rỡ. \",\n" +
            "        \"date\" : \"2016-11-13, 15:50:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/tn-lavang1-1479024789428.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ostrava mùa Xanh - Vàng - Đỏ - Trắng\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ostrava-mua-xanh-vang-do-trang-20161113155102516.htm\"\n" +
            "      },\n" +
            "      \"878787b6cb7a34cb3328404039104572df8d9691\" : {\n" +
            "        \"code\" : \"878787b6cb7a34cb3328404039104572df8d9691\",\n" +
            "        \"content\" : \"\\nDân trí Chloe Khan bất chấp mọi lời chỉ trích vì bộ ngực quá khổ...\\r\\n Sao truyền hình thực tế nổi tiếng nước Anh Chloe Khan liên tục tung lên trang cá nhân những hình ảnh nóng bỏng Chloe Khan bất chấp mọi lời chỉ trích vì bộ ngực quá khổ - cô vẫn tự tin khoe thân hình \\\"khác thường\\\" trong những bức hình tự sướng\\\" \",\n" +
            "        \"date\" : \"2016-11-13, 17:55:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/ch5-1479007030351-crop-1479007087483.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Người đẹp siêu vòng một liên tục khoe thân\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/nguoi-dep-sieu-vong-mot-lien-tuc-khoe-than-2016111310231562.htm\"\n" +
            "      },\n" +
            "      \"52bf69459554f1aaed8b6456f787f39e50d8094a\" : {\n" +
            "        \"code\" : \"52bf69459554f1aaed8b6456f787f39e50d8094a\",\n" +
            "        \"content\" : \"\\nDân trí Một chiếc xe tải chở cam bị lật đổ trên tuyến đường cao tốc thuộc khu vực nông thôn ở tỉnh Giang Tây. Người dân sống xung quanh đó nhanh chóng chạy tới \\\"hôi của\\\" trong khi tài xế lái xe chỉ biết ôm mặt khóc giữa đường vì bất lực.\\r\\nNgười dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đườngChiếc xe tải đang lưu thông trên một tuyến đường cao tốc ở Nghi Xuân, Giang Tây, Trung Quốc, thì gặp nạn và bị lật đổ. Vụ tai nạn khiến toàn bộ số cam trên xe cũng đổ xuống đường.Chứng kiến cảnh tượng trên, người dân sinh sống xung quanh đó đổ xô tới hiện trường để hôi của. Một vài người đàn ông còn mang theo những túi bao tải to \",\n" +
            "        \"date\" : \"2016-11-13, 15:15:50+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/c05a76d21c/2016/11/13/img20161113135708875-20a47.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"Người dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/nguoi-dan-lao-den-hoi-cua-tai-xe-cho-xe-cam-om-mat-khoc-giua-duong-2016111315151166.htm\"\n" +
            "      },\n" +
            "      \"bca87d293300281efd8694f991b348dbec99a924\" : {\n" +
            "        \"code\" : \"bca87d293300281efd8694f991b348dbec99a924\",\n" +
            "        \"content\" : \"\\r\\n    Sau khi chia tay người chồng kém 6 tuổi và khoảng thời gian khó khăn trên đất Mỹ, nữ diễn viên Minh Thư vẫn giữ được tinh thần lạc quan và nhan sắc đáng ngưỡng mộ.\\n Diễn viên Minh Thư nổi danh từ sau vai Hạnh trong bộ phim “Gái nhảy. Hình tượng một cô gái khôn ngoan, \",\n" +
            "        \"date\" : \"2016-11-13, 18:28:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161107110700-dsc-6555ava-1478944877391.JPG\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Hình ảnh nóng bỏng mắt của Minh Thư 'Gái nhảy'\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/hinh-anh-nong-bong-mat-cua-minh-thu-gai-nhay-2016111217040928.htm\"\n" +
            "      },\n" +
            "      \"ef1704412d269b1b0d35e7544876f0d222527025\" : {\n" +
            "        \"code\" : \"ef1704412d269b1b0d35e7544876f0d222527025\",\n" +
            "        \"content\" : \"\\nDân trí Tối ngày 11/11, tại tiểu bang Texas, Mỹ, Ngọc Quyên và Dương Mỹ Linh xuất hiện với vai trò người mẫu trình diễn những bộ trang phục vô cùng lộng lẫy trong một cuộc thi tại đây.\\r\\nSau khi kết hôn, Ngọc Quyên hiện đang định cư tại California (Mỹ) cùng gia đình. Thỉnh thoảng, Ngọc Quyên vẫn chia sẻ hình ảnh nhóc tì đáng yêu của mình trên trang cá nhân.Vì con còn nhỏ nên vợ chồng Ngọc Quyên thay phiên nhau chăm con để cả hai cùng có cơ hội tranh thủ đi du lịch, xả stress và nếu có chương trình phù hợp, Ngọc Quyên vẫn nhận tham gia những chương trình nghệ thuật do cộng đồng người Việt tại Mỹ tổ chức để đỡ nhớ nghề. \",\n" +
            "        \"date\" : \"2016-11-13, 16:14:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/487bd2df65/2016/11/13/ngoc-quyen-1478997458583.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Giải trí\",\n" +
            "        \"title\" : \"Ngọc Quyên hội ngộ bạn gái Bằng Kiều tự tin sải bước catwalk tại Mỹ\",\n" +
            "        \"url\" : \"http://dantri.com.vn/giai-tri/ngoc-quyen-hoi-ngo-ban-gai-bang-kieu-tu-tin-sai-buoc-catwalk-tai-my-20161113092147102.htm\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"Kinh doanh\" : {\n" +
            "      \"706efcad42e42c1cd90f495134fa2fa6885e707e\" : {\n" +
            "        \"code\" : \"706efcad42e42c1cd90f495134fa2fa6885e707e\",\n" +
            "        \"content\" : \"\\nDân trí Mặc dù ở Hà Nội, đã có gần 80 bãi gửi xe với giá được niêm yết theo quy định, nhưng dường như chưa phục vụ đủ hoặc người dân không nắm rõ được vị trí của các bãi gửi xe đó dẫn đến tình trạng phải gửi ở ngoài với giá cao, thậm chí có nơi chém đẹp 50.000đ đồng/xe.\\nNếu không tìm hiểu trước về các điểm gửi xe quanh phố đi bộ quanh Hồ Gươm sẽ không thể phân biệt được các điểm gửi xe theo quy định và các điểm gửi xe tự phát. Các điểm gửi xe theo quy định đều có cắm bảng niêm yết giá nhưng nếu đi vào lúc trời tối thì sẽ rất khó để quan sát.Nếu gửi xe ban ngày tại các bãi xe theo quy định thì xe đạp là 2.000 đồng/lượt, xe máy là 3.000 đồng/lượt và buổi tối một lượt gửi xe đạp là 3.000 đồng và xe máy là 5.000 đồng/lượt.Còn theo tìm hiểu của PV Dân Trí, nếu gửi xe ở các bãi tự phát ở đoạn Hàng Bạc giao Mã Mây, Tạ Hiện,...thì mức giá có thể gấp đến 6 lần hoặc hơn. Cụ thể, ban ngày xe máy 20.000 đồng/lượt và ban đêm xe máy là 30.000 đồng/lượt, có nơi lên đến 50.000 đồng/lượt.Duy Khánh, một sinh viên đang học tại Hà Nội, cho biết: \",\n" +
            "        \"date\" : \"2016-11-13, 20:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/photo-1-1479028904512.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Kinh doanh\",\n" +
            "        \"title\" : \"Hà Nội: Gửi xe cạnh phố đi bộ bị chém đẹp 50.000 đồng/xe\",\n" +
            "        \"url\" : \"http://dantri.com.vn/kinh-doanh/ha-noi-gui-xe-canh-pho-di-bo-bi-chem-dep-50000-dong-xe-20161113162606616.htm\"\n" +
            "      },\n" +
            "      \"0b216642da70a945f6b28f421a95d41c35c548b8\" : {\n" +
            "        \"code\" : \"0b216642da70a945f6b28f421a95d41c35c548b8\",\n" +
            "        \"content\" : \"\\r\\n    Việc Donald Trump, một người thiếu kinh nghiệm chính trị, đắc cử Tổng thống Mỹ đã khiến nhiều người lo sợ. Tuy nhiên, có một số lý do cho thấy ông Trump có thể là một Tổng thống Mỹ thành công.\\nBiết mình biết ngườiThứ nhất, để đi tới chiếc ghế Tổng thống, với tư cách là một doanh nhân, thay vì chính trị gia, Trump chắc hẳn là một người thông minh. Thứ hai, ông biết rõ một điều mà chúng ta ai cũng rõ. Đa phần những gì ông hứa nếu đắc cử chả có ý nghĩa gì, và đó là lý do ông có thể không thực hiện lời hứa, giáo sư Richard W. Painter thuộc trường luật Đại học Minnesota bình luận trên báo NY Times .Ngoài ra, theo nhà bình luận Sandhu của Lancashire Eveningpost, khi làm Tổng thống, \",\n" +
            "        \"date\" : \"2016-11-13, 21:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161112170625-1112g1-1479030766177.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Vì sao Trump sẽ là Tổng thống Mỹ thành công?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/vi-sao-trump-se-la-tong-thong-my-thanh-cong-20161113165841355.htm\"\n" +
            "      },\n" +
            "      \"16a2bb3c0b9df104661967bd20f112700045bb3a\" : {\n" +
            "        \"code\" : \"16a2bb3c0b9df104661967bd20f112700045bb3a\",\n" +
            "        \"content\" : \"\\r\\n    Những bài tập trận tiêu diệt khủng bố, thả dù thiết bị quân sự hay điều khiển máy bay không người lái đều được thực hiện thành công bởi binh lính của Nga, Serbia và Belarus trong cuộc tập trận “Anh em Slavic 2016” tại Serbia.\\nCuộc tập trận \\\"Anh em Slavic 2016\\\" đã kết thúc tốt đẹp vào hôm 9-11, sau khi có sự tham gia của khoảng 250 binh lính Nga, bao gồm lực lượng đặc biệt và lính dù, 50 quân nhân Belarus cùng với 450 binh sĩ từ lực lượng vũ trang Serbia.\",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/5827a1edc3618870528b458b-1-1479032295463.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Binh lính Nga - Serbia - Belarus tập trận '\\\"Anh em Slavic 2016\\\"\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/binh-linh-nga-serbia-belarus-tap-tran-anh-em-slavic-2016-20161113173102834.htm\"\n" +
            "      },\n" +
            "      \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\" : {\n" +
            "        \"code\" : \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\",\n" +
            "        \"content\" : \"\\r\\n    Các tàu chiến của Nga được triển khai trong vùng biển Syria đã bắt đầu tấn công các vị trí của nhóm khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại cực lớn cho phiến quân.\\nAl-Jafa, một chuyên gia về các vấn đề chiến lược hôm 12-11 tiết lộ, tên lửa hành trình Kalibr của Nga đã tấn công thành trì của những kẻ khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại nặng nề cho phiến quân. \",\n" +
            "        \"date\" : \"2016-11-13, 19:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/hai-quan-nga-bat-dau-tan-cong-aleppo-1479031687081.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Tàu chiến Nga bắt đầu tấn công căn cứ của phiến quân ở Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/tau-chien-nga-bat-dau-tan-cong-can-cu-cua-phien-quan-o-aleppo-20161113171219445.htm\"\n" +
            "      },\n" +
            "      \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\" : {\n" +
            "        \"code\" : \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\",\n" +
            "        \"content\" : \"\\nDân trí Tháng Mười chưa cười đã tối, ở Trung Âu là thời khắc đổi sang giờ mùa đông, 3h sáng chủ nhật vặn ngược đồng hồ thành 2h. Xa Hà Nội cả vạn cây số, thời khắc này ca khúc “Có phải em mùa Thu Hà Nội\\\" sao mà quá gợi nhớ gợi thương...\\r\\nMới “tháng Tám mùa Thu lá khởi vàng chưa nhỉ”, thoắt cái đã qua Quốc khánh 2/9 và rồi tháng 10 ập tới... cây cối nơi nơi đồng loạt gọi nhau trút lá, trải thảm vàng ... mời gọi!Rất nhanh đồng loạt lá đổi màu chiều còn xanh, sáng hôm sau đã ngả vàng rực rỡ. \",\n" +
            "        \"date\" : \"2016-11-13, 15:50:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/tn-lavang1-1479024789428.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ostrava mùa Xanh - Vàng - Đỏ - Trắng\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ostrava-mua-xanh-vang-do-trang-20161113155102516.htm\"\n" +
            "      },\n" +
            "      \"52bf69459554f1aaed8b6456f787f39e50d8094a\" : {\n" +
            "        \"code\" : \"52bf69459554f1aaed8b6456f787f39e50d8094a\",\n" +
            "        \"content\" : \"\\nDân trí Một chiếc xe tải chở cam bị lật đổ trên tuyến đường cao tốc thuộc khu vực nông thôn ở tỉnh Giang Tây. Người dân sống xung quanh đó nhanh chóng chạy tới \\\"hôi của\\\" trong khi tài xế lái xe chỉ biết ôm mặt khóc giữa đường vì bất lực.\\r\\nNgười dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đườngChiếc xe tải đang lưu thông trên một tuyến đường cao tốc ở Nghi Xuân, Giang Tây, Trung Quốc, thì gặp nạn và bị lật đổ. Vụ tai nạn khiến toàn bộ số cam trên xe cũng đổ xuống đường.Chứng kiến cảnh tượng trên, người dân sinh sống xung quanh đó đổ xô tới hiện trường để hôi của. Một vài người đàn ông còn mang theo những túi bao tải to \",\n" +
            "        \"date\" : \"2016-11-13, 15:15:50+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/c05a76d21c/2016/11/13/img20161113135708875-20a47.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"Người dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/nguoi-dan-lao-den-hoi-cua-tai-xe-cho-xe-cam-om-mat-khoc-giua-duong-2016111315151166.htm\"\n" +
            "      },\n" +
            "      \"a167fff9edf82721d2f696e436998b884ad7e77d\" : {\n" +
            "        \"code\" : \"a167fff9edf82721d2f696e436998b884ad7e77d\",\n" +
            "        \"content\" : \"\\nDân trí Chuyên gia tài chính ngân hàng TS Nguyễn Trí Hiếu nhận định số phận của Hiệp định Đối tác xuyên Thái Bình Dương (TPP) là rất mỏng manh. Theo ông, có thể hiệp định sẽ không được thông qua trong nhiệm kỳ Tổng thống Obama và thảo luận lại trong thời gian dài.\\nTS Nguyễn Trí Hiếu nhận định như trên tại phiên họp chuyên đề “Kiều bào tham gia đầu tư phát triển thương mại, dịch vụ của TPHCM” trong khuôn khổ Hội nghị người Việt Nam ở nước ngoài toàn thế giới.TS Nguyễn Trí Hiếu cho biết sự đóng góp của kiều bào vào nền kinh tế TPHCM là rất đáng kể. Trong năm nay dự kiến kiều hối về TP khoảng 6 tỷ đô la, chiếm một nửa tổng số kiều hối gửi về nước. \",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/e30bff13be/2016/11/13/ongnguyentrihieu-1479024176031.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Kinh doanh\",\n" +
            "        \"title\" : \"TPHCM: Hãy thực tế và đừng quá kỳ vọng vào TPP\",\n" +
            "        \"url\" : \"http://dantri.com.vn/kinh-doanh/tphcm-hay-thuc-te-va-dung-qua-ky-vong-vao-tpp-20161113161202028.htm\"\n" +
            "      },\n" +
            "      \"c883cedeec08b5d159f9b42507d5996011396144\" : {\n" +
            "        \"code\" : \"c883cedeec08b5d159f9b42507d5996011396144\",\n" +
            "        \"content\" : \"\\nDân trí Tại kì quay thưởng số 51, ngày 13/11, Vietlott đã xác định có một người trúng thưởng giải Jackpot với dãy số 51, 11-23-25-34-36-45, có giá trị 71 tỷ đồng. Bên cạnh đó, Vietlott còn xác định có 128 giải nhất, 5554 giải nhì và 87443 giải ba.\\nTối 13/11, đại diện Công ty Xổ số điện toán Việt Nam (Vietlott) xác nhận với Dân trí về người trúng thưởng giải Jackpot thứ 3 với số tiền 71 tỷ đồng. Theo đó, tại kì quay thưởng số 51 xổ số Mega 6/45, Vietlott đã xác định có một người trúng thưởng giải Jackpot với dãy số 51, có giá trị 71 tỷ đồng. Chuỗi số trúng giải \\\"khủng\\\" lần thứ 3 này là 11-23-25-34-36-45.Bên cạnh đó, tại kỳ quay thưởng lần thứ 51 này, ngoài giải đặc biệt, Vietlott còn xác định có 128 giải nhất (10 triệu đồng mỗi giải), 5.554 giải nhì (300.000 đồng) và 87.443 giải ba (30.000 đồng).\",\n" +
            "        \"date\" : \"2016-11-13, 20:16:22+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/33f85ba0cc/2016/11/13/screen-shot-2016-11-13-at-7-53-37-ch-1479042788698.png\",\n" +
            "        \"labels\" : \"Dân trí/Kinh doanh\",\n" +
            "        \"title\" : \"Thêm người thứ 3 \\\"siêu giàu\\\" khi trúng 71 tỷ đồng xổ số điện toán\",\n" +
            "        \"url\" : \"http://dantri.com.vn/kinh-doanh/them-nguoi-thu-3-sieu-giau-khi-trung-71-ty-dong-xo-so-dien-toan-20161113201706418.htm\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"Thế giới\" : {\n" +
            "      \"0b216642da70a945f6b28f421a95d41c35c548b8\" : {\n" +
            "        \"code\" : \"0b216642da70a945f6b28f421a95d41c35c548b8\",\n" +
            "        \"content\" : \"\\r\\n    Việc Donald Trump, một người thiếu kinh nghiệm chính trị, đắc cử Tổng thống Mỹ đã khiến nhiều người lo sợ. Tuy nhiên, có một số lý do cho thấy ông Trump có thể là một Tổng thống Mỹ thành công.\\nBiết mình biết ngườiThứ nhất, để đi tới chiếc ghế Tổng thống, với tư cách là một doanh nhân, thay vì chính trị gia, Trump chắc hẳn là một người thông minh. Thứ hai, ông biết rõ một điều mà chúng ta ai cũng rõ. Đa phần những gì ông hứa nếu đắc cử chả có ý nghĩa gì, và đó là lý do ông có thể không thực hiện lời hứa, giáo sư Richard W. Painter thuộc trường luật Đại học Minnesota bình luận trên báo NY Times .Ngoài ra, theo nhà bình luận Sandhu của Lancashire Eveningpost, khi làm Tổng thống, \",\n" +
            "        \"date\" : \"2016-11-13, 21:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161112170625-1112g1-1479030766177.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Vì sao Trump sẽ là Tổng thống Mỹ thành công?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/vi-sao-trump-se-la-tong-thong-my-thanh-cong-20161113165841355.htm\"\n" +
            "      },\n" +
            "      \"16a2bb3c0b9df104661967bd20f112700045bb3a\" : {\n" +
            "        \"code\" : \"16a2bb3c0b9df104661967bd20f112700045bb3a\",\n" +
            "        \"content\" : \"\\r\\n    Những bài tập trận tiêu diệt khủng bố, thả dù thiết bị quân sự hay điều khiển máy bay không người lái đều được thực hiện thành công bởi binh lính của Nga, Serbia và Belarus trong cuộc tập trận “Anh em Slavic 2016” tại Serbia.\\nCuộc tập trận \\\"Anh em Slavic 2016\\\" đã kết thúc tốt đẹp vào hôm 9-11, sau khi có sự tham gia của khoảng 250 binh lính Nga, bao gồm lực lượng đặc biệt và lính dù, 50 quân nhân Belarus cùng với 450 binh sĩ từ lực lượng vũ trang Serbia.\",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/5827a1edc3618870528b458b-1-1479032295463.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Binh lính Nga - Serbia - Belarus tập trận '\\\"Anh em Slavic 2016\\\"\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/binh-linh-nga-serbia-belarus-tap-tran-anh-em-slavic-2016-20161113173102834.htm\"\n" +
            "      },\n" +
            "      \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\" : {\n" +
            "        \"code\" : \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\",\n" +
            "        \"content\" : \"\\r\\n    Các tàu chiến của Nga được triển khai trong vùng biển Syria đã bắt đầu tấn công các vị trí của nhóm khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại cực lớn cho phiến quân.\\nAl-Jafa, một chuyên gia về các vấn đề chiến lược hôm 12-11 tiết lộ, tên lửa hành trình Kalibr của Nga đã tấn công thành trì của những kẻ khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại nặng nề cho phiến quân. \",\n" +
            "        \"date\" : \"2016-11-13, 19:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/hai-quan-nga-bat-dau-tan-cong-aleppo-1479031687081.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Tàu chiến Nga bắt đầu tấn công căn cứ của phiến quân ở Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/tau-chien-nga-bat-dau-tan-cong-can-cu-cua-phien-quan-o-aleppo-20161113171219445.htm\"\n" +
            "      },\n" +
            "      \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\" : {\n" +
            "        \"code\" : \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\",\n" +
            "        \"content\" : \"\\nDân trí Tháng Mười chưa cười đã tối, ở Trung Âu là thời khắc đổi sang giờ mùa đông, 3h sáng chủ nhật vặn ngược đồng hồ thành 2h. Xa Hà Nội cả vạn cây số, thời khắc này ca khúc “Có phải em mùa Thu Hà Nội\\\" sao mà quá gợi nhớ gợi thương...\\r\\nMới “tháng Tám mùa Thu lá khởi vàng chưa nhỉ”, thoắt cái đã qua Quốc khánh 2/9 và rồi tháng 10 ập tới... cây cối nơi nơi đồng loạt gọi nhau trút lá, trải thảm vàng ... mời gọi!Rất nhanh đồng loạt lá đổi màu chiều còn xanh, sáng hôm sau đã ngả vàng rực rỡ. \",\n" +
            "        \"date\" : \"2016-11-13, 15:50:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/tn-lavang1-1479024789428.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ostrava mùa Xanh - Vàng - Đỏ - Trắng\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ostrava-mua-xanh-vang-do-trang-20161113155102516.htm\"\n" +
            "      },\n" +
            "      \"2fc22d0c90c9faf69c3a1576d5ba06ce633fa4b6\" : {\n" +
            "        \"code\" : \"2fc22d0c90c9faf69c3a1576d5ba06ce633fa4b6\",\n" +
            "        \"content\" : \"\\r\\n    Hãng thông tấn Yonhap dẫn nhiều nguồn tin từ cơ quan công tố Hàn Quốc ngày 13/11 cho biết Tổng thống Park Geun-hye có thể sẽ bị thẩm vấn, sớm nhất là vào cuối tuần sau, về vai trò của bà trong vụ bê bối tham nhũng và can thiệp vào công việc nhà nước liên quan đến người bạn thân lâu năm của bà.\\nYonhap dẫn lời nhiều công tố viên cho biết họ đang xem xét mở cuộc điều tra bà Park vào khoảng ngày 20/11 sau khi hết thời hạn tạm giữ bà Choi Soon-sil –\",\n" +
            "        \"date\" : \"2016-11-13, 16:30:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/park-geunhye-afp-1479029362361.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Công tố viên Hàn Quốc sẽ thẩm vấn nữ Tổng thống Park Geun-hye\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/cong-to-vien-han-quoc-se-tham-van-nu-tong-thong-park-geun-hye-20161113163402201.htm\"\n" +
            "      },\n" +
            "      \"41b16f48cffff02b0441f666e7ecb0ff173c6f0c\" : {\n" +
            "        \"code\" : \"41b16f48cffff02b0441f666e7ecb0ff173c6f0c\",\n" +
            "        \"content\" : \"\\nDân trí Tổng thống thứ 45 sắp tới của Mỹ thực sự rất giàu có. Rõ ràng, ông sẽ là tổng thống giàu nhất trong lịch sử Mỹ. Ông Trump hiện quản lý hơn 500 công ty trong nhiều lĩnh vực và sở hữu khối tài sản lên tới 3,7 tỷ USD.\\n Đại gia đình nhà ông Trump (Ảnh: Getty)“Một cái hay của tôi là tôi rất giàu”, Donald Trump từng tuyên bố vào năm 2011 khi công khai bình luận về khả năng tranh cử tổng thống.Ông Trump hiện là chủ tịch và giám đốc điều hành Trump Organization, một tập đoàn sở hữu các công ty khắp thế giới trong nhiều lĩnh vực như bất động sản, khách sạn, sân golf, sòng bạc... \",\n" +
            "        \"date\" : \"2016-11-13, 19:09:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/2-1479038191313.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ông Trump sẽ \\\"từ bỏ\\\" 500 công ty thế nào khi nhậm chức tổng thống?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ong-trump-se-tu-bo-500-cong-ty-the-nao-khi-nham-chuc-tong-thong-20161113191113557.htm\"\n" +
            "      },\n" +
            "      \"5a4cf5e5e2fa87498605782e1f01078b7a7877af\" : {\n" +
            "        \"code\" : \"5a4cf5e5e2fa87498605782e1f01078b7a7877af\",\n" +
            "        \"content\" : \"\\r\\n    IS sẵn sàng sử dụng vũ khí hóa học, dùng dân thường làm lá chắn sống để cố thủ, làm chiến dịch giải phóng hoàn toàn Mosul bị chậm lại.\\nNguy cơ sử dụng vũ khí hóa họcNgười phát ngôn lực lượng chống khủng bố Sabah an Numairi lo ngại rằng, IS còn chia thành nhiều nhóm nhỏ sử dụng mạng lưới đường hầm rộng lớn và đường phố chật hẹp để đặt bom, gài bẫy.Hiện có khoảng 1.000 tay súng IS đang cố thủ tại Mosul. Có những báo cáo cho thấy IS sẵn sàng sử dụng các loại vũ khí hóa học để cố thủ.Bộ Quốc phòng Iraq cho biết, trong những ngày tới các lực lượng tham gia chống khủng bố IS, với sự hậu thuẫn từ trên không của liên quân quốc tế sẽ phối hợp tác chiến từ nhiều ngả, tổng lực tấn công để giải phóng Mosul.Trước tình hình chiến sự diễn biến vô cùng ác liệt, ngày 12/11, Liên Hợp Quốc lo ngại sâu sắc về số phận của hàng trăm nghìn dân thường đang mắc kẹt tại Mosul.\",\n" +
            "        \"date\" : \"2016-11-13, 20:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/giai-phong-mosul-cham-is-giu-dan-thuong-lam-la-chan-131153131-1479034663117.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Chiến dịch Mosul giảm tốc, \\\"Hổ Syria\\\" lệnh giải phóng Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/chien-dich-mosul-giam-toc-ho-syria-lenh-giai-phong-aleppo-20161113180815246.htm\"\n" +
            "      },\n" +
            "      \"7639b9a11e7e876ae8877784ef1f090667f8ca43\" : {\n" +
            "        \"code\" : \"7639b9a11e7e876ae8877784ef1f090667f8ca43\",\n" +
            "        \"content\" : \"\\nDân trí Các máy bay trên tàu sân bay Đô đốc Kuznetsov, thuộc một đội tàu chiến của Nga được triển khai tới gần bờ biển Syria mới đây, đã bắt đầu tiến hành các chuyến bay nhằm tương tác với một sân bay ven biển, chỉ huy của tàu sân bay này cho biết.\\n Một máy bay chiến đấu Sukhoi Su-27K cất cánh từ tàu sân bay Đô đốc Kuznetsov (Ảnh: Sputnik)“Các chuyến bay đang được thực hiện từ khoang tàu sân bay. Sự tương tác với một sân bay ven biển đang được tiến hành”, Sergey Artamonov, chỉ huy tàu sân bay Đô đốc Kuznetsov cho biết với kênh truyền hình Rossiya của Nga.Theo quan chức trên, các chuyến bay đã được tiến hành trên cơ sở thường ngày trong 4 ngày vừa qua.Đội tàu chiến của Hạm đội phương Bắc thuộc Hải quân Nga, gồm tàu sân bay Đô đốc Kuznetsov, tuần dương hạm Pyotr Velikiy, 2 tàu chống ngầm loại lớn Severomorsk và Phó đô đốc Kulakov, cùng các tàu hỗ trợ, đã tới Địa Trung Hải vào đầu tháng 11.Chỉ huy tàu Pyotr Veliki\",\n" +
            "        \"date\" : \"2016-11-13, 16:15:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/0-1479028410691.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Chiến đấu cơ Nga cất cánh từ tàu sân bay ngoài khơi Syria\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/chien-dau-co-nga-cat-canh-tu-tau-san-bay-ngoai-khoi-syria-20161113161708872.htm\"\n" +
            "      },\n" +
            "      \"ae8b11b807c7179632aaad3e49bae8474f5c18ba\" : {\n" +
            "        \"code\" : \"ae8b11b807c7179632aaad3e49bae8474f5c18ba\",\n" +
            "        \"content\" : \"\\nDân trí Một trận động đất mạnh 7,4 độ Richter đã xảy ra vào nửa đêm hôm nay 13/11 ở khu vực Christchurch, miền trung New Zealand kéo theo sóng thần.\\n Động đất xảy ra ở đông bắc Christchurch. (Ảnh: USSG)Theo Cơ quan khảo sát địa chất Mỹ, trận động đất mạnh 7,4 độ Richter xảy ra vào lúc 23h02 hôm nay 13/11 ở\",\n" +
            "        \"date\" : \"2016-11-13, 20:14:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/ny-1479042793263.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Sóng thần tấn công New Zealand sau động đất mạnh\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/song-than-tan-cong-new-zealand-sau-dong-dat-manh-20161113201632146.htm\"\n" +
            "      },\n" +
            "      \"52bf69459554f1aaed8b6456f787f39e50d8094a\" : {\n" +
            "        \"code\" : \"52bf69459554f1aaed8b6456f787f39e50d8094a\",\n" +
            "        \"content\" : \"\\nDân trí Một chiếc xe tải chở cam bị lật đổ trên tuyến đường cao tốc thuộc khu vực nông thôn ở tỉnh Giang Tây. Người dân sống xung quanh đó nhanh chóng chạy tới \\\"hôi của\\\" trong khi tài xế lái xe chỉ biết ôm mặt khóc giữa đường vì bất lực.\\r\\nNgười dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đườngChiếc xe tải đang lưu thông trên một tuyến đường cao tốc ở Nghi Xuân, Giang Tây, Trung Quốc, thì gặp nạn và bị lật đổ. Vụ tai nạn khiến toàn bộ số cam trên xe cũng đổ xuống đường.Chứng kiến cảnh tượng trên, người dân sinh sống xung quanh đó đổ xô tới hiện trường để hôi của. Một vài người đàn ông còn mang theo những túi bao tải to \",\n" +
            "        \"date\" : \"2016-11-13, 15:15:50+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/c05a76d21c/2016/11/13/img20161113135708875-20a47.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"Người dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/nguoi-dan-lao-den-hoi-cua-tai-xe-cho-xe-cam-om-mat-khoc-giua-duong-2016111315151166.htm\"\n" +
            "      },\n" +
            "      \"b5b754ee0944f79e2f9c85051ff2b1b679abed5e\" : {\n" +
            "        \"code\" : \"b5b754ee0944f79e2f9c85051ff2b1b679abed5e\",\n" +
            "        \"content\" : \"\\r\\n    Ngày 12-11, theo trang tin quân sự Defense Aerospace, máy bay chiến đấu thế hệ thứ 5 F-35B Lightning II thuộc biên chế Thủy quân lục chiến Mỹ đã lần đầu tiên tham gia tập trận chống lại mục tiêu giả lập là máy bay tiêm kích của đối phương.\\nTrong cuộc diễn tập trên, máy bay chiến đấu F-35B của Thủy quân lục chiến Mỹ đã tham gia nhiệm vụ ngăn chặn các “mục tiêu bay” VMFT-401, vốn là máy bay F-5N Tiger II hoán cải nhiệm vụ (thường được Quân đội Mỹ đặt biệt danh là Xạ thủ bắn tỉa).Thông tin chi tiết về lần tập luyện chiến đấu đầu tiên của máy bay F-35B không được công bố.\",\n" +
            "        \"date\" : \"2016-11-13, 22:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/photo-1-1479031247538.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"F-35B lần đầu tiên tham gia diễn tập chiến đấu giả lập\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/f-35b-lan-dau-tien-tham-gia-dien-tap-chien-dau-gia-lap-20161113170534885.htm\"\n" +
            "      },\n" +
            "      \"b7aa0aec0dc3f27fd87effe47ee6b67036fc416c\" : {\n" +
            "        \"code\" : \"b7aa0aec0dc3f27fd87effe47ee6b67036fc416c\",\n" +
            "        \"content\" : \"\\nDân trí Nữ phi công Trung Quốc lái máy bay chiến đấu J-10 đã tử nạn hôm qua 12/11 sau khi chiếc máy bay huấn luyện bất ngờ gặp sự cố và lao xuống đất ở tỉnh Hà Bắc.\\n \",\n" +
            "        \"date\" : \"2016-11-13, 21:50:53+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/phi-cong-1479047990521.png\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Chiến đấu cơ Trung Quốc lao xuống đất, nữ phi công tử nạn\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/chien-dau-co-trung-quoc-lao-xuong-dat-nu-phi-cong-tu-nan-20161113215243737.htm\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"Đời sống\" : {\n" +
            "      \"52bf69459554f1aaed8b6456f787f39e50d8094a\" : {\n" +
            "        \"code\" : \"52bf69459554f1aaed8b6456f787f39e50d8094a\",\n" +
            "        \"content\" : \"\\nDân trí Một chiếc xe tải chở cam bị lật đổ trên tuyến đường cao tốc thuộc khu vực nông thôn ở tỉnh Giang Tây. Người dân sống xung quanh đó nhanh chóng chạy tới \\\"hôi của\\\" trong khi tài xế lái xe chỉ biết ôm mặt khóc giữa đường vì bất lực.\\r\\nNgười dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đườngChiếc xe tải đang lưu thông trên một tuyến đường cao tốc ở Nghi Xuân, Giang Tây, Trung Quốc, thì gặp nạn và bị lật đổ. Vụ tai nạn khiến toàn bộ số cam trên xe cũng đổ xuống đường.Chứng kiến cảnh tượng trên, người dân sinh sống xung quanh đó đổ xô tới hiện trường để hôi của. Một vài người đàn ông còn mang theo những túi bao tải to \",\n" +
            "        \"date\" : \"2016-11-13, 15:15:50+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/c05a76d21c/2016/11/13/img20161113135708875-20a47.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"Người dân lao đến \\\"hôi của\\\", tài xế chở xe cam ôm mặt khóc giữa đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/nguoi-dan-lao-den-hoi-cua-tai-xe-cho-xe-cam-om-mat-khoc-giua-duong-2016111315151166.htm\"\n" +
            "      },\n" +
            "      \"0b216642da70a945f6b28f421a95d41c35c548b8\" : {\n" +
            "        \"code\" : \"0b216642da70a945f6b28f421a95d41c35c548b8\",\n" +
            "        \"content\" : \"\\r\\n    Việc Donald Trump, một người thiếu kinh nghiệm chính trị, đắc cử Tổng thống Mỹ đã khiến nhiều người lo sợ. Tuy nhiên, có một số lý do cho thấy ông Trump có thể là một Tổng thống Mỹ thành công.\\nBiết mình biết ngườiThứ nhất, để đi tới chiếc ghế Tổng thống, với tư cách là một doanh nhân, thay vì chính trị gia, Trump chắc hẳn là một người thông minh. Thứ hai, ông biết rõ một điều mà chúng ta ai cũng rõ. Đa phần những gì ông hứa nếu đắc cử chả có ý nghĩa gì, và đó là lý do ông có thể không thực hiện lời hứa, giáo sư Richard W. Painter thuộc trường luật Đại học Minnesota bình luận trên báo NY Times .Ngoài ra, theo nhà bình luận Sandhu của Lancashire Eveningpost, khi làm Tổng thống, \",\n" +
            "        \"date\" : \"2016-11-13, 21:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/20161112170625-1112g1-1479030766177.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Vì sao Trump sẽ là Tổng thống Mỹ thành công?\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/vi-sao-trump-se-la-tong-thong-my-thanh-cong-20161113165841355.htm\"\n" +
            "      },\n" +
            "      \"16a2bb3c0b9df104661967bd20f112700045bb3a\" : {\n" +
            "        \"code\" : \"16a2bb3c0b9df104661967bd20f112700045bb3a\",\n" +
            "        \"content\" : \"\\r\\n    Những bài tập trận tiêu diệt khủng bố, thả dù thiết bị quân sự hay điều khiển máy bay không người lái đều được thực hiện thành công bởi binh lính của Nga, Serbia và Belarus trong cuộc tập trận “Anh em Slavic 2016” tại Serbia.\\nCuộc tập trận \\\"Anh em Slavic 2016\\\" đã kết thúc tốt đẹp vào hôm 9-11, sau khi có sự tham gia của khoảng 250 binh lính Nga, bao gồm lực lượng đặc biệt và lính dù, 50 quân nhân Belarus cùng với 450 binh sĩ từ lực lượng vũ trang Serbia.\",\n" +
            "        \"date\" : \"2016-11-13, 18:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/5827a1edc3618870528b458b-1-1479032295463.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Binh lính Nga - Serbia - Belarus tập trận '\\\"Anh em Slavic 2016\\\"\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/binh-linh-nga-serbia-belarus-tap-tran-anh-em-slavic-2016-20161113173102834.htm\"\n" +
            "      },\n" +
            "      \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\" : {\n" +
            "        \"code\" : \"19c093d197dbe149abf5dc5e3c3e47ddac1a9f95\",\n" +
            "        \"content\" : \"\\r\\n    Các tàu chiến của Nga được triển khai trong vùng biển Syria đã bắt đầu tấn công các vị trí của nhóm khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại cực lớn cho phiến quân.\\nAl-Jafa, một chuyên gia về các vấn đề chiến lược hôm 12-11 tiết lộ, tên lửa hành trình Kalibr của Nga đã tấn công thành trì của những kẻ khủng bố ở phía tây thành phố Aleppo, và phía đông thị trấn al-Bab, gây thiệt hại nặng nề cho phiến quân. \",\n" +
            "        \"date\" : \"2016-11-13, 19:00:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/hai-quan-nga-bat-dau-tan-cong-aleppo-1479031687081.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Tàu chiến Nga bắt đầu tấn công căn cứ của phiến quân ở Aleppo\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/tau-chien-nga-bat-dau-tan-cong-can-cu-cua-phien-quan-o-aleppo-20161113171219445.htm\"\n" +
            "      },\n" +
            "      \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\" : {\n" +
            "        \"code\" : \"1d76ed6730c51f5fbecd44c5a3acf69116bd3076\",\n" +
            "        \"content\" : \"\\nDân trí Tháng Mười chưa cười đã tối, ở Trung Âu là thời khắc đổi sang giờ mùa đông, 3h sáng chủ nhật vặn ngược đồng hồ thành 2h. Xa Hà Nội cả vạn cây số, thời khắc này ca khúc “Có phải em mùa Thu Hà Nội\\\" sao mà quá gợi nhớ gợi thương...\\r\\nMới “tháng Tám mùa Thu lá khởi vàng chưa nhỉ”, thoắt cái đã qua Quốc khánh 2/9 và rồi tháng 10 ập tới... cây cối nơi nơi đồng loạt gọi nhau trút lá, trải thảm vàng ... mời gọi!Rất nhanh đồng loạt lá đổi màu chiều còn xanh, sáng hôm sau đã ngả vàng rực rỡ. \",\n" +
            "        \"date\" : \"2016-11-13, 15:50:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/tn-lavang1-1479024789428.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Thế giới\",\n" +
            "        \"title\" : \"Ostrava mùa Xanh - Vàng - Đỏ - Trắng\",\n" +
            "        \"url\" : \"http://dantri.com.vn/the-gioi/ostrava-mua-xanh-vang-do-trang-20161113155102516.htm\"\n" +
            "      },\n" +
            "      \"5fe415e4eba4883fc03ff93accf51a8f932c6fa9\" : {\n" +
            "        \"code\" : \"5fe415e4eba4883fc03ff93accf51a8f932c6fa9\",\n" +
            "        \"content\" : \"\\r\\n    TP.HCM có nhiều tên đường viết tắt, đánh số khó hiểu, khó tìm. Nhiều đường trùng tên với nhau gây ra nhầm lẫn, có tên đường còn thô tục.\\r\\nMới đây, Trung tâm Nghiên cứu đô thị và phát triển cho biết toàn TP có 1.774 đường mang tên tạm và khoảng 400 tên đường “có vấn đề” như tên không có ý nghĩa, tên trùng, tên khác nhau của cùng một nhân vật; nhiều tên đường còn ghi sai tên danh nhân; tên thiếu thẩm mỹ…Viết tắt và đánh số tùy tiện Pháp Luật TP.HCM đã tìm đến quận 12 và huyện Hóc Môn - nơi có nhiều khu vực được mệnh danh là ma trận tên đường với cách đặt tên đường viết tắt, kèm đánh số khó hiểu, khó nhớ.Ở quận 12, tên đường được đặt the\",\n" +
            "        \"date\" : \"2016-11-13, 15:01:00+00:00\",\n" +
            "        \"image_url\" : \"https://dantri4.vcmedia.vn/zoom/80_50/2016/3-duong-gfus-1479024004065.jpg\",\n" +
            "        \"labels\" : \"Dân trí/Đời sống\",\n" +
            "        \"title\" : \"TP HCM: Đến xe ôm chuyên nghiệp còn sợ tên đường\",\n" +
            "        \"url\" : \"http://dantri.com.vn/doi-song/tp-hcm-den-xe-om-chuyen-nghiep-con-so-ten-duong-20161113150234674.htm\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
