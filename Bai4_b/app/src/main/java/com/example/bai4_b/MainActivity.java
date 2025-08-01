package com.example.bai4_b;

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

public class MainActivity extends AppCompatActivity {
    private EditText edtten, edtchieucao, edtcannang, edtbmi, edtkq;
    private Button btnkq;
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
        edtten = findViewById(R.id.edtten);
        edtchieucao = findViewById(R.id.edtchieucao);
        edtcannang = findViewById(R.id.edtcannang);
        edtbmi = findViewById(R.id.edtbmi);
        edtkq = findViewById(R.id.edtkq);
        btnkq = findViewById(R.id.btnkq);
        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double chieucao = Double.parseDouble(edtchieucao.getText().toString());
                double cannang = Double.parseDouble(edtcannang.getText().toString());
                double bmi = cannang / Math.pow(chieucao, 2);
                String kq = "";
                if (bmi < 18) {
                    kq = "Bạn gầy";
                } else if (bmi <= 24.9) {
                    kq = "Bạn bình thường";
                } else if (bmi <= 29.9) {
                    kq = "Bạn béo phì độ 1";
                } else if (bmi <= 34.9){
                    kq = "Bạn béo phì độ 2";
                } else {
                    kq = "Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtbmi.setText(dcf.format(bmi) + "");
                edtkq.setText(kq);
            }
        });
    }
}