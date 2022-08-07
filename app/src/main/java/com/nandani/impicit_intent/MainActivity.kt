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
import javax.security.auth.Subject

class MainActivity : AppCompatActivity() {
    lateinit var phn : EditText
    lateinit var etMailto : EditText
    lateinit var etSubject : EditText
    lateinit var etMailMess : EditText
    lateinit var sms : EditText
    lateinit var share : EditText
    lateinit var tvRating : EditText

    lateinit var btn1 : Button
    lateinit var btnMail : Button
    lateinit var btn3 : Button
    lateinit var btn4: Button
    lateinit var btn5 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phn=findViewById(R.id.phn)
        etMailto=findViewById(R.id.etMailto)
        etSubject=findViewById(R.id.etSubject)
        etMailMess=findViewById(R.id.etMailMess)
        sms=findViewById(R.id.sms)
        share=findViewById(R.id.share)
        tvRating=findViewById(R.id.tvRating)

        btn1=findViewById(R.id.btn1)
        btnMail=findViewById(R.id.btnMail)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)

        btn1.setOnClickListener {
            val mobileNumber = phn.text.toString().trim()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + Uri.encode(mobileNumber))
            startActivity(dialIntent)
        }
        // set up run time permission in manifests

        checkPermission()

        btn3.setOnClickListener {
            val mobileNumber = phn.text.toString()
            val message = sms.text.toString()
            if (mobileNumber.isNotEmpty()) {
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.putExtra(Intent.EXTRA_TEXT,message)

                smsIntent.data = Uri.parse("sms: $mobileNumber ")
                startActivity(smsIntent)
            }
        }
        btn4.setOnClickListener {
            val note :String = share.text.toString().trim()
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,note)
            val chooser =Intent.createChooser(shareIntent,"Share using....")
            startActivity(chooser)
        }


        btnMail.setOnClickListener {
            val email = etMailto.text.toString().trim()
            val subject = etSubject.text.toString().trim()
            val message = etMailMess.text.toString().trim()
            val emailIntent = Intent(Intent.ACTION_SENDTO)

            emailIntent.type = "text/plain"
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)
            val chooser = Intent.createChooser(emailIntent, "Send email....")
            startActivity(chooser)
        }

        btn5.setOnClickListener {
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
