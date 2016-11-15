package nb.cblink.vnnews.modelview;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yalantis.phoenix.PullToRefreshView;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 26/10/2016.
 */

public class NewsFeedModelView {
    private Context context;
    private RecyclerView recyclerView;
    private PullToRefreshView mPullToRefreshView;
    private static final String TAG = NewsFeedModelView.class.getSimpleName();

    public NewsFeedModelView(Context context, RecyclerView recyclerView, PullToRefreshView mPullToRefreshView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.mPullToRefreshView = mPullToRefreshView;
        init();
    }

    void init() {
        StickyHeaderLayoutManager layoutManager = new StickyHeaderLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setHeaderPositionChangedCallback(new StickyHeaderLayoutManager.HeaderPositionChangedCallback() {
            @Override
            public void onHeaderPositionChanged(int sectionIndex, View header, StickyHeaderLayoutManager.HeaderPosition oldPosition, StickyHeaderLayoutManager.HeaderPosition newPosition) {
                Log.i(TAG, "onHeaderPositionChanged: section: " + sectionIndex + " -> old: " + oldPosition.name() + " new: " + newPosition.name());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    boolean elevated = newPosition == StickyHeaderLayoutManager.HeaderPosition.STICKY;
                    header.setElevation(elevated ? 8 : 0);
                }
                if (sectionIndex == 0 && newPosition.name().equals("NATURAL")) {
                    mPullToRefreshView.setEnabled(true);
                    if (MainActivity.toolbar.getVisibility() != View.VISIBLE)
                        MainActivity.toolbar.setVisibility(View.VISIBLE);
                    header.setBackgroundColor(context.getResources().getColor(R.color.Color_Azure));
                } else {
                    mPullToRefreshView.setEnabled(false);
                    if (MainActivity.toolbar.getVisibility() != View.GONE && sectionIndex != 0)
                        MainActivity.toolbar.setVisibility(View.GONE);
//                    header.setBackground(context.getDrawable(R.drawable.buildings));
                }
//                if (newPosition.name().equals("STICKY")) {
//                    header.setBackgroundColor(context.getResources().getColor(R.color.Color_Cyan));
//                }
            }
        });

        NewsFeedAdapter adapter = new NewsFeedAdapter(100, 5, false, false, true, true);
        recyclerView.setAdapter(adapter);

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
}
