package com.arkever.indoorplayground.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.databinding.ActivityOtpBinding
import com.arkever.indoorplayground.utils.SharedPrefEmail
import com.arkever.indoorplayground.viewmodel.OTPValidateViewMOdel

class OTP : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding

    private lateinit var viewMOdel: OTPValidateViewMOdel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val editTexts = arrayOf(
            binding.one,
            binding.two,
            binding.three,
            binding.four,
            binding.five,
            binding.six
        )

        for(i in editTexts.indices)
        {
            editTexts[i].addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                   if(s?.length == 1 && i< editTexts.size-1)
                   {
                       editTexts[i+1].requestFocus()
                   }
                    else if (s?.length == 0 && i>0)
                   {
                        editTexts[i-1].requestFocus()
                   }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        showLoading(false)

        viewMOdel = ViewModelProvider(this)[OTPValidateViewMOdel::class.java]

        viewMOdel.otpValidateResult.observe(this, Observer{(success, message)->
            showLoading(false)
            if(success)
            {
                Toast.makeText(this,"OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, PassReset::class.java))
                binding.textView5.visibility=View.GONE
            }
            else
            {
                errorMsg(message)
            }
        })


        binding.otpbtn.setOnClickListener {


            var optstr = editTexts.joinToString(""){it.text.toString()}



            Log.d("myenter","Entered OTP:$optstr")
            if(optstr.length<6)
            {
               errorMsg("Invalid OTP")
                showLoading(false)
            }
            else
            {
                showLoading(true)
                val sp = SharedPrefEmail(this)
                val email= sp.getFEmail().toString()
                Log.d("myemail","here: $email")
                val otp = optstr
                viewMOdel.vrifyOTP(email,otp)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        binding.poBar.visibility = if (show) View.VISIBLE else View.GONE
    }
    fun errorMsg(err:String)
    {
        binding.textView5.text= err
        binding.textView5.gravity= Gravity.CENTER
        binding.textView5.visibility= View.VISIBLE
    }
}