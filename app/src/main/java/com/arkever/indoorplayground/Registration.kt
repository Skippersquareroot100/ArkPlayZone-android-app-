package com.arkever.indoorplayground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var rerr = findViewById<TextView>(R.id.registraionerror)
        var redir = findViewById<TextView>(R.id.logpage)
        var regi = findViewById<Button>(R.id.regis)
        var fname = findViewById<EditText>(R.id.fname)
        var mname = findViewById<EditText>(R.id.mname)
        var lname = findViewById<EditText>(R.id.lname)
        var city = findViewById<EditText>(R.id.city)
        var pcode = findViewById<EditText>(R.id.postalcode)
        var stno = findViewById<EditText>(R.id.street)
        var sname = findViewById<EditText>(R.id.sname)
        var email = findViewById<EditText>(R.id.email)
        var pass = findViewById<EditText>(R.id.pass)
        var cpass = findViewById<EditText>(R.id.cpass)
        var aprt = findViewById<EditText>(R.id.apartment)




        regi.setOnClickListener {

            if (fname.text.isEmpty() ||
                mname.text.isEmpty() ||
                lname.text.isEmpty() ||
                city.text.isEmpty() ||
                pcode.text.isEmpty() ||
                stno.text.isEmpty() ||
                sname.text.isEmpty() ||
                email.text.isEmpty() ||
                pass.text.isEmpty() ||
                cpass.text.isEmpty() ||
                aprt.text.isEmpty()) {

                rerr.gravity= Gravity.CENTER
                rerr.text = "Complete All Feilds First !"
                rerr.visibility = TextView.VISIBLE

            }
            else if(pass.text.toString()!=cpass.text.toString())
            {
                rerr.gravity= Gravity.CENTER
                rerr.text = "password doesnot macthed !"
                rerr.visibility = TextView.VISIBLE
            }
            else
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }


        }

        redir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}