package com.example.thuchanh02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        TextView tvErrorMsg = findViewById(R.id.tvErrorMsg);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                tvErrorMsg.setText("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu");
                tvErrorMsg.setVisibility(View.VISIBLE);
            } else if (username.equals("admin") && password.equals("123456")) {
                tvErrorMsg.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                tvErrorMsg.setText("Sai tên đăng nhập hoặc mật khẩu");
                tvErrorMsg.setVisibility(View.VISIBLE);
            }
        });
    }
}