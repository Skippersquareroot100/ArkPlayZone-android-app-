package com.arkever.indoorplayground.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.model.DTOs.StaffDto
import com.arkever.indoorplayground.viewmodel.StaffViewModel

class Registration : AppCompatActivity() {

    private lateinit var rerr: TextView
    private lateinit var redir: TextView
    private lateinit var regi: Button

    private lateinit var fname: EditText
    private lateinit var mname: EditText
    private lateinit var lname: EditText
    private lateinit var city: EditText
    private lateinit var pcode: EditText
    private lateinit var phone: EditText
    private lateinit var stno: EditText
    private lateinit var sname: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var cpass: EditText
    private lateinit var aprt: EditText
    private lateinit var pbar :ProgressBar
    private lateinit var viewModel: StaffViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]


        bindViews()
        observeViewModel()
    }

    private fun bindViews()
    {
        rerr = findViewById(R.id.registraionerror)
        redir = findViewById(R.id.logpage)
        regi = findViewById(R.id.regis)
        fname = findViewById(R.id.fname)
        mname = findViewById(R.id.mname)
        lname = findViewById(R.id.lname)
        city = findViewById(R.id.city)
        pcode = findViewById(R.id.postalcode)
        phone = findViewById(R.id.editTextPhone)
        stno = findViewById(R.id.street)
        sname = findViewById(R.id.sname)
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        cpass = findViewById(R.id.cpass)
        aprt = findViewById(R.id.apartment)
        pbar = findViewById(R.id.pBar)


        regi.setOnClickListener {
            if (fname.text.isEmpty() || lname.text.isEmpty() || city.text.isEmpty() ||
                pcode.text.isEmpty() || stno.text.isEmpty() || sname.text.isEmpty() ||
                email.text.isEmpty() || pass.text.isEmpty() || cpass.text.isEmpty() ||
                aprt.text.isEmpty() || phone.text.isEmpty()
            ) {
                showError("Complete all fields first!")
                return@setOnClickListener
            }


            val nameRegex = Regex("^[a-zA-Z]+\$")
            if (!nameRegex.matches(fname.text.toString())) {
                showError("First name must contain only letters.")
                return@setOnClickListener
            }

            if (!nameRegex.matches(lname.text.toString())) {
                showError("Last name must contain only letters.")
                return@setOnClickListener
            }


            val phoneRegex = Regex("^01\\d{8,9}\$")
            if (!phoneRegex.matches(phone.text.toString())) {
                showError("Phone number must start with 01 and contain 11 digits.")
                return@setOnClickListener
            }


            val passwordText = pass.text.toString()
            val confirmPasswordText = cpass.text.toString()

            if (passwordText.length < 6) {
                showError("Password must be at least 6 characters long.")
                return@setOnClickListener
            }

            val passwordRegex = Regex("^(?=.*[a-z])(?=.*[@#\$&]).+")
            if (!passwordRegex.matches(passwordText)) {
                showError("Password must contain at least one lowercase letter and one special character (@, #, \$, &).")
                return@setOnClickListener
            }

            if (passwordText != confirmPasswordText) {
                showError("Passwords do not match!")
                return@setOnClickListener
            }
            val dto = StaffDto(
                firstName = fname.text.toString(),
                middleName = if (mname.text.isEmpty()) null else mname.text.toString(),
                lastName = lname.text.toString(),
                street_no = stno.text.toString(),
                street_name = sname.text.toString(),
                apartment_name = aprt.text.toString(),
                city = city.text.toString(),
                state = "Dhaka",
                postal_code = pcode.text.toString(),
                deduction = 0,
                overtime = 0,
                role = "android",
                email = email.text.toString(),
                phone = phone.text.toString(),
                salary = 1000,
                password = pass.text.toString(),
                file = "hello.jpg"
            )
            showLoading(true)
            viewModel.registerStaff(dto)
        }

        redir.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private  fun observeViewModel()
    {
        viewModel.registrationResult.observe(this)
        {
                (success, message) ->
                 showLoading(false)
            if(success)
            {
                Toast.makeText(this,"Redirecting into Login page", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else
            {
                showError(message)
            }
        }

    }


    private fun showLoading(show: Boolean) {
        pbar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        rerr.gravity = Gravity.CENTER
        rerr.text = message
        rerr.visibility = TextView.VISIBLE
    }



}
