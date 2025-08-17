package com.arkever.indoorplayground.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.databinding.ActivityPassResetBinding
import com.arkever.indoorplayground.utils.SharedPrefEmail
import com.arkever.indoorplayground.viewmodel.PassReseViewMOdel


class PassReset : AppCompatActivity() {
    private lateinit var binding: ActivityPassResetBinding
    private lateinit var viewModel: PassReseViewMOdel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPassResetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sp = SharedPrefEmail(this)
        val email =  sp.getFEmail().toString()

        showLoading(false)


        viewModel = ViewModelProvider(this)[PassReseViewMOdel::class.java]
        viewModel.passresetResult.observe(this, Observer { (success, message) ->
            showLoading(false)
            if (success)
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else
            {
                errorMsg(message)
            }
        })


        binding.passresetbtn.setOnClickListener {

            val passwordText = binding.pass.text.toString()
            val confirmPasswordText = binding.cpass.text.toString()

            if (passwordText.length < 6) {

               errorMsg("Password must be at least 6 characters long.")
                return@setOnClickListener
            }

            val passwordRegex = Regex("^(?=.*[a-z])(?=.*[@#\$&]).+")
            if (!passwordRegex.matches(passwordText)) {

                errorMsg("Password must contain at least one lowercase letter and one special character (@, #, \$, &).")
                return@setOnClickListener
            }

            if (passwordText != confirmPasswordText) {

                errorMsg("Passwords do not match!")
                return@setOnClickListener
            }

            if(passwordText!=confirmPasswordText)
            {
                errorMsg("Password Doesn't Matched")
            }

            showLoading(true)
            viewModel.updatePassword(email,passwordText)
            sp.clearFEmail()
        }

    }
    private fun showLoading(show: Boolean) {
        binding.prBar.visibility = if (show) View.VISIBLE else View.GONE
    }
    fun errorMsg(err:String)
    {
        showLoading(false)
        binding.textView4.text= err
        binding.textView4.gravity= Gravity.CENTER
        binding.textView4.visibility= View.VISIBLE
    }
}