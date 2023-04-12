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

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser, edtPassword;
    private AppCompatButton buttonLogin;
    private TextView tvCreateNewAccount, tvForgotPassword;

    String url = "http://192.168.31.244/forum/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mapping();

        buttonLogin.setOnClickListener(v->
                isCheckInput());

        tvCreateNewAccount.setOnClickListener(v->
                startActivity(new Intent(this, SignUpActivity.class)));

        tvForgotPassword.setOnClickListener(v->
                Toast.makeText(this, "Quên mật khẩu", Toast.LENGTH_SHORT).show());
    }

    private void Mapping() {
        edtUser             = findViewById(R.id.edt_user);
        edtPassword         = findViewById(R.id.edt_password);
        buttonLogin         = findViewById(R.id.btn_login);
        tvForgotPassword    = findViewById(R.id.forgot_pwd);
        tvCreateNewAccount  = findViewById(R.id.sign_up);
    }

    // Kiểm tra trường user và password
    private void isCheckInput()
    {
        String strUer       = edtUser.getText().toString().trim();
        String strPassword  = edtPassword.getText().toString().trim();
        if(strUer.equals("") || strPassword.equals(""))
        {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else
        {
            isAuth(strUer, strPassword);
        }
    }

    //Xác thực tài khoản
    private void isAuth(String strUser, String strPassword) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            if(response.equals("1"))
            {
                Toast.makeText(LoginActivity.this,
                        "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }else
            {
                Toast.makeText(LoginActivity.this,
                        "Tên tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show())
        {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", strUser);
                params.put("Password", strPassword);
                params.put("FullName", "");
                return params;
            }
        };
        requestQueue.add(request);
    }
}