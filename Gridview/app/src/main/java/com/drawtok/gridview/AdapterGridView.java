package com.drawtok.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class AdapterGridView extends BaseAdapter
{
    private  final Context context;
    private final  int layout;
    private final List<GridViewImages> gridViewImagesList;

    public AdapterGridView(Context context, int layout, List<GridViewImages> gridViewImagesList) {
        this.context = context;
        this.layout = layout;
        this.gridViewImagesList = gridViewImagesList;
    }


    @Override
    public int getCount() {
        return gridViewImagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder
    {
        ImageView imgGrid;
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

            holder.imgGrid = convertView.findViewById(R.id.img_GridView);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        GridViewImages viewImages = gridViewImagesList.get(position);
        holder.imgGrid.setImageResource(viewImages.getImage());

        return convertView;
    }
}
