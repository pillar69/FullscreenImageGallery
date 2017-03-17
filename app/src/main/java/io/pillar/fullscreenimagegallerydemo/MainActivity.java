package io.pillar.fullscreenimagegallerydemo;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView imageGrid = new RecyclerView(this);
        setContentView(imageGrid);

        imageGrid.setLayoutManager(
                new GridLayoutManager(this, 2));

        final int itemPadding = getResources().getDimensionPixelSize(R.dimen.item_padding);
        imageGrid.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {

                outRect.set(itemPadding, itemPadding, itemPadding, itemPadding);
            }
        });

        imageGrid.setAdapter(new ImageAdapter());
    }

    private class ImageAdapter extends RecyclerView.Adapter {
        private final String[] imageUrls = new String[]{
                "http://eskipaper.com/images/stunning-landscape-wallpaper-3.jpg",
                "http://www.hdwallpaperspulse.com/wp-content/uploads/2016/05/28/cloudy-weather-beautiful-landscape-wallpaper.jpeg",
                "http://www.pixelstalk.net/wp-content/uploads/2016/04/Beautiful-backgrounds-landscape-wide.jpg",
                "http://eskipaper.com/images/stunning-landscape-wallpaper-2.jpg",
                "http://www.topphotoshow.com/wp-content/uploads/2013/10/1588894.jpg",
                "http://hdwallpaperbackgrounds.net/wp-content/uploads/2016/02/Stunning-Ocean-Landscape-Wallpaper.jpg",
                "http://www.caoping8.com/wp-content/uploads/2014/06/popular-landscaping-wonderful-beautiful-landscape-pictures-wallpaper-beautiful-yard-landscape-pictures-beautiful-front-yard-landscape-pictures-beautiful-landscape-pictures-world-beautiful-landscap.jpg",
                "http://files.vividscreen.info/soft/9190874678c0e1f1353d9eca8d2ac27a/Beautiful-Stunning-Landscape-1920x1200.jpg",
                "http://pic.yayabizhi.com:7704/uploads/120811/co120Q1160446-0.jpg",
                "http://photo.tp88.net/pic/20144/17/2014417753-44943459.jpg",
                "http://pic.yayabizhi.com:7704/uploads/120811/co120Q1161209-0.jpg",
                "http://s9.knowsky.com/bizhi/l/1-5000/200952816729919131814.jpg",
                "http://pic.yayabizhi.com:7704/uploads/120811/co120Q1161243-0.jpg",
                "http://photo.tp88.net/pic/20144/17/2014417750-76537947.jpg",
                "http://www.xxthemes.com/article/UploadPic/2011-11/201111232257836285.jpg",
                "http://image5.tuku.cn/wallpaper/Landscape%20Wallpapers/8294_2560x1600.jpg"
        };

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());

            return new RecyclerView.ViewHolder(imageView) {
            };
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            String imageUrl = imageUrls[position];

            if (holder.itemView instanceof ImageView) {

                ImageView imageView = (ImageView) holder.itemView;

                Glide.with(imageView.getContext())
                        .load(imageUrl)
                        .into(imageView);

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),
                            io.pillar.fullscreenimagegallery.MainActivity.class);

                    intent.putExtra(io.pillar.fullscreenimagegallery.MainActivity.EXTRA_IMAGE_URLS,
                            imageUrls);
                    intent.putExtra(io.pillar.fullscreenimagegallery.MainActivity.EXTRA_CURRENT_INDEX,
                            holder.getAdapterPosition());

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return imageUrls.length;
        }
    }
}
