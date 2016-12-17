package nb.cblink.vnnews.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

import nb.cblink.vnnews.model.FeedTopic;
import nb.cblink.vnnews.model.News;

/**
 * Created by nguyenbinh on 17/12/2016.
 */

public class BookmarkFactory {

    private static String PRE_TOPIC = "bookmark";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static BookmarkFactory me = null;
    private HashSet<String> hashUrl;
    public FeedTopic bookmarkData;

    private BookmarkFactory() {
        hashUrl = new HashSet<>();
    }

    public static BookmarkFactory getReference(Context context) {
        if (me == null) {
            me = new BookmarkFactory();
            preferences = context.getSharedPreferences(PRE_TOPIC, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return me;
    }

    public void getCurrentBookmark() {

    }

    public boolean isBookmarked(News news) {
        return hashUrl.contains(news.getNewsUrl());
    }
}
