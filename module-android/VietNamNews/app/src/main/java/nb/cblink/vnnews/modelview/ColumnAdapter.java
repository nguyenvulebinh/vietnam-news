package nb.cblink.vnnews.modelview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.data.DataFactory;
import nb.cblink.vnnews.databinding.ColumnItemDataBinding;
import nb.cblink.vnnews.model.FeedTopic;

/**
 * Created by nguyenbinh on 22/11/2016.
 */

public class ColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private static int MAX_SHOW = 100;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private SpecificColumnModelView columnItemModelView;
    private FeedTopic topic;

    public ColumnAdapter(Context context, int idColumn, RecyclerView recyclerView, SpecificColumnModelView columnItemModelView) {
        this.context = context;
        this.columnItemModelView = columnItemModelView;
        this.topic = DataFactory.getInstance().data.get(idColumn);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                if (totalItemCount < MAX_SHOW) {
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });

    }

    void addToLoadMore() {
        topic.getListNews().add(null);
        this.notifyItemInserted(topic.getListNews().size() - 1);
    }

    void addMoreSongToShow() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                topic.getListNews().remove(topic.getListNews().size() - 1);
                ColumnAdapter.this.notifyItemRemoved(topic.getListNews().size());

                //Load data
                //...

                ColumnAdapter.this.notifyDataSetChanged();
                ColumnAdapter.this.setLoaded();
            }
        }, 1000);
    }

    private void setLoaded() {
        isLoading = false;
    }

    void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            ColumnItemDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.frag_item_column, parent, false);
            return new ColumnItemViewHolder(binding);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadMoreViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ColumnItemViewHolder) {
            ColumnItemDataBinding binding = ((ColumnItemViewHolder) holder).getBinding();
            binding.setNewsData(topic.getListNews().get(position));
            binding.setCItemMv(columnItemModelView);
        } else {
            LoadMoreViewHolder loadingViewHolder = (LoadMoreViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return topic.getListNews().get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return topic.getListNews().size();
    }

    class ColumnItemViewHolder extends RecyclerView.ViewHolder {
        private ColumnItemDataBinding binding;

        public ColumnItemViewHolder(ColumnItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ColumnItemDataBinding getBinding() {
            return binding;
        }
    }

    class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        public View loadMore;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            loadMore = (CardView) itemView.findViewById(R.id.load_more);
        }
    }

    interface OnLoadMoreListener {
        void onLoadMore();
    }

}
