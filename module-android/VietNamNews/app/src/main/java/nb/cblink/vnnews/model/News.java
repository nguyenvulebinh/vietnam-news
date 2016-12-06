package nb.cblink.vnnews.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nb.cblink.vnnews.BR;
import nb.cblink.vnnews.R;

/**
 * Created by nguyenbinh on 14/11/2016.
 */

public class News extends BaseObservable {
    @Bindable
    private String newsUrl;
    @Bindable
    private String imageUrl;
    @Bindable
    private String paperName;
    @Bindable
    private int paperImage;
    @Bindable
    private String newsTitle;
    @Bindable
    private String time;
    private int type;
    @Bindable
    private String content;
    @Bindable
    private boolean firstNews;
    @Bindable
    private int lastNews;
    @Bindable
    private boolean mark;
    private long currentMilisecond;

    public News() {
        lastNews = View.VISIBLE;
        mark = false;
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
        if (this.imageUrl == null || this.imageUrl.length() == 0) {
            this.imageUrl = "";
        } else {
            if (this.imageUrl.startsWith("https://dantri4")) {
                this.imageUrl = this.imageUrl.replace("zoom/80_50/", "");
            } else if (this.imageUrl.startsWith("http://img.")) {
                this.imageUrl = this.imageUrl.replace("_180x108", "");
            } else if (this.imageUrl.startsWith("https://vtv")) {
                this.imageUrl = this.imageUrl.replace("zoom/80_50/", "");
            }
        }
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
        if (this.paperName.indexOf("/") > 0) {
            this.paperName = this.paperName.substring(0, this.paperName.indexOf("/"));
        }
        switch (this.paperName) {
            case "vtv":
                setPaperImage(R.drawable.logo_vtv);
                break;
            case "vnexpress":
                setPaperImage(R.drawable.logo_vnexpress);
                break;
            case "vietbao":
                setPaperImage(R.drawable.logo_vietbao);
                break;
            case "Dân trí":
                setPaperImage(R.drawable.logo_dantri);
                break;
            case "techtalk":
                setPaperImage(R.drawable.logo_techtalk);
                break;
        }
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
        //"2016-11-24, 08:30:43+00:00"
        this.time = time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ssZZZZ");
        try {
            Date date = simpleDateFormat.parse(this.time);
            long past = System.currentTimeMillis() - date.getTime();
            currentMilisecond = past;
            long mins = past / 1000 / 60;
            if (mins < 60) {
                this.time = mins + " phút trước";
            } else {
                long hours = mins / 60;
                if (hours < 24) {
                    this.time = hours + " giờ trước";
                } else {
                    this.time = hours / 24 + " ngày trước";
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        if (this.content.length() < 10) this.firstNews = true;
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

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
        notifyPropertyChanged(BR.mark);
    }

    public long getCurrentMilisecond() {
        return currentMilisecond;
    }
}
