package com.drawtok.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        list = findViewById(R.id.list_Subject);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        list.setAdapter(adapter);

        addSubject();

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                popup_Delete_Subject(position, arrayList.get(position));
                return false;
            }
        });
    }

    private void popup_Delete_Subject(final int pos, final String course)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setMessage("Do you want to delete " +course+ " course?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void addSubject()
    {
        arrayList.add("OOP");
        arrayList.add("IOS");
        arrayList.add("Android");
        arrayList.add("Lập trình căn bản");
        arrayList.add("Frontend");
        arrayList.add("Backend");
    }
}