package nb.cblink.vnnews.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.databinding.SpecificColumnDataBinding;
import nb.cblink.vnnews.modelview.SpecificColumnModelView;
import nb.cblink.vnnews.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 26/10/2016.
 */

public class SpecificColumnFragment extends Fragment {
    SpecificColumnModelView modelView;
    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SpecificColumnDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.frag_specific_column, container, false);
        layout = binding.getRoot();
        modelView = new SpecificColumnModelView((MainActivity) getActivity(), (RecyclerView) layout.findViewById(R.id.recycleview_column), getArguments().getInt("idColumn"), (PullToRefreshView) layout.findViewById(R.id.pull_to_refresh_column));
        binding.setSCmv(modelView);
        return layout;
    }
}
