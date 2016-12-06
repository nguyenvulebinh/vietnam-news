package nb.cblink.vnnews.model;

import java.util.ArrayList;

import nb.cblink.vnnews.data.TestListData;

/**
 * Created by nguyenbinh on 22/11/2016.
 */

public class ColumnNews {
    private ArrayList<News> data;

    public ColumnNews(int id) {
        data = new ArrayList<>();
        ArrayList<FeedTopic> allData = new ArrayList<>();
        //Get test data
        TestListData.getData(allData);
        FeedTopic topic = allData.get(0);
        for (News news : topic.getListNews()) {
            data.add(news);
        }
    }

    public int getSize() {
        return data.size();
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }
}
