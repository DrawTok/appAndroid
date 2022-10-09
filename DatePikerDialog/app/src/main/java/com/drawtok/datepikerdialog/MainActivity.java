package com.drawtok.datepikerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.id_edt_datetime);

        time.setOnClickListener(v -> pickTime());
    }

    private void pickTime()
    {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        DatePickerDialog datePicker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format;
            format = new SimpleDateFormat("dd/MM/yyyy");
            time.setText(format.format(calendar.getTime()));
        }, currentYear, currentMonth, today);
        datePicker.show();
    }
}