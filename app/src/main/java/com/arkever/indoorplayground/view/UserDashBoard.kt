package com.arkever.indoorplayground.view

import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arkever.indoorplayground.R
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import com.arkever.indoorplayground.view.Fragments.HomeFragment
import com.arkever.indoorplayground.view.Fragments.NotificationFragment
import com.arkever.indoorplayground.view.Fragments.ProfileFragment
import androidx.fragment.app.commit
class UserDashBoard : AppCompatActivity() {

    lateinit var framelayout: FrameLayout
    lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_dash_board)

        framelayout = findViewById(R.id.framelayout)
        tablayout = findViewById(R.id.tableLayout)

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add tabs programmatically




        // Set default fragment
        replaceFragment(HomeFragment())

        // Handle tab selection
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> replaceFragment(HomeFragment())
                    1 -> replaceFragment(NotificationFragment())
                    2 -> replaceFragment(ProfileFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.framelayout, fragment)
        }
    }
}
