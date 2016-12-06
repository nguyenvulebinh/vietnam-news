package nb.cblink.vnnews.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import nb.cblink.vnnews.model.FeedTopic;

/**
 * Created by nguyenbinh on 05/12/2016.
 */

public class TopicPrereference {
    private static String PRE_TOPIC = "topic";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static TopicPrereference me = null;

    public static TopicPrereference getReference(Context context) {
        if (me == null) {
            me = new TopicPrereference();
            preferences = context.getSharedPreferences(PRE_TOPIC, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return me;
    }

    public boolean getListTopic(ArrayList<FeedTopic> data) {
//        ArrayList<FeedTopic> topics = new ArrayList<>();
//        FeedTopic topic = new FeedTopic();
//        topic.setNameTopic("Van hoa");
//        topics.add(topic);
//        return topics;
        return false;
    }

}
