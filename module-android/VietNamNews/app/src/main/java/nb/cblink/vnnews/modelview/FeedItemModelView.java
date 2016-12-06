package nb.cblink.vnnews.modelview;

import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;

import nb.cblink.vnnews.model.News;

/**
 * Created by nguyenbinh on 14/11/2016.
 */

public class FeedItemModelView {

    public void showWebNews(View v, String url) {
        new FinestWebView.Builder(v.getContext()).show(url);
    }

    public void clickBookmark(News news) {
        news.setMark(!news.isMark());
    }
}
