package com.drawtok.quanlicongviec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.drawtok.quanlicongviec.ActiFragment.HomeFragment;
import com.drawtok.quanlicongviec.ActiFragment.NotificationFragment;
import com.drawtok.quanlicongviec.ActiFragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();
        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item ->
        {

            switch (item.getItemId())
            {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_notification:
                    replaceFragment(new NotificationFragment());
                    break;
                case R.id.nav_user:
                    replaceFragment(new UserFragment());
                    break;
            }

            return true;
        });
    }

    private void Mapping() {
        bottomNavigationView = findViewById(R.id.nav);
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }
}