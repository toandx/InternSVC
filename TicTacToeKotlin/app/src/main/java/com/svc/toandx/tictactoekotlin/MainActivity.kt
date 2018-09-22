package com.svc.toandx.tictactoekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    lateinit var playBoard:PlayBoard
    lateinit var textView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playBoard=findViewById(R.id.playboard) as PlayBoard
        textView=findViewById(R.id.textview1) as TextView
    }

    fun onClick0(view: View) {
        if (playBoard.status === 1)
            textView.setText("O Play")
        else if (playBoard.status === 2)
            textView.setText("X Play")
        else if (playBoard.status === 3) {
            textView.setText("X Win")
            Toast.makeText(applicationContext, "Congratulations! X Win", Toast.LENGTH_SHORT).show()
        } else if (playBoard.status === 4) {
            textView.setText("O Win")
            Toast.makeText(applicationContext, "Congratulations! O Win", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClick1(view: View) {
        playBoard.reset()
        textView.setText("")
    }
}
