package com.example.gestionbibliotheque.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionbibliotheque.DB.DataBaseHelper;
import com.example.gestionbibliotheque.R;

import java.util.ArrayList;

public class DeleteBookActivity extends AppCompatActivity {
    Button bBack, bDelete;
    EditText ETDelete;
    String title;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        bBack = findViewById(R.id.buttonBack7);
        bDelete = findViewById(R.id.buttonDelete);
        ETDelete = findViewById(R.id.editTextDeleteBook);

        DB = new DataBaseHelper(this);

        bDelete.setOnClickListener(View -> {
            title = ETDelete.getText().toString();
            if (title.isEmpty()) Toast.makeText(DeleteBookActivity.this, "Veuillez renseignez tous les champs", Toast.LENGTH_SHORT).show();
            else {
                Cursor res = DB.getBookByTitle(title);
                if (res.getCount() != 0) {
                    ArrayList<String> idBook = new ArrayList<>();
                    while (res.moveToNext()) {
                        idBook.add(res.getString(0));
                    }
                    Integer nbLigne = DB.deleteBook(idBook.get(0));
                    Toast.makeText(DeleteBookActivity.this, nbLigne + " Delete", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DeleteBookActivity.this, "Ce livre n'existe pas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bBack.setOnClickListener(View -> {
            Intent i = new Intent(getApplicationContext(), ManageBookActivity.class);
            startActivity(i);
            finish();
        });
    }
}