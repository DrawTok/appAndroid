package com.drawtok.handinguiland;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements TransmissionDataStd {

    FragmentInfo fragmentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentStudentList fragmentStudentList = new FragmentStudentList();
        if(fragmentTransaction == null)
        {
            fragmentTransaction.add(R.id.frame_Layout_Student, fragmentStudentList);
        }else
        {
            fragmentTransaction.replace(R.id.frame_Layout_Student, fragmentStudentList);
        }

        fragmentTransaction.commit();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            FragmentManager fragmentManager1 = getFragmentManager();
            fragmentInfo = new FragmentInfo();
            FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
            fragmentTransaction1.add(R.id.frame_Layout_infoStudent, fragmentInfo);
            fragmentTransaction1.commit();
        }
    }

    @Override
    public void TransmissionData(Student std) {
        if(fragmentInfo != null)
        {
            fragmentInfo.setInfo(std);
        }else
        {
            Intent intent = new Intent(MainActivity.this, InfoStudentActivity.class);
            intent.putExtra("dataStudent", std);
            startActivity(intent);
        }
    }
}