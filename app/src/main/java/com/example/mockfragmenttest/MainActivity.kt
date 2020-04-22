package com.example.mockfragmenttest

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.frag_one.*
import kotlinx.android.synthetic.main.frag_two.*

open class MainActivity : AppCompatActivity() {
    var isFragOneLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragOne()

    }

    fun showFragOne() {

        val transaction = manager.beginTransaction()
        val fragment = FragOne.newInstance()
        transaction.replace(R.id.frag_handler, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showFragTwo(appName: String) {
        val transaction = manager.beginTransaction()
        val fragment = FragTwo.newInstance()
        fragment.appName = appName
        transaction.replace(R.id.frag_handler, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }


}
