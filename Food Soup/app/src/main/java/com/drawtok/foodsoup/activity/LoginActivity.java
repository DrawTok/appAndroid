package com.drawtok.foodsoup.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.drawtok.foodsoup.Model.User;
import com.drawtok.foodsoup.R;
import com.drawtok.foodsoup.Retrofit.ApiUser;
import com.drawtok.foodsoup.Retrofit.RetrofitClient;
import com.drawtok.foodsoup.Utils.Utils;
import com.google.android.material.button.MaterialButton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignUp;
    EditText edtNumPhone, edtPassword;
    MaterialButton btnLogin;
    ApiUser apiUser;
    User user;
    SharedPreferences sharedPreferences;
    boolean isFirstRun;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Mapping();

        sharedPreferences = getSharedPreferences("infoUser", MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if(isFirstRun)
        {
            btnLogin.setOnClickListener(v->
            {
                String phone = edtNumPhone.getText().toString();
                String password = edtPassword.getText().toString();

                if(phone.length() < 10 || password.equals(""))
                {
                    Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else
                {
                    isCheckLogin(phone, password);
                }
            });
        }else
        {
            startActivity(new Intent(this, MainActivity.class));
        }



        tvSignUp.setOnClickListener(v->startActivity(new Intent(this, SignUpActivity.class)));
    }

    private void isCheckLogin(String phone, String password) {

        apiUser = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiUser.class);

        compositeDisposable.add(apiUser.sendData("", phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel ->
                        {
                            if(userModel != null && userModel.getSuccess())
                            {
                                user = userModel.getResult();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isFirstRun", false).apply();
                                editor.putString("Username", user.getUsername());
                                editor.putString("PhoneNumber", user.getPhoneNumber());
                                editor.putString("GenderUser", user.getGenderUser());
                                editor.apply();
                                startActivity(new Intent(this, MainActivity.class));
                            }else
                                Toast.makeText(this,
                                        "Tài khoản hoặc mật khẩu không chính xác" +
                                                ", Vui lòng thử lại...", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void Mapping() {
        edtNumPhone = findViewById(R.id.edt_num_phone);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin    = findViewById(R.id.btn_login);
        tvSignUp  = findViewById(R.id.tv_sign_up);
    }
}