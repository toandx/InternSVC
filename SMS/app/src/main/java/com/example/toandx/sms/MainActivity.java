package com.example.toandx.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private IncomingSms SMS;
    private BroadcastReceiver nhan;
    private ListView list;
    ArrayList<String> name;
    ArrayList<String> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=new ArrayList<String>();
        info=new ArrayList<String>();
        list=(ListView) findViewById(R.id.listview);
        CustomListAdapter adapter=new CustomListAdapter(this,name,info);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,Integer.toString(i),Toast.LENGTH_LONG).show();
            }
        });
        SMS=new IncomingSms();
        nhan=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s1=intent.getStringExtra("NUM");
                String s2=intent.getStringExtra("MSG");
                if (s1!=null && s2!=null)
                {
                    name.add("Nhan:"+s1);
                    info.add(s2);
                    list.invalidateViews();
                }
            }
        };


    }
    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(SMS);
        unregisterReceiver(nhan);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(SMS,new IntentFilter("com.example.toandx.sms.IncomingSMS"));
        registerReceiver(nhan,new IntentFilter("com.example.toandx.service.receiver"));
    }
    public void Send(View view)
    {
        Intent intent=new Intent(this,SendSMS.class);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(resultCode == Activity.RESULT_OK && requestCode==1){
            name.add("Gui:"+data.getStringExtra("NUM"));
            info.add(data.getStringExtra("MSG"));
            list.invalidateViews();

        }

    }
}
