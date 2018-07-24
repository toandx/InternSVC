package com.example.toandx.intent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText tv=(EditText) findViewById(R.id.editText2);
        //show text in the Intent object in the TextView

    }
    /*@Override
    public void onStart(){
        super.onStart();
        TextView tv=(TextView)findViewById(R.id.textview1);
        //show text in the Intent object in the TextView
        if (getIntent()!=null)
        tv.setText("Received text:"+getIntent().getStringExtra("text"));
    }*/
    public void onClick(View view){

        EditText input=(EditText) findViewById(R.id.editText2);
        Intent intent=new Intent(MainActivity.this, Activity2.class);
        //add data to the Intent object
        intent.putExtra("text",input.getText().toString());
        //start the second activity
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(resultCode == Activity.RESULT_OK && requestCode==1){
            EditText et=(EditText) findViewById(R.id.editText2);
            if (data.getStringExtra("result")!=null)
            et.setText(data.getStringExtra("result"));
        }

    }
}
