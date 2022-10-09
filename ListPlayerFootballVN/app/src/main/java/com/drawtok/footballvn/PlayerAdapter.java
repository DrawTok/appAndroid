package com.drawtok.footballvn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<PlayerInfo> playerInfoList;

    public PlayerAdapter(Context context, int layout, List<PlayerInfo> playerInfoList) {
        this.context = context;
        this.layout = layout;
        this.playerInfoList = playerInfoList;
    }

    @Override
    public int getCount() {
        return playerInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView imgPlayer     = convertView.findViewById(R.id.image_player);
        ImageView imgFlag       = convertView.findViewById(R.id.imv_flag);
        TextView tvNamePlayer   = convertView.findViewById(R.id.tv_name_player);
        TextView tvDob          = convertView.findViewById(R.id.tv_dob);

        PlayerInfo playerInfo = playerInfoList.get(position);
        imgPlayer.setImageResource(playerInfo.getImgPlayer());
        imgFlag.setImageResource(playerInfo.getImgFlag());
        tvNamePlayer.setText(playerInfo.getName());
        tvDob.setText(playerInfo.getDob());

        return convertView;
    }
}
