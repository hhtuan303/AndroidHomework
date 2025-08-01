package com.example.bai7_b;

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

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    private Button btnBack;
    private EditText edtKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtKQ = findViewById(R.id.edtKQ);
        btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        int a = bundle.getInt("SoA", 0);
        int b = bundle.getInt("SoB", 0);
        String kq = "";
        if (a == 0 && b == 0) {
            kq = "Vô số nghiệm";
        }
        else if (a == 0 & b != 0) {
            kq = "Vô nghiệm";
        } else {
            DecimalFormat dcf = new DecimalFormat("#.00");
            kq = (-1.0 * b / a) + "";
        }
        edtKQ.setText(kq);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}