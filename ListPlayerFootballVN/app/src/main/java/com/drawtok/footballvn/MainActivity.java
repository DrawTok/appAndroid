package com.drawtok.footballvn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lsPlayer;
    ArrayList<PlayerInfo> playerInfoArrayList;
    PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInList();
        lsPlayer = findViewById(R.id.list_view_player);
        playerAdapter = new PlayerAdapter(MainActivity.this,
                R.layout.custom_item_listview, playerInfoArrayList);
        lsPlayer.setAdapter(playerAdapter);
    }

    private void addInList() {
        playerInfoArrayList = new ArrayList<>();
        playerInfoArrayList.add(new PlayerInfo("Nguyễn Quang Hải", "12/4/1997",
                R.drawable.quanghai, R.drawable.qk));
        playerInfoArrayList.add(new PlayerInfo("Đỗ Hùng Dũng", "8/9/1993",
                R.drawable.hungdung, R.drawable.qk));
        playerInfoArrayList.add(new PlayerInfo("Đặng Văn Lâm", "13/8/1993",
                R.drawable.vanlam, R.drawable.qk));
    }


}