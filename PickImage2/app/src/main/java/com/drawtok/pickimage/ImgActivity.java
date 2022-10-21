package com.drawtok.pickimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImgActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        tableLayout = findViewById(R.id.table_img);

        Collections.shuffle(MainActivity.arrayList);

        int row = 3, col = 3;
        for(int i = 1; i <= row; i++)
        {
            TableRow tableRow = new TableRow(this);
            for(int j = 1; j <= col; j++)
            {
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(200, 200);
                imageView.setLayoutParams(layoutParams);
                int pos = col * (i - 1) + j - 1;
                int idImg = getResources().getIdentifier(MainActivity.arrayList.get(pos),
                        "drawable", getPackageName());

                imageView.setImageResource(idImg);
                //thêm imageView vào tableRow
                tableRow.addView(imageView);

                imageView.setOnClickListener(v ->
                {
                    Intent intent = new Intent();
                    intent.putExtra("nameImgPick", MainActivity.arrayList.get(pos));
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }
            tableLayout.addView(tableRow);
        }
    }
}