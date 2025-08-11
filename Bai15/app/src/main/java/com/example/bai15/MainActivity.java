package com.example.bai15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtmalop, edttenlop, edtsiso;
    Button btnThem, btnXoa, btnCapNhat, btnQuery;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter<String> adapter;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtmalop = findViewById(R.id.edtmalop);
        edttenlop = findViewById(R.id.edttenlop);
        edtsiso = findViewById(R.id.edtsiso);
        btnThem = findViewById(R.id.btnthem);
        btnXoa = findViewById(R.id.btnxoa);
        btnCapNhat = findViewById(R.id.btncapnhat);
        btnQuery = findViewById(R.id.btnquery);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        lv.setAdapter(adapter);
        mydatabase = openOrCreateDatabase("qlysinhvien.db", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE tblop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table đã tồn tại");
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                String tenlop = edttenlop.getText().toString();
                int siso = Integer.parseInt(edtsiso.getText().toString());
                ContentValues values = new ContentValues();
                values.put("malop", malop);
                values.put("tenlop", tenlop);
                values.put("siso", siso);
                String msg = "";
                if (mydatabase.insert("tblop", null, values) == -1) {
                    msg = "Fail to insert record";
                } else {
                    msg = "Insert record successfully";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                int n = mydatabase.delete("tblop", "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "No record deleted";
                } else {
                    msg = n + " record is delete";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso = Integer.parseInt(edtsiso.getText().toString());
                String malop = edtmalop.getText().toString();
                ContentValues values = new ContentValues();
                values.put("siso", siso);
                int n = mydatabase.update("tblop", values, "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "No record to update";
                } else {
                    msg = n + " record is updated";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.clear();
                Cursor c = mydatabase.query("tblop", null, null, null, null, null, null);
                c.moveToNext();
                String data = "";
                while (c.isAfterLast() == false) {
                    data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
                    c.moveToNext();
                    myList.add(data);
                }
                c.close();
                adapter.notifyDataSetChanged();
            }
        });
    }
}