package com.nandani.impicit_intent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import android.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var phn : EditText
    lateinit var email : EditText
    lateinit var sms : EditText
    lateinit var share : EditText
    lateinit var tvRating : EditText

    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4: Button
    lateinit var btn5 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phn=findViewById(R.id.phn)
        email=findViewById(R.id.email)
        sms=findViewById(R.id.sms)
        share=findViewById(R.id.share)
        tvRating=findViewById(R.id.tvRating)

        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)

        btn1.setOnClickListener {
            val subject = email.text.toString().trim()
            val message = sms.text.toString().trim()


            val emailIntent = Intent(Intent.ACTION_SENDTO)

            emailIntent.type = "text/plain"
            emailIntent.data = Uri.parse("Mail to:")
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)
            val chooser = Intent.createChooser(emailIntent, "Send email....")
            startActivity(chooser)
        }

        tvRating.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://play.google.com/")
            startActivity(openURL)
        }


    }



    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 101)
        }
    }

}

