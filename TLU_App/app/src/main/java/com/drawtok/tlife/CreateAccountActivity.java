package com.drawtok.tlife;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;


public class CreateAccountActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword, edtConfirmPwd;
    MaterialButton btnSignUp;
    TextView tv_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mapping();

        tv_SignIn.setOnClickListener(v -> startActivity
                (new Intent(CreateAccountActivity.this, SignInActivity.class)));

        btnSignUp.setOnClickListener(v->createAccount());
    }

    private void createAccount() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    }


    private void mapping() {
        edtPhone = findViewById(R.id.numberPhone_edt);
        edtPassword = findViewById(R.id.password_edt);
        edtConfirmPwd = findViewById(R.id.confirm_pwd_edt);
        btnSignUp = findViewById(R.id.btn_Create_account);
        tv_SignIn = findViewById(R.id.tv_sign_in);
    }
}