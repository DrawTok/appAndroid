package com.drawtok.foodsoup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.drawtok.foodsoup.R;
import com.drawtok.foodsoup.Utils.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername, edtPhone, edtPassword, edtConfirmPwd;
    MaterialButton btnSignUp;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Mapping();

        btnSignUp.setOnClickListener(v->
        {
            String username = edtUsername.getText().toString();
            String phone = edtPhone.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPass = edtConfirmPwd.getText().toString();
            if(!checkInput(username, phone, password, confirmPass))
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            else
            {
                if(!password.equals(confirmPass))
                    Toast.makeText(this, "Mật khẩu không trùng khớp. Vui lòng thử lại...", Toast.LENGTH_SHORT).show();
                else
                    createAccount(username, phone, password);

            }
        });

        tvLogin.setOnClickListener(v->startActivity(new Intent(this, LoginActivity.class)));
    }

    private void createAccount(String username, String phone, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, Utils.BASE_URL, response -> {
            if(response.equals("1"))
            {
                Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }else
                Toast.makeText(this, "Tạo tài khoản không " +
                        "thành công. Vui lòng thử lại...", Toast.LENGTH_SHORT).show();

        }, error -> Toast.makeText(this, "Có lỗi xảy ra. Vui lòng thử lại...", Toast.LENGTH_SHORT).show())
        {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("PhoneNumber", phone);
                params.put("Password", password);
                return params;
            }
        };
        requestQueue.add(request);
    }

    private boolean checkInput(String username, String phone, String password, String confirmPass) {

        return !username.equals("") && !phone.equals("") &&
                !password.equals("") && !confirmPass.equals("");

    }

    private void Mapping() {
        edtUsername     = findViewById(R.id.edt_user_name);
        edtPhone        = findViewById(R.id.edt_num_phone);
        edtPassword     = findViewById(R.id.edt_password);
        edtConfirmPwd   = findViewById(R.id.edt_confirm_password);
        btnSignUp       = findViewById(R.id.btn_sign_up);
        tvLogin         = findViewById(R.id.tv_login);
    }
}