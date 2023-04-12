package com.drawtok.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddSVActivity extends AppCompatActivity {

    EditText edtHoten, edtNamSinh, edtDiaChi;
    Button btnAdd, btnCancel;
    String urlInsert = "http://drawtokappdev.atwebpages.com/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_svactivity);

        AnhXa();

        btnAdd.setOnClickListener(v->
        {
            String hoten = edtHoten.getText().toString().trim();
            String namsinh = edtNamSinh.getText().toString().trim();
            String diachi = edtDiaChi.getText().toString().trim();
            if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty())
            {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }else
            {
                ThemSinhVien(urlInsert);
            }
        });

        btnCancel.setOnClickListener(v-> finish());
    }

    private void ThemSinhVien(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Successful"))
                {
                    Toast.makeText(AddSVActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddSVActivity.this, MainActivity.class));
                }else
                {
                    Toast.makeText(AddSVActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddSVActivity.this, "Đã xảy ra lỗi!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("hotenSV", edtHoten.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {

        edtHoten = findViewById(R.id.edt_HoTen);
        edtNamSinh = findViewById(R.id.edt_NamSinh);
        edtDiaChi = findViewById(R.id.edt_DiaChi);
        btnAdd = findViewById(R.id.btn_Add);
        btnCancel = findViewById(R.id.btn_Cancel);

    }
}