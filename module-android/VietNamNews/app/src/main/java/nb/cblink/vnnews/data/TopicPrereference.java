package nb.cblink.vnnews.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import nb.cblink.vnnews.model.FeedTopic;

/**
 * Created by nguyenbinh on 05/12/2016.
 */

public class TopicPrereference {
    private static String PRE_TOPIC = "topic";
    private static String PRE_LIST = "LIST_TOPICS";
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
        Set<String> setTopicName = preferences.getStringSet(PRE_LIST, null);
        if (setTopicName == null) return false;
        for (String topicName : setTopicName) {
            for (FeedTopic current : data) {
                if (current.getNameTopic().equals(topicName)) {
                    Set<String> setRefer = preferences.getStringSet(topicName, null);
                    if (setRefer == null) return false;
                    current.setListReference(new ArrayList<>(setRefer));
                    break;
                }
            }
        }
        return true;
    }

    public void saveTopicPrereference(ArrayList<FeedTopic> data) {
        Set<String> setTopicName = new HashSet<>();
        for (FeedTopic topic : data) {
            setTopicName.add(topic.getNameTopic());
            Set<String> topicRefer = new HashSet<>();
            for (String str : topic.getListReference()) {
                topicRefer.add(str);
            }
            editor.putStringSet(topic.getNameTopic(), topicRefer);
        }
        editor.putStringSet(PRE_LIST, setTopicName);
        editor.apply();
    }

}
