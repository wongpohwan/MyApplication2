package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener {
            sendMessage();
        }

        buttonCallMe.setOnClickListener{
            dialPhone()
        }

    }

    private fun dialPhone() {
        val intent = Intent(Intent.ACTION_VIEW)
        val phone: String = "tel:0123456789"

        // check package manager for app to handle an intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null) {
            startActivity(intent)
        }
    }

    private fun sendMessage() {
        // explicit intent
        val intent = Intent(this, SecondActivity::class.java)
        val message = editTextMessage.text.toString()
        val luckyNumber = editTextNumber.text.toString().toInt()
        intent.putExtra(KEY, message)
        intent.putExtra(NUM, luckyNumber)

        //startActivity(intent) // An intent without return value
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val reply = data?.getStringExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s", getString(R.string.reply), reply)
            }
        }
    }

    companion object {
        //if u have more than one item to pass , u can create more than one const val
        const val KEY = "com.example.myapplication.KEY" //unique package name
        const val NUM = "com.example.myapplication.NUM"
        const val REPLY ="com.example.myapplication.REPLY"
        const val REQUEST_CODE = 1
    }
}
