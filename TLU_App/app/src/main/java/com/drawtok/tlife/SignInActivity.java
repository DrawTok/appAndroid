package com.drawtok.tlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

        ImageView btnSignInWithGG, btnSignInWithFB;

        GoogleSignInOptions ggo;
        GoogleSignInClient ggc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignInWithGG = findViewById(R.id.btn_signIn_gg);

        ggo = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail().build();
        ggc = GoogleSignIn.getClient(this, ggo);
        btnSignInWithGG.setOnClickListener(v -> signInWithGG());

    }

    private void signInWithGG() {
        Intent signInIntent = ggc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Utility.showToast(getApplicationContext(), "Something went wrong");
            }
        }
    }
    private void navigateToSecondActivity() {
        finish();
        startActivity(new Intent(SignInActivity.this, MainActivity.class));

    }
}