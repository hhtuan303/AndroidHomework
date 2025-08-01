package com.example.bai7_c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    private EditText edta;
    private EditText edtb;
    private Button btntong, btnhieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edta = findViewById(R.id.edta2);
        edtb = findViewById(R.id.edtb2);
        btntong = findViewById(R.id.btntong);
        btnhieu = findViewById(R.id.btnhieu);
        Intent intent = getIntent();
        int a = intent.getIntExtra("soa", 0);
        int b = intent.getIntExtra("sob", 0);
        edta.setText(a + "");
        edtb.setText(b + "");
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(SubActivity.this, MainActivity.class);
                int c = a + b;
                resultIntent.putExtra("kq", c);
                setResult(33, resultIntent);
                finish();
            }
        });
        btnhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(SubActivity.this, MainActivity.class);
                int c = a - b;
                resultIntent.putExtra("kq", c);
                setResult(34, resultIntent);
                finish();
            }
        });
    }
}