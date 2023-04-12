package com.drawtok.sqliteimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final List<item> itemList;

    public ItemsAdapter(Context context, int layout, List<item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
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
        ImageView imgItem;
        TextView tvDes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgItem = convertView.findViewById(R.id.img_item);
            viewHolder.tvDes   = convertView.findViewById(R.id.tv_des);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        item item = itemList.get(position);

        //chuyển byte[] -> bitmap
        byte[] imgByte = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        viewHolder.imgItem.setImageBitmap(bitmap);

        viewHolder.tvDes.setText(item.getDes());

        return convertView;
    }
}
