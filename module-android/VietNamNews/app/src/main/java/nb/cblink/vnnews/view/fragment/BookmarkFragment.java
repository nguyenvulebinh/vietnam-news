package nb.cblink.vnnews.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.databinding.BookmarkDataBinding;
import nb.cblink.vnnews.modelview.BookmarkModelView;

/**
 * Created by nguyenbinh on 17/12/2016.
 */

public class BookmarkFragment extends Fragment {
    BookmarkModelView modelView;
    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BookmarkDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.frag_bookmark, container, false);
        layout = binding.getRoot();
        modelView = new BookmarkModelView();
        binding.setBMmv(modelView);
        return layout;
    }
}
