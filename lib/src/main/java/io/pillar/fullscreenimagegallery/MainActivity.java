package io.pillar.fullscreenimagegallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_URLS =
            MainActivity.class.getName() + ".EXTRA_IMAGE_URLS";
    public static final String EXTRA_CURRENT_INDEX =
            MainActivity.class.getName() + ".EXTRA_CURRENT_INDEX";

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager viewPager = new ViewPager(this);
        setContentView(viewPager);

        String[] imageUrls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
        int currentIndex = getIntent().getIntExtra(EXTRA_CURRENT_INDEX, -1);

        if (imageUrls != null) {
            viewPager.setAdapter(new ImagePagerAdapter(imageUrls));
        }
        if (currentIndex != -1) {
            viewPager.setCurrentItem(currentIndex);
        }

        decorView = getWindow().getDecorView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUI() {
        // Set the IMMERSIVE_STICKY flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private String[] imageUrls;

        ImagePagerAdapter(String[] imageUrls) {
            this.imageUrls = imageUrls;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FrameLayout layout = new FrameLayout(container.getContext());

            String imageUrl = imageUrls[position];
            addViewsForItem(layout, imageUrl);
            Log.d("ImagePagerAdapter", "layout:" + layout + ", imageUrl:" + imageUrl);

            container.addView(layout);
            return layout;
        }

        private void addViewsForItem(final FrameLayout layout, String imageUrl) {
            Context context = layout.getContext();

            ImageView imageView = new ImageView(context);
            layout.addView(imageView);

            final ProgressBar progressBar = new ProgressBar(context);
            layout.addView(progressBar, new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

            Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target,
                                                   boolean isFirstResource) {

                            progressBar.setVisibility(View.GONE);

                            e.printStackTrace();

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap bitmap, String model,
                                                       Target<Bitmap> target,
                                                       boolean isFromMemoryCache,
                                                       boolean isFirstResource) {

                            progressBar.setVisibility(View.GONE);

                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette p) {
                                    int vibrantColor = p.getVibrantColor(Color.TRANSPARENT);
                                    layout.setBackgroundColor(vibrantColor);
                                }
                            });

                            return false;
                        }
                    })
                    .into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
