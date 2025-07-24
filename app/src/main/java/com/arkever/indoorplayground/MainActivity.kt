package com.arkever.indoorplayground

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var email = findViewById<EditText>(R.id.editTextText)
        var password = findViewById<EditText>(R.id.editTextTextPassword)
        var loginbtn = findViewById<Button>(R.id.button)
        var forgetpass = findViewById<TextView>(R.id.forgotpass)
        var createacc = findViewById<TextView>(R.id.creatacc)
        var errorMessage = findViewById<TextView>(R.id.errorMessage)


        loginbtn.setOnClickListener {
           if(email.text.toString().trim() == "user@gamil.com" && password.text.toString() == "123" )
           {
                val intent = Intent(this , UserDashBoard::class.java)
               startActivity(intent)
               finish()
           }
           else {
               errorMessage.gravity=Gravity.CENTER
               errorMessage.text = "Invalid email or password"
               errorMessage.visibility = TextView.VISIBLE
           }
        }

        createacc.setOnClickListener()
        {
            
            val intent = Intent(this , Registration::class.java)
            startActivity(intent)
            finish()
        }



    }
}