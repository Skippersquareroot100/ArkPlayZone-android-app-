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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Call POST /manager/hello when page opens
        testHello()

        // UI bindings
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

        regi.setOnClickListener {
            rerr.visibility = TextView.INVISIBLE

            if (fname.text.isEmpty() ||
                lname.text.isEmpty() ||
                city.text.isEmpty() ||
                pcode.text.isEmpty() ||
                stno.text.isEmpty() ||
                sname.text.isEmpty() ||
                email.text.isEmpty() ||
                pass.text.isEmpty() ||
                cpass.text.isEmpty() ||
                aprt.text.isEmpty() ||
                phone.text.isEmpty()
            ) {
                showError("Complete all fields first!")
                return@setOnClickListener
            }

            if (pass.text.toString() != cpass.text.toString()) {
                showError("Password does not match!")
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
                password = pass.text.toString()
            )

            RetrofitClient.instance.createStaff(dto)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Log.d("Register", "Staff created!")
                            val intent = Intent(this@Registration, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.e("Register", "Failed: ${response.code()} - ${response.message()}")
                            showError("Registration failed: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.e("Register", "Error: ${t.localizedMessage}", t)
                        showError("Error: ${t.localizedMessage}")
                    }
                })
        }

        redir.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun testHello() {
        RetrofitClient.instance.hello().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("HelloTest", "Backend says hello!")
                } else {
                    Log.e("HelloTest", "Hello call failed: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("HelloTest", "Network error: ${t.localizedMessage}", t)
            }
        })
    }

    private fun showError(message: String) {
        rerr.gravity = Gravity.CENTER
        rerr.text = message
        rerr.visibility = TextView.VISIBLE
    }
}
