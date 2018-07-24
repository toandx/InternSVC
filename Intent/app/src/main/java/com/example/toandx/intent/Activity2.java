package com.example.toandx.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        EditText tv = (EditText) findViewById(R.id.editText3);
        Intent intent2=getIntent();
        tv.setText(intent2.getStringExtra("text"));
        //show text in the Intent object in the TextView
        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.editText3);
                Intent intent2 = new Intent();
                //add data to the Intent object
                intent2.putExtra("result",input.getText().toString());
                //intent2.putExtra("result", input.getText().toString());
                setResult(RESULT_OK,intent2);
                finish();



            }
        });
    }
}
