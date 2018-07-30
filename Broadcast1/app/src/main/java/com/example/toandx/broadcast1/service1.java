package com.example.toandx.broadcast1;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

public class service1 extends IntentService {
    public service1()
    {
        super("service1");
    }
    @Override
    public void onHandleIntent(Intent intent)
    {
        String x=intent.getStringExtra("name");
        String y=intent.getStringExtra("age");
        xuatoutput("Name: "+x+" | Age: "+y);
    }
    private void xuatoutput(String str)
    {
        Intent intent=new Intent("com.example.toandx.service.receiver");
        intent.putExtra("output",str);
        sendBroadcast(intent);
    }
}
