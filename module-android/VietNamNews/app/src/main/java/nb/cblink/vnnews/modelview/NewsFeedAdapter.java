package nb.cblink.vnnews.modelview;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.databinding.FeedHeaderDataBinding;
import nb.cblink.vnnews.databinding.FeedItemDataBinding;
import nb.cblink.vnnews.model.News;

/**
 * Created by nguyenbinh on 13/10/2016.
 */

public class NewsFeedAdapter extends SectioningAdapter {

    private static final String TAG = NewsFeedAdapter.class.getSimpleName();

    private class Section {
        int index;
        int copyCount;
        String header;
        ArrayList<News> items = new ArrayList<>();
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        TextView textView;
        TextView adapterPositionTextView;
        ImageButton cloneButton;
        ImageButton deleteButton;
        private FeedItemDataBinding feedItemDataBinding;

        public ItemViewHolder(FeedItemDataBinding feedItemDataBinding) {
            super(feedItemDataBinding.getRoot());
            View itemView = feedItemDataBinding.getRoot();
            this.feedItemDataBinding = feedItemDataBinding;
//            textView = (TextView) itemView.findViewById(R.id.textView);
//            adapterPositionTextView = (TextView) itemView.findViewById(R.id.adapterPositionTextView);
//
//            cloneButton = (ImageButton) itemView.findViewById(R.id.cloneButton);
//            cloneButton.setOnClickListener(this);
//
//            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
//            deleteButton.setOnClickListener(this);
//
//            if (!NewsFeedAdapter.this.showModificationControls) {
//                cloneButton.setVisibility(View.GONE);
//                deleteButton.setVisibility(View.GONE);
//            }
//
//            if (!NewsFeedAdapter.this.showAdapterPositions) {
//                adapterPositionTextView.setVisibility(View.GONE);
//            }
        }

        public FeedItemDataBinding getFeedItemDataBinding() {
            return feedItemDataBinding;
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder implements View.OnClickListener {
        TextView textView;
        TextView adapterPositionTextView;
        ImageButton cloneButton;
        ImageButton deleteButton;
        ImageButton collapseButton;
        private FeedHeaderDataBinding feedHeaderDataBinding;

        public HeaderViewHolder(FeedHeaderDataBinding feedHeaderDataBinding) {
            super(feedHeaderDataBinding.getRoot());
            View itemView = feedHeaderDataBinding.getRoot();
            this.feedHeaderDataBinding = feedHeaderDataBinding;
            textView = (TextView) itemView.findViewById(R.id.textView);
            adapterPositionTextView = (TextView) itemView.findViewById(R.id.adapterPositionTextView);

            cloneButton = (ImageButton) itemView.findViewById(R.id.cloneButton);
            cloneButton.setOnClickListener(this);

            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);

            collapseButton = (ImageButton) itemView.findViewById(R.id.collapseButton);
            collapseButton.setOnClickListener(this);

            if (!NewsFeedAdapter.this.showModificationControls) {
                cloneButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }

            if (!NewsFeedAdapter.this.showCollapsingSectionControls) {
                collapseButton.setVisibility(View.GONE);
            }

            if (!NewsFeedAdapter.this.showAdapterPositions) {
                cloneButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                adapterPositionTextView.setVisibility(View.INVISIBLE);
            }
        }

        public FeedHeaderDataBinding getFeedHeaderDataBinding() {
            return feedHeaderDataBinding;
        }

        void updateSectionCollapseToggle(boolean sectionIsCollapsed) {
            @DrawableRes int id = sectionIsCollapsed
                    ? R.drawable.ic_expand_more_black_24dp
                    : R.drawable.ic_expand_less_black_24dp;

            collapseButton.setImageDrawable(ContextCompat.getDrawable(collapseButton.getContext(), id));
        }

        @Override
        public void onClick(View v) {
        }
    }

    ArrayList<Section> sections = new ArrayList<>();
    boolean showModificationControls;
    boolean showCollapsingSectionControls;
    boolean showAdapterPositions;
    boolean hasFooters;

    public NewsFeedAdapter(int numSections, int numItemsPerSection, boolean hasFooters, boolean showModificationControls, boolean showCollapsingSectionControls, boolean showAdapterPositions) {
        this.showModificationControls = showModificationControls;
        this.showCollapsingSectionControls = showCollapsingSectionControls;
        this.showAdapterPositions = showAdapterPositions;
        this.hasFooters = hasFooters;

        for (int i = 0; i < numSections; i++) {
            appendSection(i, numItemsPerSection);
        }
    }

    void appendSection(int index, int itemCount) {
        Section section = new Section();
        section.index = index;
        section.copyCount = 0;
        section.header = Integer.toString(index);

        for (int j = 0; j < itemCount; j++) {
            News news = new News();
//            section.items.add(index + "/" + j);
        }

        sections.add(section);
    }

    @Override
    public int getNumberOfSections() {
        return sections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return sections.get(sectionIndex).items.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return !TextUtils.isEmpty(sections.get(sectionIndex).header);
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
        Section s = sections.get(sectionIndex);
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        FeedItemDataBinding binding = ivh.getFeedItemDataBinding();
//        binding.setNewsData();
//        ivh.textView.setText(s.items.get(itemIndex));
//        ivh.adapterPositionTextView.setText(Integer.toString(getAdapterPositionForSectionItem(sectionIndex, itemIndex)));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        Section s = sections.get(sectionIndex);
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        hvh.adapterPositionTextView.setText(Integer.toString(getAdapterPositionForSectionHeader(sectionIndex)));
        hvh.textView.setText(s.header);
        hvh.updateSectionCollapseToggle(isSectionCollapsed(sectionIndex));
    }

}
