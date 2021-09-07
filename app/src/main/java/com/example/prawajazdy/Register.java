package com.example.prawajazdy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;

    EditText email, password, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Register.this, Login.class));
            finish();
        }
    }

    public void toLoginPage(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }

    public void register(View view) {
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

        mAuth.createUserWithEmailAndPassword(emailData, passwordData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "User created",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    } else {
                        Toast.makeText(Register.this, "Error! " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}