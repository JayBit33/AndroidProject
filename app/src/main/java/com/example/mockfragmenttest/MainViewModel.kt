package com.example.mockfragmenttest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel : ViewModel() {

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