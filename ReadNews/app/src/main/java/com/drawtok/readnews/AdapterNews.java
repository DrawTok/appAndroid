package com.drawtok.readnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterNews extends BaseAdapter {
    private Context context;
    private int layout;
    private List<News> newsList;

    public AdapterNews(Context context, int layout, List<News> newsList)
    {
        this.context = context;
        this.layout = layout;
        this.newsList = newsList;
    }
    @Override
    public int getCount() {
        return newsList.size();
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
        ImageView imgContent;
        TextView tvTitle, tvTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgContent = convertView.findViewById(R.id.img_content);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        News news = newsList.get(position);
        Picasso.get().load(news.getLinkImage()).into(viewHolder.imgContent);
        viewHolder.tvTitle.setText(news.getTitle());
        viewHolder.tvTime.setText(news.getTime());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_layout_in);
        convertView.startAnimation(animation);
        return convertView;
    }
}
