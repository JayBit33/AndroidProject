package com.example.mockfragmenttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.frag_two.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
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

       btnLogon2.setOnClickListener {
           logon()
       }

        btnBack.setOnClickListener {
            // save users input (username and password)
            usernameInput = username_input.text.toString()
            passwordInput = Plain_password.text.toString()
            notesInput = note_input.text.toString()
            var id: String = " "
            // TODO: Save to firebase Storage
            // Note: Use appName to differentiate which application your saving the user input to. i.e. facebook, google etc.
            if(appName == "facebook") {
                id = "facebook"
            } else if (appName == "google") {
                id = "google"
            } else if (appName == "github") {
                id = "github"
            } else if (appName == "pinterest") {
                id = "pinterest"
            }
            else if (appName == "twitter") {
                id = "twitter"
            }else if (appName == "usbank") {
                id = "usbank"
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
            "twitter" -> imageView.setImageResource(R.drawable.twitter)
            "usbank" -> imageView.setImageResource(R.drawable.usbank)


        }

        // Grab data from firestore for the correct application
        when(appName) {
            "facebook" -> getApp("facebook")
            "google" -> getApp("google")
            "github" -> getApp("github")
            "pinterest" -> getApp("pinterest")
            "twitter" -> getApp("twitter")
            "usbank" -> getApp("usbank")


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
          //  app = appName
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

    fun save (application: Applications, id:String){
        firestore.collection("Applications")
            .document(id)
            .set(application)
            .addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "save failed")
            }

    }

    // Retrieves firestore data and displays username, password and notes in FragTwo layout
    fun getApp(id: String) {
        firestore.collection("Applications")
            .document(id)
            .get()
            .addOnSuccessListener(OnSuccessListener<DocumentSnapshot> { documentSnapshot ->
                val username = documentSnapshot.get("userName")
                username_input.setText(username.toString())
                val password = documentSnapshot.get("passWord")
                Plain_password.setText(password.toString())
                val notes = documentSnapshot.get("notes")
                note_input.setText(notes.toString())
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
      //  viewModel.applications.observe(this, Observer{
        //    applications ->  username_input.setText(applications)
       // })
        if (requestCode == Activity.RESULT_OK){
            if (resultCode == AUTH_REQUEST_CODE)
                user =  FirebaseAuth.getInstance().currentUser
        }

    }
}

