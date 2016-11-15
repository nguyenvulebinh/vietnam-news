package nb.cblink.vnnews.model;

/**
 * Created by nguyenbinh on 14/11/2016.
 */

public class News {
    private String url;
    private String image;
    private String paperName;
    private int paperImage;
    private String time;
    private int type;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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


}
