package com.arkever.indoorplayground.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.databinding.ActivityForgetPasswordBinding
import com.arkever.indoorplayground.utils.SharedPrefEmail
import com.arkever.indoorplayground.viewmodel.forgetPassViewModel

class ForgetPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding
    private lateinit var viewModel: forgetPassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[forgetPassViewModel::class.java]


        viewModel.forgetPassResult.observe(this, Observer { (success, message) ->
            showLoading(false)
            if (success) {
                val email = binding.emailField.text.toString()
                val sp = SharedPrefEmail(this)
                sp.saveFEmail(email)
                Log.d("ForPassEmail", email)
                val r = sp.getFEmail()
                Log.d("SharedForPassEmail", r ?: "No email found")

                startActivity(Intent(this, OTP::class.java))
                binding.textView2.visibility = View.GONE

            } else {
                errorMsg(message)
            }
        })


        binding.continuebtn.setOnClickListener {
            val email = binding.emailField.text.toString().trim()

            if (email.isEmpty()) {
                errorMsg("Enter Your Email First")
                showLoading(false)
            } else {
                showLoading(true)
                viewModel.forgetPassword(email)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        binding.pfBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun errorMsg(err: String) {
        binding.textView2.text = err
        binding.textView2.gravity = Gravity.CENTER
        binding.textView2.visibility = View.VISIBLE
    }
}
