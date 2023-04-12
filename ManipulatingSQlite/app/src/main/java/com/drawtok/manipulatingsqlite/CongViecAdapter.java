package com.drawtok.manipulatingsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CongViecAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<CongViec> listCongViec;

    public CongViecAdapter(Context context, int layout, ArrayList<CongViec> listCongViec) {
        this.context = context;
        this.layout = layout;
        this.listCongViec = listCongViec;
    }

    @Override
    public int getCount() {
        return listCongViec.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.tvNote = convertView.findViewById(R.id.tv_note);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CongViec congViec = listCongViec.get(position);
        viewHolder.tvNote.setText(congViec.getTitle());

        return convertView;
    }
}
