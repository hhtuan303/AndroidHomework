package com.example.bai12_a;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String myPhone[] = {"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"};
    ListView lvPhone;
    TextView edtPhone;
    ArrayAdapter<String> myAdapter;
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
        edtPhone = findViewById(R.id.edit_text_phone);
        lvPhone = findViewById(R.id.list_view_phone);
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myPhone);
        lvPhone.setAdapter(myAdapter);
        lvPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtPhone.setText("");
                edtPhone.setText("Vị trí " + (position + 1) + ": " + myPhone[position]);
            }
        });
    }
}