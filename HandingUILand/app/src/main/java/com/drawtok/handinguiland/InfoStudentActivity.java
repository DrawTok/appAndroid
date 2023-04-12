package com.drawtok.handinguiland;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InfoStudentActivity extends AppCompatActivity {

    TextView tvAcID, tvAcName, tvAcBirthYear, tvAcAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_student);

        tvAcID = findViewById(R.id.tv_ac_id);
        tvAcName = findViewById(R.id.tv_ac_name);
        tvAcBirthYear = findViewById(R.id.tv_ac_birthYear);
        tvAcAddress     = findViewById(R.id.tv_ac_address);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("dataStudent");

        tvAcID.setText("ID: "+student.getIdStudent());
        tvAcName.setText("Họ tên: "+student.getName());
        tvAcBirthYear.setText("Năm sinh: "+student.getBirthYear());
        tvAcAddress.setText("Địa chỉ: "+student.getAddress());
    }
}