package com.example.mockfragmenttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.frag_two.*

class FragTwo: Fragment() {

    companion object {
        fun newInstance() = FragTwo()
    }

    private lateinit var viewModel: MainViewModel
    private val AUTH_REQUEST_CODE = 2000
   // private var user : FirebaseUser? = null

    lateinit var appName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.frag_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      /*  btnLogon.setOnClickListener {
            logon()
        }*/

        btnBack.setOnClickListener {
            // save users input (username and password)
           saveApp()
            // TODO: Save to firebase Storage
            // Note: Use appName to differentiate which application your saving the user input to. i.e. facebook, google etc.

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

   /* fun logon() {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build() , AUTH_REQUEST_CODE
        )
    }*/

    fun saveApp() {
        var application = Applications().apply {
            userName = username_input.text.toString()
            passWord = password_input.text.toString()
            notes = note_input.text.toString()

        }
        save(application)

    }

    private lateinit var firestore : FirebaseFirestore
    private var _applications: MutableLiveData<ArrayList<Applications>> = MutableLiveData<ArrayList<Applications>>()
    // private var storageReferenence = FirebaseStorage.getInstance().getReference()
    private var _app = Applications()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save (applications: Applications){
        firestore.collection("Applications")
            .document()
            .set(applications)
            .addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "save failed")
            }

    }

    internal var applications: Applications
        get() {return _app}
        set(value) {_app = value}
}

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK){
            if (resultCode == AUTH_REQUEST_CODE)
                user =  FirebaseAuth.getInstance().currentUser
        }

}*/