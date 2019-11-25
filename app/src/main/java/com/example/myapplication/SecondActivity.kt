package com.example.myapplication

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.KEY)
        val luckyNumber = intent.getIntExtra(MainActivity.NUM, 0)
        textView.text = String.format("%s %s", getString(R.string.message), message)
        textViewNumber.text = String.format("%s %d", getString(R.string.lucky_number), luckyNumber)

        buttonDone.setOnClickListener{
            if(!editTextReply.text.isEmpty()) { // user can ignore the reply
                val reply = editTextReply.text.toString()
                val intent = getIntent() // return the MainActivity intent
                intent.putExtra(MainActivity.REPLY, reply)

                // inform the MainActivity that everything is OK
                setResult(Activity.RESULT_OK, intent)
            }else
                setResult(Activity.RESULT_CANCELED)

            finish()
        }
    }
}
