package nb.cblink.vnnews.modelview;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.firebase.database.DatabaseReference;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.Random;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.data.DataFactory;
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
    private Window window;
    private static final String[] colors = {
            "F44336", "E91E63", "00BCD4", "4CAF50", "9C27B0", "673AB7",
            "FF5722", "3F51B5",
            "2196F3", "795548", "607D8B", "009688",
            "8BC34A", "FFEB3B", "FFC107", "FF9800", "03A9F4", "9E9E9E", "CDDC39"};
    private Random getIndexColor;
    DatabaseReference mFirebaseDatabaseReference;

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


    public NewsFeedAdapter(MainActivity activity, Window window, DatabaseReference mFirebaseDatabaseReference) {
        this.mFirebaseDatabaseReference = mFirebaseDatabaseReference;
        this.context = activity;
        this.window = window;
        getIndexColor = new Random();
    }

    @Override
    public int getNumberOfSections() {
        return DataFactory.getInstance().data.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        try {
            return DataFactory.getInstance().data.get(sectionIndex).getListNews().size() > 10 ? 10 : DataFactory.getInstance().data.get(sectionIndex).getListNews().size();
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
        FeedTopic s = DataFactory.getInstance().data.get(sectionIndex);
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        FeedItemDataBinding binding = ivh.getFeedItemDataBinding();
        if (itemIndex == s.getListNews().size() - 1)
            s.getListNews().get(itemIndex).setLastNews(View.INVISIBLE);
        if (itemIndex < s.getListNews().size()) {
            binding.setNewsData(s.getListNews().get(itemIndex));
            binding.setFIMv(new FeedItemModelView());
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        FeedTopic s = DataFactory.getInstance().data.get(sectionIndex);
        s.setColorTopic(0xdf000000 + Integer.parseInt(colors[sectionIndex], 16));
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        FeedHeaderDataBinding binding = hvh.getFeedHeaderDataBinding();
        binding.setFTp(s);
        binding.setFHMv(new FeedHeaderModelView());
        binding.getRoot().setTag(s);
    }
}
