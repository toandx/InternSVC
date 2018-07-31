package com.example.toandx.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> name;
    ArrayList<String> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=new ArrayList<String>();
        name.add("Toan");
        info=new ArrayList<String>();
        info.add("dep trai");

        //Toast.makeText(this,name.get(0), Toast.LENGTH_SHORT).show();
        //info.add("Dep trai");
        ListView list=(ListView) findViewById(R.id.list);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,name,info);
        CustomListAdapter adapter=new CustomListAdapter(this,name,info);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,Integer.toString(i),Toast.LENGTH_LONG).show();
            }
        });
        info.add("CHO");
        name.add("Thang");
    }

}
