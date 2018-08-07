package com.example.toandx.passdatacontent2;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick(View view)
    {
        Log.d("LOG","onClick");
        Uri uri=Uri.parse("com.example.toandx.senddata.MsgDB");
        String URL = "content://com.example.toandx.senddata.MsgDB";
        Cursor c=getContentResolver().query(Uri.parse(URL),null,null,null,null);

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex("tel")) +
                                //", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( "info")),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        } else
            Toast.makeText(this,"No message found",Toast.LENGTH_SHORT).show();
    }
}
