package nb.cblink.vnnews.modelview;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import nb.cblink.vnnews.R;

/**
 * Created by phanirajabhandari on 7/8/15.
 */
public class CustomBindingImage {
    private static Picasso picasso = null;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (imageView.getWidth() == 0) return;
        if (picasso == null) {
            picasso = new Picasso.Builder(imageView.getContext())
                    .build();
        }
        if (url.length() == 0) {
            picasso.load(R.mipmap.ic_launcher).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        } else {
            RequestCreator creator = picasso.load(url);
            if (imageView.getWidth() / imageView.getHeight() < 1.5)
                creator.resize(imageView.getWidth() / 2, imageView.getHeight()).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
            else
                creator.memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        if (picasso == null) {
            picasso = new Picasso.Builder(imageView.getContext())
                    .build();
        }
        if (resource == R.drawable.logo_dantri || resource == R.drawable.logo_vietbao || resource == R.drawable.logo_vtv || resource == R.drawable.logo_techtalk || resource == R.drawable.logo_vnexpress) {
            picasso.load(resource).resize(100, 100).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        } else {
            picasso.load(resource).resize(imageView.getWidth(), imageView.getHeight()).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, String url) {
        if (picasso == null) {
            int maxSize = 1024 * 1024 * 100;
            picasso = new Picasso.Builder(imageView.getContext())
                    .memoryCache(new LruCache(maxSize))
                    .build();
        }
        picasso.load(url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
    }
}
