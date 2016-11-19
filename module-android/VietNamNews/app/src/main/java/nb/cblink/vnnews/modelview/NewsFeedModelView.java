package nb.cblink.vnnews.modelview;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;

import com.yalantis.phoenix.PullToRefreshView;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.model.FeedTopic;
import nb.cblink.vnnews.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 26/10/2016.
 */

public class NewsFeedModelView {
    private MainActivity context;
    private RecyclerView recyclerView;
    private PullToRefreshView mPullToRefreshView;
    private static final String TAG = NewsFeedModelView.class.getSimpleName();
    private Window window;

    public NewsFeedModelView(MainActivity context, RecyclerView recyclerView, PullToRefreshView mPullToRefreshView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.mPullToRefreshView = mPullToRefreshView;
        window = context.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
                } else {
                    mPullToRefreshView.setEnabled(false);
                    if (MainActivity.toolbar.getVisibility() != View.GONE) {
                        if (sectionIndex != 0) {
                            MainActivity.toolbar.setVisibility(View.GONE);
                        }
                    }
//                    else if(sectionIndex == 0) {
//                        window.setStatusBarColor(((FeedTopic)header.getTag()).getColorTopic() - 0x0000000f);
//                    }
                }

                if ((oldPosition.name().equals("NATURAL") && newPosition.name().equals("STICKY")) || oldPosition.name().equals("TRAILING") && newPosition.name().equals("STICKY")) {
                    ScaleAnimation scale = new ScaleAnimation((float) 1.0, (float) 0.25, (float) 1.0, (float) 0.25);
                    scale.setFillAfter(true);
                    scale.setDuration(600);
                    ((FeedTopic) header.getTag()).setSizeText(55);
                    ((FeedTopic) header.getTag()).setBackgroundSrcVisibility(View.INVISIBLE);
                    header.startAnimation(scale);
                    //window.setStatusBarColor(((FeedTopic)header.getTag()).getColorTopic() - 0xff00000f);
                } else if (oldPosition.name().equals("STICKY") && newPosition.name().equals("NATURAL")) {
                    ScaleAnimation scale = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 1.0, (float) 1.0);
                    scale.setFillAfter(true);
                    scale.setDuration(0);
                    ((FeedTopic) header.getTag()).setSizeText(25);
                    ((FeedTopic) header.getTag()).setBackgroundSrcVisibility(View.VISIBLE);
                    header.startAnimation(scale);
                }
                if (MainActivity.toolbar.getVisibility() == View.VISIBLE) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
            }
        });

        NewsFeedAdapter adapter = new NewsFeedAdapter(context, window);
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
