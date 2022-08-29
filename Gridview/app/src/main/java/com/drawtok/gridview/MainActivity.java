package com.drawtok.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    ArrayList<GridViewImages> gridViewImages;
    AdapterGridView adapterGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        adapterGridView = new AdapterGridView(this,
                R.layout.layout_gridview_img, gridViewImages);

        grid.setAdapter(adapterGridView);

    }

    private void AnhXa() {
        grid = findViewById(R.id.gridView);
        gridViewImages = new ArrayList<>();
        gridViewImages.add(new GridViewImages(R.drawable.ad));
        gridViewImages.add(new GridViewImages(R.drawable.ar));
        gridViewImages.add(new GridViewImages(R.drawable.id));
        gridViewImages.add(new GridViewImages(R.drawable.hk));
        gridViewImages.add(new GridViewImages(R.drawable.hq));
        gridViewImages.add(new GridViewImages(R.drawable.ir));
        gridViewImages.add(new GridViewImages(R.drawable.lb));
        gridViewImages.add(new GridViewImages(R.drawable.sg));
        gridViewImages.add(new GridViewImages(R.drawable.tl));
        gridViewImages.add(new GridViewImages(R.drawable.vn));
        gridViewImages.add(new GridViewImages(R.drawable.ym));
    }
}