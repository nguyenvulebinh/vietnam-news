package nb.cblink.vnnews.modelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.data.TestListData;
import nb.cblink.vnnews.databinding.FeedHeaderDataBinding;
import nb.cblink.vnnews.databinding.FeedItemDataBinding;
import nb.cblink.vnnews.model.FeedTopic;
import nb.cblink.vnnews.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 13/10/2016.
 */

public class NewsFeedAdapter extends SectioningAdapter {

    private static final String TAG = NewsFeedAdapter.class.getSimpleName();
    private MainActivity context;
    ArrayList<FeedTopic> data;
    private Window window;

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        private FeedItemDataBinding feedItemDataBinding;

        public ItemViewHolder(FeedItemDataBinding feedItemDataBinding) {
            super(feedItemDataBinding.getRoot());
            this.feedItemDataBinding = feedItemDataBinding;
        }

        public FeedItemDataBinding getFeedItemDataBinding() {
            return feedItemDataBinding;
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder implements View.OnClickListener {
        private FeedHeaderDataBinding feedHeaderDataBinding;

        public HeaderViewHolder(FeedHeaderDataBinding feedHeaderDataBinding) {
            super(feedHeaderDataBinding.getRoot());
            this.feedHeaderDataBinding = feedHeaderDataBinding;
        }

        public FeedHeaderDataBinding getFeedHeaderDataBinding() {
            return feedHeaderDataBinding;
        }

        @Override
        public void onClick(View v) {
        }
    }


    public NewsFeedAdapter(MainActivity activity, Window window) {
        data = TestListData.getData();
        this.context = activity;
        this.window = window;
    }

    @Override
    public int getNumberOfSections() {
        return data.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        try {
            return data.get(sectionIndex).getListNews().size();
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        FeedItemDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_simple_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        FeedHeaderDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_simple_header, parent, false);
        return new HeaderViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        FeedTopic s = data.get(sectionIndex);
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        FeedItemDataBinding binding = ivh.getFeedItemDataBinding();
        if (itemIndex == s.getListNews().size() - 1)
            s.getListNews().get(itemIndex).setLastNews(View.INVISIBLE);
        binding.setNewsData(s.getListNews().get(itemIndex));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        FeedTopic s = data.get(sectionIndex);
        s.setColorTopic(0xdf00a394 + (sectionIndex - 1) * 0x0a000);
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        FeedHeaderDataBinding binding = hvh.getFeedHeaderDataBinding();
        binding.setFTp(s);
        binding.getRoot().setTag(s);
    }
}
