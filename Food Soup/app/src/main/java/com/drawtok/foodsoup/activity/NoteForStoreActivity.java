package com.drawtok.foodsoup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.drawtok.foodsoup.R;
import com.google.android.material.button.MaterialButton;

public class NoteForStoreActivity extends AppCompatActivity {

    ImageView imvClose;
    EditText edtNote;
    MaterialButton buttonAddNote;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_for_store);
        Mapping();

        Intent intent = getIntent();
        text = intent.getStringExtra("noteForStore");
        edtNote.setText(text);

        imvClose.setOnClickListener(v->finish());

        buttonAddNote.setOnClickListener(v->
        {
            text = edtNote.getText().toString();
            Intent intentToOrder = new Intent(this, OrderActivity.class);
            intentToOrder.putExtra("note", text);
            setResult(RESULT_OK, intentToOrder);
            finish();
        });
    }

    private void Mapping() {
        imvClose        = findViewById(R.id.imv_close);
        edtNote         = findViewById(R.id.edt_note);
        buttonAddNote   = findViewById(R.id.btn_add_note);
    }
}