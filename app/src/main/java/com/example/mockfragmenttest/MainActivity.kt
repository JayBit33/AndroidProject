package com.example.mockfragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.frag_one.*

class MainActivity : AppCompatActivity() {
    var isFragOneLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val switch = findViewById<Button>(R.id.btn_switch)
        showFragOne()
        switch.setOnClickListener {
            if (isFragOneLoaded) {
                showFragTwo()
            } else {
                showFragOne()
            }
        }

    }

    fun showFragOne() {
        val transaction = manager.beginTransaction()
        val fragment = FragOne()
        transaction.replace(R.id.frag_handler, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

//        val addAccountBtn = fragment.add_account_btn
//        addAccountBtn.setOnClickListener {
//            showFragTwo()
//        }
    }

    fun showFragTwo() {
        val transaction = manager.beginTransaction()
        val fragment = FragTwo()
        transaction.replace(R.id.frag_handler, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
