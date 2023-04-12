package com.drawtok.imagepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class pickImageActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        tableLayout = findViewById(R.id.table_image_answer);

        Collections.shuffle(MainActivity.arrayList);
        int row = 3, col = 3;
        for (int i = 0; i < row; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < col; j++) {
                int pos = col * (i) + j;
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(200, 200);
                imageView.setLayoutParams(params);
                int idImage = getResources().getIdentifier(
                        MainActivity.arrayList.get(pos), "drawable", getPackageName());
                imageView.setImageResource(idImage);
                tableRow.addView(imageView);

                imageView.setOnClickListener(v ->
                {
                    Intent intent = new Intent();
                    intent.putExtra("namePicked", MainActivity.arrayList.get(pos));
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }
            tableLayout.addView(tableRow);
        }
    }
}