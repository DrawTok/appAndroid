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

public class SignInActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    ProgressBar progressBar;
    TextView btnCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Mapping();
        btnLogin.setOnClickListener((v)->loginUser());
        btnCreateAccount.setOnClickListener((v) -> startActivity(new Intent(
                SignInActivity.this, CreateAccountActivity.class)));
    }

    private void loginUser() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        boolean isValidate = validateData(email, password);
        if(!isValidate)
        {
            return;
        }
        loginAccountInFirebase(email, password);
    }

    private void loginAccountInFirebase(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if(task.isSuccessful())
                        {
                            if(firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                startActivity(new Intent(SignInActivity.this,
                                        MainActivity.class));
                            }else
                            {
                                Utility.showToast(SignInActivity.this,
                                        "Email not verified, Please verify your email.");
                            }
                        }else
                        {
                            Utility.showToast(SignInActivity.this,
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
            btnLogin.setVisibility(View.GONE);
        }else
        {
            progressBar.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password)
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
        return true;
    }

    private void Mapping() {
        edtEmail = findViewById(R.id.email_edt);
        edtPassword = findViewById(R.id.password_edt);
        btnLogin = findViewById(R.id.sign_in_btn);
        progressBar = findViewById(R.id.progress_bar);
        btnCreateAccount = findViewById(R.id.sign_up_tv);
    }
}