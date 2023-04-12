package com.drawtok.drawtoknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout searchBar;
    RelativeLayout toolBar;
    EditText searchNote;
    ImageView btnClose, btnSelectMore;
    TextView tvNumberSelect;
    ImageButton btnAddNote;
    ListView listNote;
    public static ArrayList<Note> noteArrayList;
    public static NoteAdapter noteAdapter;
    public static DatabaseNote databaseNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh Xạ
        Mapping();
        noteArrayList = new ArrayList<>();
        btnAddNote.setOnClickListener(v->
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class)));
        noteAdapter = new NoteAdapter(MainActivity.this, R.layout.layout_note, noteArrayList);
        listNote.setAdapter(noteAdapter);

        listNote.setOnItemLongClickListener((parent, view, position, id) -> {
            searchNote.setVisibility(View.INVISIBLE);
            toolBar.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.long_click_item_custom);
            String title = null;
            databaseNote.QueryData("DELETE FROM GhiChu WHERE title = '"+title+"'");
            return true;
        });

        btnClose.setOnClickListener(v->
        {
            searchNote.setVisibility(View.VISIBLE);
            toolBar.setVisibility(View.INVISIBLE);
        });
    }

    private void Mapping() {
        searchBar = findViewById(R.id.search_bar);
        toolBar = findViewById(R.id.tool_bar);
        searchNote = findViewById(R.id.search_note);
        btnClose = findViewById(R.id.btn_close);
        btnSelectMore = findViewById(R.id.btn_select_more);
        tvNumberSelect = findViewById(R.id.select_note_number);
        btnAddNote = findViewById(R.id.btn_add);
        listNote = findViewById(R.id.ls_note);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseNote = new DatabaseNote(this, "note.sqlite", null, 1);
        //TẠO BẢNG
        databaseNote.QueryData("CREATE TABLE IF NOT EXISTS GhiChu" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title VARCHAR(200)," +
                " content VARCHAR(200))");
        showDataToScreen();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showDataToScreen();
    }

    private void showDataToScreen()
    {
        Cursor dataNote = databaseNote.GetData("SELECT * FROM GhiChu");
        noteArrayList.clear();
        while (dataNote.moveToNext())
        {
            String title =dataNote.getString(1);
            String content = dataNote.getString(2);
            noteArrayList.add(new Note(title, content));
        }
        noteAdapter.notifyDataSetChanged();
    }

}