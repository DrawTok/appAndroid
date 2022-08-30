package com.drawtok.customalert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtView_login);

        txtLogin.setOnClickListener(v -> DialogLogin());
    }

    private void DialogLogin()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtUsername = dialog.findViewById(R.id.id_username);
        final  EditText edtPassword = dialog.findViewById(R.id.id_password);
        Button btnContinue = dialog.findViewById(R.id.id_btn_continue);
        Button btnCancel = dialog.findViewById(R.id.id_btn_cancel);

        btnContinue.setOnClickListener(v ->
        {
            String user = edtUsername.getText().toString().trim();
            String passwd = edtPassword.getText().toString().trim();
            if(user.equals("hoa") && passwd.equals("123"))
            {
                dialog.dismiss();
                Toast.makeText(this, "Đăng nhập thành công!",
                        Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu @@",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v ->
                dialog.dismiss());
        dialog.show();
    }

}