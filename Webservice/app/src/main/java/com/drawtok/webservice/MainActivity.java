package com.drawtok.webservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listSV;
    ArrayList<SinhVien> sinhVienArrayList;
    SinhVienAdapter adapter;
    String urlGetData = "http://drawtokappdev.atwebpages.com/getData.php";
    String urlDelete  = "http://drawtokappdev.atwebpages.com/delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listSV = findViewById(R.id.ls_sinhvien);
        sinhVienArrayList = new ArrayList<>();
        adapter = new SinhVienAdapter(this, R.layout.sinhvien_layout, sinhVienArrayList);
        listSV.setAdapter(adapter);
        ReadJSON(urlGetData);
    }

    private void ReadJSON(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sinhVienArrayList.clear();
                for(int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        sinhVienArrayList.add(new
                                SinhVien(
                                        object.getInt("ID"),
                                        object.getString("HoTen"),
                                        object.getInt("NamSinh"),
                                        object.getString("DiaChi")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_student, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuAddStudent)
        {
            startActivity(new Intent(MainActivity.this, AddSVActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void DeleteStudent(int id)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Successful"))
                {
                    Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    ReadJSON(urlGetData);
                }else{
                    Toast.makeText(MainActivity.this, "Lỗi xóa...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("idSV", String.valueOf(id));

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}