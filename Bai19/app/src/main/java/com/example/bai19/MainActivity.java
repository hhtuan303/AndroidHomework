package com.example.bai19;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btnParse;
    ArrayList<String> mylist;
    ArrayAdapter myadapter;
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
        lv = findViewById(R.id.lv);
        btnParse = findViewById(R.id.btnparse);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsexml();
            }

            private void parsexml() {
                try {
                    InputStream myInput = getAssets().open("employee.xml");
                    XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = fc.newPullParser();
                    parser.setInput(myInput, null);
                    int eventType = -1;
                    String nodeName;
                    String datashow = "";
                    while (eventType!=XmlPullParser.END_DOCUMENT) {
                        eventType=parser.next();
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                nodeName=parser.getName();
                                if (nodeName.equals("employee")) {
                                    datashow+=parser.getAttributeName(0)+"-";
                                    datashow+=parser.getAttributeName(1)+"-";
                                } else if (nodeName.equals("name")) {
                                    parser.next();
                                    datashow+=parser.getText()+"-";
                                } else if (nodeName.equals("phone")) {
                                    datashow+=parser.nextText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                nodeName=parser.getName();
                                if (nodeName.equals("employee")) {
                                    mylist.add(datashow);
                                    datashow="";
                                }
                                break;
                        }
                        myadapter.notifyDataSetChanged();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (XmlPullParserException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}