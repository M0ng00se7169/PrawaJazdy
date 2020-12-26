package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MenuActivity.class));
            finish();
        }
    }

    public void toRegisterPage(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    public void login(View view) {
        String emailData = email.getText().toString().trim();
        String passwordData = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailData)) {
            email.setError("Email is required!");
            return;
        }
        if (!emailData.contains("@") && !emailData.contains(".")) {
            email.setError("Provide real email address.");
            return;
        }
        if (passwordData.length() < 8) {
            password.setError("Provide strong password with at least 8 symbols.");
            return;
        }

        mAuth.signInWithEmailAndPassword(emailData, passwordData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Logged in successfully",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MenuActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Error! " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}