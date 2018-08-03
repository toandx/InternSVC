package com.example.toandx.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private IncomingSms SMS;
    private BroadcastReceiver nhan;
    private ListView list;
    private CustomListAdapter adapter;
    //private SimpleCursorAdapter adapter;
    ArrayList<String> name;
    ArrayList<String> info;
    Uri inboxURI;
    String[] reqCols;
    Cursor c;
    String numphone,body;
    private Bundle data;
    private Message message;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=new ArrayList<String>();
        info=new ArrayList<String>();
        list=(ListView) findViewById(R.id.listview);
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if (msg.getData()!=null) {
                    numphone = msg.getData().getString("name");
                    body=msg.getData().getString("info");
                    Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.DATA + "='" + numphone + "'",
                            null, null);
                    if (phone.moveToFirst()) {
                        String id = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        Cursor phonect = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                                null, ContactsContract.Contacts._ID + "=" + id, null, null);
                        if (phonect.moveToFirst()) {
                            String s = phonect.getString(phonect.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            numphone = numphone + "(" + s + ")";
                        }
                    }
                    info.add(msg.getData().getString("info"));
                    name.add(numphone);
                }
            }
        };
        inboxURI = Uri.parse("content://sms/inbox");
        // List required columns
        reqCols = new String[] {"_id","address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Inbox SMS Message from Built-in Content Provider
        c = cr.query(inboxURI, reqCols, null, null, null);
        adapter=new CustomListAdapter(this,name,info);
        //adapter=new SimpleCursorAdapter(this,R.layout.listview,c,new String[] {"address","body"},
        //      new int[]{R.id.nameTextViewID,R.id.infoTextViewID});
        list.setAdapter(adapter);
        Runnable run1=new Runnable() {
            @Override
            public void run() {
                if (c.moveToFirst())
                {
                    do {
                        data=new Bundle();
                        data.putString("info",c.getString(c.getColumnIndex("body")));
                        data.putString("name",c.getString(c.getColumnIndex("address")));
                        message=new Message();
                        message.setData(data);
                        handler.sendMessage(message);
                    } while (c.moveToNext());
                }
            }
        };
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this,Integer.toString(i),Toast.LENGTH_LONG).show();
            }
        });
        SMS=new IncomingSms();
        nhan=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                c = getContentResolver().query(inboxURI, reqCols, null, null, null);
                if (c.moveToLast())
                {
                    info.add(c.getString(c.getColumnIndex("body")));
                    numphone=c.getString(c.getColumnIndex("address"));
                    Cursor phone=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.DATA+"='"+numphone+"'",
                            null,null);
                    if (phone.moveToFirst()) {
                        String id = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        Cursor phonect=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                                null,ContactsContract.Contacts._ID+"="+id,null,null);
                        if (phonect.moveToFirst())
                        {
                            String s=phonect.getString(phonect.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            numphone=numphone+"("+s+")";
                        }
                    }
                    name.add(numphone);
                }
                list.invalidateViews();
            }
        };
        new Thread(run1).start();


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

        list.invalidateViews();

    }
}
