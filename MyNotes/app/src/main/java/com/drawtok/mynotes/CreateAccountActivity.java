package com.drawtok.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword, edtConfirmPwd;
    Button btnSignUp;
    ProgressBar progressBar;
    TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Mapping();

        btnSignUp.setOnClickListener(v -> CreateAccount());
        btnSignIn.setOnClickListener(v -> startActivity(new Intent(
                CreateAccountActivity.this, SignInActivity.class)));
    }

    private void CreateAccount() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPwd = edtConfirmPwd.getText().toString();

        boolean isValidate = validateData(email, password, confirmPwd);
        if(!isValidate)
        {
            return;
        }
        CreateAccountInFirebase(email, password);
    }

    private void CreateAccountInFirebase(String email, String password) {
        changeInProgress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful())
                {
                    Utility.showToast(CreateAccountActivity.this, "Successfully " +
                            "create account, Check email to verify");
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }else
                {
                    Utility.showToast(CreateAccountActivity.this,
                            task.getException().getLocalizedMessage());
                }
            }
        });
    }

    private void changeInProgress(boolean inProgress)
    {
        if(inProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.GONE);
        }else
        {
            progressBar.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password, String confirmPwd)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Email is invalid");
            return false;
        }
        if(password.length() < 6)
        {
            edtPassword.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmPwd))
        {
            edtConfirmPwd.setError("Password not matched");
        }
        return true;
    }

    private void Mapping() {
        edtEmail = findViewById(R.id.email_edt);
        edtPassword = findViewById(R.id.password_edt);
        edtConfirmPwd = findViewById(R.id.confirm_password_edt);
        btnSignUp = findViewById(R.id.sign_up_btn);
        progressBar = findViewById(R.id.progress_bar);
        btnSignIn = findViewById(R.id.login_tv_btn);
    }
}