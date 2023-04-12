package com.drawtok.foodsoup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.drawtok.foodsoup.Model.Photo;
import com.drawtok.foodsoup.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private final List<Photo> listPhoto;
    Context context;
    ViewPager2 viewPager;

    public PhotoAdapter(Context context, List<Photo> listPhoto, ViewPager2 viewPager) {
        this.listPhoto = listPhoto;
        this.context = context;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = listPhoto.get(position);
        if(position == listPhoto.size()-2)
        {
            viewPager.post(runnable);
        }
        Glide.with(context).load(photo.getUrlBanner()).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        if(listPhoto != null)
        {
            return listPhoto.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            listPhoto.addAll(listPhoto);
            notifyDataSetChanged();
        }
    };
}
