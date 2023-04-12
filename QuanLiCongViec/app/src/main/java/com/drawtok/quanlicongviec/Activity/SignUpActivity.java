package com.drawtok.quanlicongviec.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.drawtok.quanlicongviec.MainActivity;
import com.drawtok.quanlicongviec.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    AppCompatButton btnSignUp;
    EditText edtFullName, edtUser, edtPassword, edtConfirm;
    TextView tvLogin;
    String url = "http://192.168.31.244/forum/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Mapping();

        btnSignUp.setOnClickListener(v->
                isCheckInput());

        tvLogin.setOnClickListener(
                v-> startActivity(new Intent(this, LoginActivity.class)));

    }

    private void Mapping() {
        edtFullName         = findViewById(R.id.edt_FullName);
        edtUser             = findViewById(R.id.edt_regUser);
        edtPassword         = findViewById(R.id.edt_regPassword);
        edtConfirm          = findViewById(R.id.edt_confirm_password);
        btnSignUp           = findViewById(R.id.btn_sign_up);
        tvLogin             = findViewById(R.id.tv_sign_in);
    }

    private void isCheckInput()
    {
        String strName          = edtFullName.getText().toString().trim();
        String strUer           = edtUser.getText().toString().trim();
        String strPassword      = edtPassword.getText().toString().trim();
        String strConfirmPwd    = edtConfirm.getText().toString().trim();
        if(strName.equals("") || strUer.equals("") || strPassword.equals("") || strConfirmPwd.equals(""))
        {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else
        {
            if(strPassword.equals(strConfirmPwd))
            {
                //thành công
                insertData(strUer, strPassword, strName);
            }else
            {
                Toast.makeText(this, "Mật khẩu chưa trùng khớp. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void insertData(String strUer, String strPassword, String strName)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            if(response.equals("1"))
            {
                Toast.makeText(this,
                        "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }else
            {
                Toast.makeText(this,
                        "Lỗi tạo tài khoản. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show())
        {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", strUer);
                params.put("Password", strPassword);
                params.put("FullName", strName);
                return params;
            }
        };
        requestQueue.add(request);
    }
}