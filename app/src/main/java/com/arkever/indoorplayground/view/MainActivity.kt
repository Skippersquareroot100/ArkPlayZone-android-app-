package com.arkever.indoorplayground.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.utils.SharedPrefManager
import com.arkever.indoorplayground.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var errorMessage: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginbtn: Button
    private lateinit var forgetpass: TextView
    private lateinit var createacc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sp= SharedPrefManager(this)
        val token = sp.getToken()
        Log.d("TOKEN", token ?: "No token found")
        if (!token.isNullOrEmpty() && token.startsWith("Bearer ") && !sp.isTokenExpired())
        {
            startActivity(Intent(this, UserDashBoard::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindViews()
    }

    private fun bindViews() {
        email = findViewById(R.id.editTextText)
        password = findViewById(R.id.editTextTextPassword)
        loginbtn = findViewById(R.id.button)
        forgetpass = findViewById(R.id.forgotpass)
        createacc = findViewById(R.id.creatacc)
        errorMessage = findViewById(R.id.errorMessage)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        loginbtn.setOnClickListener {
            val emailStr = email.text.toString()
            val passStr = password.text.toString()

            if (emailStr.isEmpty() || passStr.isEmpty()) {
                val msg = "Please fill in all fields"
                showErrorMessage(msg)

            }

            viewModel.login(emailStr, passStr)


        }


        viewModel.loginResult.observe(this){ (success, tokenOrMessage)->
            if(success)
            {
                val sp = SharedPrefManager(this)
                sp.saveUser(email.text.toString(),tokenOrMessage,3600)
                Log.d("TOKEN", tokenOrMessage ?: "No token found")

                startActivity(Intent(this,UserDashBoard::class.java))
                Toast.makeText(this,"Login Succesfull", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                showErrorMessage(tokenOrMessage)
            }
        }


        createacc.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        forgetpass.setOnClickListener {
           val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMessage(err: String) {
        errorMessage.gravity = Gravity.CENTER
        errorMessage.text = err
        errorMessage.visibility = View.VISIBLE

    }
}