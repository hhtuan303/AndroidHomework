package com.example.bai12_b;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listWork;
    ArrayAdapter<String> myAdapter;
    EditText edtWork, edtHour, edtMinute;
    TextView txtDate;
    Button btnWork;
    ListView lvWork;
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
        edtWork = findViewById(R.id.edit_text_work);
        edtHour = findViewById(R.id.edit_text_hour);
        edtMinute = findViewById(R.id.edit_text_minute);
        txtDate = findViewById(R.id.edit_text_date);
        btnWork = findViewById(R.id.button_add_work);
        lvWork = findViewById(R.id.list_view_work);
        listWork = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listWork);
        lvWork.setAdapter(myAdapter);
        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hôm nay: " + dateFormat.format(currentDate));
        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtWork.getText().toString().equals("") || edtHour.getText().toString().equals("") ||
                edtMinute.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    String str = edtWork.getText().toString() + " - " + edtHour.getText().toString() +
                            ":" + edtMinute.getText().toString();
                    listWork.add(str);
                    myAdapter.notifyDataSetChanged();
                    edtHour.setText("");
                    edtMinute.setText("");
                    edtWork.setText("");
                }
            }
        });
    }
}