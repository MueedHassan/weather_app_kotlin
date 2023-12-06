package com.example.myapplication.ui



import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.model.ForeCast
import com.example.myapplication.ui.fragments.Fragment_one
import com.example.myapplication.ui.fragments.Fragment_two
import com.example.myapplication.ui.fragments.fragment_settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(Fragment_one())


        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {


            // Return true to indicate the item selection is handled

            when (it.itemId) {
                R.id.home -> {
                    loadFragment(Fragment_one())


                    true
                }
                R.id.message -> {
                    loadFragment(Fragment_two())


                    true
                }
                R.id.settings -> {
                    loadFragment(fragment_settings())


                    true
                }

                else -> {
                    Log.e("MainActivity", "Unhandled menu item clicked")
                    false
                }
            }
        }
        var forecast= ForeCast()
        var recentData= ForeCast().toString()
        println("value is forecast $recentData")
    }


      fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
    // Function to get color based on item selection


}