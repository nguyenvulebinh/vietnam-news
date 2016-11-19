package nb.cblink.vnnews.modelview;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by phanirajabhandari on 7/8/15.
 */
public class CustomBindingImage {
    private static Picasso picasso = null;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (imageView.getWidth() == 0) return;
        if (picasso == null) {
            int maxSize = 1024 * 1024 * 100;
            picasso = new Picasso.Builder(imageView.getContext())
                    .memoryCache(new LruCache(maxSize))
                    .build();
        }
        RequestCreator creator = picasso.load(url);
        if (imageView.getWidth() / imageView.getHeight() < 1.5)
            creator.resize(imageView.getWidth() / 2, imageView.getHeight()).centerCrop().into(imageView);
        else
            creator.into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        if (picasso == null) {
            int maxSize = 1024 * 1024 * 100;
            picasso = new Picasso.Builder(imageView.getContext())
                    .memoryCache(new LruCache(maxSize))
                    .build();
        }
        picasso.load(resource).resize(imageView.getWidth(), imageView.getHeight()).into(imageView);
    }
}
