package com.example.toandx.broadcast1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private BroadcastReceiver receiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String out=intent.getStringExtra("output");
            textView.setText(out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textview);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("com.example.toandx.service.receiver"));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }



    public void onClick(View view)
    {
        Intent intent=new Intent(this,service1.class);
        intent.putExtra("name","Toan");
        intent.putExtra("age","22");
        startService(intent);
        Toast.makeText(MainActivity.this,"Start Service",Toast.LENGTH_LONG).show();

    }
}
