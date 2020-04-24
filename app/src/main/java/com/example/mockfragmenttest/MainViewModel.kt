package com.example.mockfragmenttest

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel : ViewModel() {

    private lateinit var firestore : FirebaseFirestore
    private var _applications: MutableLiveData<ArrayList<Applications>> = MutableLiveData<ArrayList<Applications>>()
    private var _app = Applications()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToApplications()
    }

    private fun listenToApplications() {
        firestore.collection("Applications").addSnapshotListener{
            snapshot, e ->
            if (e != null){
                Log.w(TAG, "Listen Failed", e)
                return@addSnapshotListener
            }
            if (snapshot != null){
                val allApplications = ArrayList<Applications>()
                val documents = snapshot.documents
                documents.forEach{
                   val application =  it.toObject(Applications::class.java)
                    if (application != null){

                        allApplications.add(application!!)
                    }
                }
                _applications.value = allApplications
            }

        }

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

    internal var applications: MutableLiveData<ArrayList<Applications>>
        get() {return _applications}
        set(value) {_applications = value}
}