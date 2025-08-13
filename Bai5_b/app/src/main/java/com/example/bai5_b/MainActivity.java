package com.example.bai5_b;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btntieptuc, btngiaipt, btnthoat;
    EditText edta, edtb, edtc;
    TextView txtkq;
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
        btntieptuc = findViewById(R.id.btntieptuc);
        btngiaipt = findViewById(R.id.btngiaipt);
        btnthoat = findViewById(R.id.btnthoat);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);
        btngiaipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sa = edta.getText() + "";
                String sb = edtb.getText() + "";
                String sc = edtc.getText() + "";
                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);
                String kq = "";
                DecimalFormat dcf = new DecimalFormat("0.00");
                if (a == 0) {
                    if (b == 0) {
                        if (c == 0) {
                            kq = "PT vô số nghiệm";
                        } else {
                            kq = "PT vô nghiệm";
                        }
                    } else {
                        kq = "Pt có 1 nghiệm, x = " + dcf.format(-c/b);
                    }
                } else {
                    double delta = b * b - 4 * a * c;
                    if (delta < 0) {
                        kq = "PT vô nghiệm";
                    } else if (delta == 0) {
                        kq = "PT có nghiệm kép x1 = x2 = " + dcf.format(-b/(2*a));
                    } else {
                        kq = "PT có 2 nghiệm, x1 = " + dcf.format((-b+Math.sqrt(delta)) / (2 * a));
                        kq += ", x2 = " + dcf.format((-b-Math.sqrt(delta)) / (2 * a));
                    }
                }
                txtkq.setText(kq);
            }
        });
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edta.setText("");
                edtb.setText("");
                edtc.setText("");
                edta.requestFocus();
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}