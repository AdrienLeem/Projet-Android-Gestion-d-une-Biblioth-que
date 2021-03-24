package com.example.gestionbibliotheque;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.gestionbibliotheque.Auth.LoginActivity;
import com.example.gestionbibliotheque.Auth.RegisterActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button bRegister = findViewById(R.id.buttonRegister);
        Button bLogin = findViewById(R.id.buttonLogin);

        bRegister.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
            finish();
        });

        bLogin.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}