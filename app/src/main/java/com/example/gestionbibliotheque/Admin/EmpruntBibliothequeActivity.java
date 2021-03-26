package com.example.gestionbibliotheque.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionbibliotheque.R;

public class EmpruntBibliothequeActivity extends AppCompatActivity {
    Button bBack, bMap;
    EditText ETVille;
    String ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunt_bibliotheque);

        bBack = findViewById(R.id.buttonBack12);
        bMap = findViewById(R.id.buttonEVille);
        ETVille = findViewById(R.id.editTextVille);

        bBack.setOnClickListener(View -> {
            Intent i = new Intent(getApplicationContext(), ManageStockActivity.class);
            startActivity(i);
            finish();
        });

        bMap.setOnClickListener(View -> {
            ville = ETVille.getText().toString();
            if (ville.isEmpty()) Toast.makeText(EmpruntBibliothequeActivity.this, "Veuillez renseigner tous les champs", Toast.LENGTH_SHORT).show();
            else {
                String map = "https://www.google.fr/maps/search/bibliotheque+" + ville;
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(map));
                startActivity(i);
                finish();
            }
        });
    }
}