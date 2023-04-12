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

public class UpdateSVActivity extends AppCompatActivity {

    EditText updateEdtHoTen, updateEdtNamSinh, updateEdtDiaChi;
    Button uBtnUpdate, uBtnUCancel;
    int id = 0;
    String urlUpdate = "http://drawtokappdev.atwebpages.com/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_svactivity);

        AnhXa();

        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSV");

        id = sinhVien.getId();
        updateEdtHoTen.setText(sinhVien.getHoten());
        updateEdtNamSinh.setText(sinhVien.getNamsinh()+"");
        updateEdtDiaChi.setText(sinhVien.getDiachi());
        
        uBtnUpdate.setOnClickListener(v->{
            String hoten = updateEdtHoTen.getText().toString().trim();    
            String namsinh = updateEdtNamSinh.getText().toString().trim();    
            String diachi = updateEdtDiaChi.getText().toString().trim();    
        
            if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty())
            {
                Toast.makeText(this, "Bạn phải điền đầy đủ thông tin...", Toast.LENGTH_SHORT).show();
            }else
            {
                CapNhatSV(urlUpdate);
            }
            
        });

    }

    private void CapNhatSV(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Successful"))
                {
                    Toast.makeText(UpdateSVActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateSVActivity.this, MainActivity.class));
                }else
                {
                    Toast.makeText(UpdateSVActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateSVActivity.this, "Xảy ra lỗi. Vui lòng thử lại...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params;
                params = new HashMap<>();

                params.put("idSV", String.valueOf(id));
                params.put("hotenSV", updateEdtHoTen.getText().toString().trim());
                params.put("namsinhSV", updateEdtNamSinh.getText().toString().trim());
                params.put("diachiSV", updateEdtDiaChi.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {

        updateEdtHoTen = findViewById(R.id.update_edt_HoTen);
        updateEdtNamSinh = findViewById(R.id.update_edt_NamSinh);
        updateEdtDiaChi = findViewById(R.id.update_edt_DiaChi);
        uBtnUpdate = findViewById(R.id.btn_Update);
        uBtnUCancel = findViewById(R.id.update_btn_Cancel);

    }
}