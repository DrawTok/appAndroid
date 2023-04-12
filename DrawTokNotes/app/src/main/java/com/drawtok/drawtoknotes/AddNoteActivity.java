package com.drawtok.drawtoknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    EditText edtTitle, edtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtTitle = findViewById(R.id.edt_title);
        edtContent = findViewById(R.id.edt_content);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        if((content==null && title == null) || (content == null && title != null))
        {
            edtContent.setError("Content is required");
        }else
        {
            MainActivity.databaseNote.QueryData("INSERT INTO GhiChu VALUES(null, '"+title+"', '"+content+"')");
        }

        //Lấy dữ liệu từ database đổ ra màn hình
        Cursor dataNote = MainActivity.databaseNote.GetData("SELECT * FROM GhiChu");
        while (dataNote.moveToNext())
        {
            title =dataNote.getString(1);
            content = dataNote.getString(2);
            MainActivity.noteArrayList.add(new Note(title, content));
        }
        MainActivity.noteAdapter.notifyDataSetChanged();
    }

}