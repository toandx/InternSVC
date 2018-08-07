package com.example.toandx.senddata;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText) findViewById(R.id.editText);
        et2=(EditText) findViewById(R.id.editText2);
    }
    public void Add(View view)
    {
        ContentValues values = new ContentValues();
        values.put(MsgDB.TEL, et1.getText().toString());

        values.put(MsgDB.INFO, et2.getText().toString());
        Uri uri = getContentResolver().insert(
                MsgDB.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_SHORT).show();
    }
    public void Del(View view)
    {
        int i=getContentResolver().delete(MsgDB.CONTENT_URI,null,null);
    }
}
