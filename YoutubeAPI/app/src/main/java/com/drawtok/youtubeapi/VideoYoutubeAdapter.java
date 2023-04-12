package com.drawtok.youtubeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoYoutubeAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final List<Video> videoList;

    public VideoYoutubeAdapter(Context context, int layout, List<Video> videoList) {
        this.context = context;
        this.layout = layout;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder
    {
        ImageView imgVideo;
        TextView tvTitleVideo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgVideo = convertView.findViewById(R.id.img_Video);
            holder.tvTitleVideo = convertView.findViewById(R.id.tv_title_Video);

            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Video video = videoList.get(position);
        holder.tvTitleVideo.setText(video.getTitle());

        Picasso.get().load(video.getThumbnail()).into(holder.imgVideo);

        return convertView;
    }
}
