package com.drawtok.countingdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtFirstTime, edtSecondTime;
    Button btnAccept;
    TextView tvNumOfDays;
    Calendar calendarSecond, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        edtFirstTime.setOnClickListener(v ->PickFirstDate());
        edtSecondTime.setOnClickListener(v ->PickSecondDate());
        btnAccept.setOnClickListener(v -> displayNumOfDay());
    }

    private void PickSecondDate() {
        calendarSecond = Calendar.getInstance();
        int currentYear = calendarSecond.get(Calendar.YEAR);
        int currentMonth = calendarSecond.get(Calendar.MONTH);
        int today = calendarSecond.get(Calendar.DATE);

        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarSecond.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                edtSecondTime.setText(simpleDateFormat.format(calendarSecond.getTime()));
            }
        }, currentYear, currentMonth, today);
        pickerDialog.show();
    }

    private void displayNumOfDay()
    {
        int numDays = (int) ((calendarSecond.getTimeInMillis() -
                calendar.getTimeInMillis())/(1000*24*3600));

        if(numDays < 0)
        {
            Toast.makeText(this, "Vui lòng chọn ngày thứ 2 sau ngày thứ nhất",
                    Toast.LENGTH_SHORT).show();
        }else
        {
            tvNumOfDays.setText("Số ngày hiện tại là: "+numDays);
        }
    }

    private void AnhXa() {
        edtFirstTime = findViewById(R.id.first_time);
        edtSecondTime = findViewById(R.id.second_time);
        btnAccept = findViewById(R.id.btn_Accept);
        tvNumOfDays = findViewById(R.id.tv_current_time);
    }

    private void PickFirstDate()
    {
        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int today = calendar.get(Calendar.DATE);

        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtFirstTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, currentYear, currentMonth, today);
        pickerDialog.show();
    }
}