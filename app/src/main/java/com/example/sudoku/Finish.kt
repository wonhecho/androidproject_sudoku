package com.example.sudoku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class Finish : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        var time : Int
        time = intent.getStringExtra("time").toInt()
        var total = Math.round(time * 0.001)
        val minute = total/60
        val sec = total%60


//        wrongcount = intent.getStringExtra("wrong").toInt()

        record1.setText("${minute}분 ${sec}초 ")


        returnbtn.setOnClickListener{
            val rehome = Intent(this, Home::class.java)
            rehome.putExtra("time",total.toString())
            startActivity(rehome)

        }
    }
}
