package com.example.bai13_a;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView txtselection;
    AutoCompleteTextView singlecomplete;
    MultiAutoCompleteTextView multicomplete;
    String arr[] = {"Hà Nội", "Huế", "Sài Gòn", "Hà Giang", "Hội An", "Kiên Giang", "Lâm Đồng"};
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
        txtselection = findViewById(R.id.textView);
        singlecomplete = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arr
        );
        singlecomplete.setAdapter(myAdapter);
        multicomplete = findViewById(R.id.multiAutoCompleteTextView);
        multicomplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr));

        multicomplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singlecomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtselection.setText(singlecomplete.getText());
            }
        });
    }
}