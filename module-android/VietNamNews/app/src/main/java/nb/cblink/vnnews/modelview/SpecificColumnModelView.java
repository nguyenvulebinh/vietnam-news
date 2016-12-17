package nb.cblink.vnnews.modelview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;
import com.yalantis.phoenix.PullToRefreshView;

import nb.cblink.vnnews.model.News;
import nb.cblink.vnnews.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 26/10/2016.
 */

public class SpecificColumnModelView {
    private MainActivity context;
    private ColumnAdapter adapter;

    public SpecificColumnModelView(MainActivity context, RecyclerView recyclerView, int idColumn, final PullToRefreshView mPullToRefreshView) {
        this.context = context;
        //Set layout cho recycle view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ColumnAdapter(context, idColumn, recyclerView, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new ColumnAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //Mở khi cần load thêm
//                adapter.addToLoadMore();
//                adapter.addMoreSongToShow();
            }
        });

        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void showWebNews(View v, String url) {
        new FinestWebView.Builder(v.getContext()).show(url);
    }

    public void clickBookmark(News news) {
        news.setMark(!news.isMark());
    }

}
