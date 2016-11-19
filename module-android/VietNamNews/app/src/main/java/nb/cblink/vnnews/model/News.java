package nb.cblink.vnnews.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by nguyenbinh on 14/11/2016.
 */

public class News extends BaseObservable {
    private String newsUrl;
    @Bindable
    private String imageUrl;
    @Bindable
    private String paperName;
    private int paperImage;
    @Bindable
    private String newsTitle;
    private String time;
    private int type;
    @Bindable
    private String content;
    @Bindable
    private boolean firstNews;
    @Bindable
    private int lastNews;

    public News() {
        lastNews = View.VISIBLE;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getPaperImage() {
        return paperImage;
    }

    public void setPaperImage(int paperImage) {
        this.paperImage = paperImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public boolean isFirstNews() {
        return firstNews;
    }

    public void setFirstNews(boolean firstNews) {
        this.firstNews = firstNews;
    }

    public int getLastNews() {
        return lastNews;
    }

    public void setLastNews(int lastNews) {
        this.lastNews = lastNews;
    }
}
