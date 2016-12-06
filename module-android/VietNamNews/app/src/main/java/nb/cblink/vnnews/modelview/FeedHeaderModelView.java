package nb.cblink.vnnews.modelview;

import android.view.View;
import android.widget.Toast;

import nb.cblink.vnnews.model.FeedTopic;

/**
 * Created by nguyenbinh on 14/11/2016.
 */

public class FeedHeaderModelView {
    public void clickSpecColumn(View v, FeedTopic topic) {
        Toast.makeText(v.getContext(), topic.getNameTopic(), Toast.LENGTH_SHORT).show();
    }
}
