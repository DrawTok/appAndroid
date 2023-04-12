package com.drawtok.fragmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = findViewById(R.id.btn_change_tvFragmentA);

        btnChange.setOnClickListener(v ->
        {
        });
    }

    public void AddFrameLayout(View v)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (v.getId())
        {
            case R.id.btn_fragment_1:
                fragment = new FragmentA();
                break;
            case R.id.btn_fragment_2:
                fragment = new FragmentB();
                break;
        }
        fragmentTransaction.add(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }
}