package com.example.mockfragmenttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_two.*
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FragTwo: Fragment() {

    companion object {
        fun newInstance() = FragTwo()
    }

    private lateinit var viewModel: MainViewModel
    private val AUTH_REQUEST_CODE = 2000
    private var user : FirebaseUser? = null

    lateinit var appName: String
    lateinit var usernameInput: String
    lateinit var passwordInput: String
    lateinit var notesInput: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.frag_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       btnLogon.setOnClickListener {
           logon()
       }

        btnBack.setOnClickListener {
            // save users input (username and password)
            usernameInput = username_input.text.toString()
            passwordInput = password_input.text.toString()
            notesInput = note_input.text.toString()
            var id: String = " "
            // TODO: Save to firebase Storage
            // Note: Use appName to differentiate which application your saving the user input to. i.e. facebook, google etc.
            if(appName == "facebook") {
                id = " "
            } else if (appName == "google") {
                id = " "
            } else if (appName == "github") {
                id = ""
            } else if (appName == "pinterest") {
                id = " "
            }

            saveApp(id)

            (activity as MainActivity).showFragOne()
        }

        // Sets image according to which appButton was clicked
        when(appName) {
            "facebook" -> imageView.setImageResource(R.drawable.facebook)
            "google" -> imageView.setImageResource(R.drawable.google_white)
            "github" -> imageView.setImageResource(R.drawable.git)
            "pinterest" -> imageView.setImageResource(R.drawable.pinterst)
        }
    }

    fun logon() {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build() , AUTH_REQUEST_CODE
        )
    }

    fun saveApp(id:String) {
        var application = Applications().apply {
            app = appName
            userName = usernameInput
            passWord = passwordInput
            notes = notesInput
        }

        save(application, id)
    }

    private lateinit var firestore : FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save (applications: Applications, id:String){
        firestore.collection("Applications")
            .document(id)
            .set(applications)
            .addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "save failed")
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK){
            if (resultCode == AUTH_REQUEST_CODE)
                user =  FirebaseAuth.getInstance().currentUser
        }

    }
}

