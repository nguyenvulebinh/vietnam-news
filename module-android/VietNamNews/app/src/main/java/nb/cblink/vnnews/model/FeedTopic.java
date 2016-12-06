package nb.cblink.vnnews.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import nb.cblink.vnnews.R;

/**
 * Created by nguyenbinh on 17/11/2016.
 */

public class FeedTopic extends BaseObservable {
    @Bindable
    private String nameTopic;
    @Bindable
    private int colorTopic;
    @Bindable
    private int sizeText;
    @Bindable
    private int backgroundSrc;
    @Bindable
    private int backgroundSrcVisibility;
    private int navIcon;

    private ArrayList<News> listNews;
    private ArrayList<String> listReference;

    public FeedTopic() {
        colorTopic = 0xffa29394;
        sizeText = 20;
        backgroundSrc = R.drawable.buildings;
        backgroundSrcVisibility = View.VISIBLE;
        listNews = new ArrayList<>();
        listReference = new ArrayList<>();
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public ArrayList<News> getListNews() {
        return listNews;
    }

    public void setListNews(ArrayList<News> listNews) {
        this.listNews = listNews;
    }

    public void addNews(News news) {
        if (listNews.size() > 0) listNews.get(0).setFirstNews(false);
        listNews.add(news);
        sort(listNews);
        listNews.get(0).setFirstNews(true);
    }

    /**
     * Sap xep lai theo trinh tu thoi gian
     *
     * @param list
     */
    private void sort(ArrayList<News> list) {
        Collections.sort(list, new Comparator<News>() {
            @Override
            public int compare(News news, News t1) {
                return (int) (news.getCurrentMilisecond() - t1.getCurrentMilisecond());
            }
        });
    }

    private int timeCompare(String time1, String time2) {
        return 1;
    }

    public int getColorTopic() {
        return colorTopic;
    }

    public void setColorTopic(int colorTopic) {
        this.colorTopic = colorTopic;
    }

    public int getSizeText() {
        return sizeText;
    }

    public void setSizeText(int sizeText) {
        this.sizeText = sizeText;
        notifyPropertyChanged(BR.sizeText);
    }

    public int getBackgroundSrc() {
        return backgroundSrc;
    }

    public void setBackgroundSrc(int backgroundSrc) {
        this.backgroundSrc = backgroundSrc;
        notifyPropertyChanged(BR.backgroundSrc);
    }

    public int getBackgroundSrcVisibility() {
        return backgroundSrcVisibility;
    }

    public void setBackgroundSrcVisibility(int backgroundSrcVisibility) {
        this.backgroundSrcVisibility = backgroundSrcVisibility;
        notifyPropertyChanged(BR.backgroundSrcVisibility);
    }

    public ArrayList<String> getListReference() {
        return listReference;
    }

    public void setListReference(ArrayList<String> listReference) {
        this.listReference = listReference;
    }

    public int getNavIcon() {
        return navIcon;
    }

    public void setNavIcon(int navIcon) {
        this.navIcon = navIcon;
    }
}
