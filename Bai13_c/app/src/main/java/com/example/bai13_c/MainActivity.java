package com.example.bai13_c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] name = {"Điện thoại Iphone 12", "Điện thoại SamSung S20", "Điện thoại Nokia 6",
    "Điện thoại BPhone2020", "Điện thoại Oppo 5", "Điện thoại VSmart joy2"};
    int imagephone = R.drawable.phone;
    ArrayList<Phone> myList;
    PhoneAdapter adapter;
    ListView lv;
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
        lv = findViewById(R.id.list_view_phone);
        myList = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            myList.add(new Phone(name[i], imagephone));
        }
        adapter = new PhoneAdapter(this, R.layout.item_phone, myList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("name", name[position]);
                startActivity(intent);
            }
        });
    }
}