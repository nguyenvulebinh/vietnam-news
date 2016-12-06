package nb.cblink.vnnews.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.model.FeedTopic;

/**
 * Created by nguyenbinh on 05/12/2016.
 */

public class DataFactory {
    private static DataFactory me = null;
    private static List<String> ORDER_TOPIC = Arrays.asList("Thời sự", "Kinh tế", "Giải trí", "Thể thao", "Khoa học - Công nghệ", "Pháp luật", "Giáo dục", "Sức khỏe", "Đời sống - Xã hội", "Việc làm", "Du lịch", "Xe", "Độc giả", "Tin khác");
    private static List<Integer> TOPIC_ICON = Arrays.asList(R.drawable.nav_news, R.drawable.nav_economy, R.drawable.nav_entertainment, R.drawable.nav_sport, R.drawable.nav_science, R.drawable.nav_law, R.drawable.nav_education, R.drawable.nav_health, R.drawable.nav_social, R.drawable.nav_job, R.drawable.nav_travel, R.drawable.nav_vehicle, R.drawable.nav_reader, R.drawable.nav_other);


    private DataFactory() {
        data = new ArrayList<>();
        for (int i = 0; i < ORDER_TOPIC.size(); i++) {
            String topicName = ORDER_TOPIC.get(i);
            FeedTopic topic = new FeedTopic();
            topic.setNameTopic(topicName);
            topic.setNavIcon(TOPIC_ICON.get(i));
            topic.setBackgroundSrc(R.drawable.topic_header);
            data.add(topic);
        }
    }

    public ArrayList<FeedTopic> data;

    public static DataFactory getInstance() {
        if (me == null) {
            me = new DataFactory();
        }
        return me;
    }

    public boolean haveData() {
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            count += data.get(i).getListNews().size();
            if (count > 0) return true;
        }
        return false;
    }

}
