package com.headspire.xmlparsingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.headspire.xmlparsingtest.model.Employee;

import org.xml.sax.helpers.XMLReaderFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //result to be shown in the UI.
    private TextView result;
    //list of employees in the data.xml file.
    List<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        try
        {
            XmlParsing parsing=new XmlParsing();
            InputStream inputStream=getAssets().open("data.xml");
            employees=parsing.parse(inputStream);
            result.setText(employees.get(0).getId()+"::"+employees.get(0).getName()
            +"\n"+employees.get(1).getId()+"::"+employees.get(1).getName()
            +"\n"+employees.get(2).getId()+"::"+employees.get(2).getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
