package com.example.mockfragmenttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.frag_one.*
import kotlinx.android.synthetic.main.frag_one.btnLogon

class FragOne: Fragment() {

    private val AUTH_REQUEST_CODE = 2000
    private var user : FirebaseUser? = null

    companion object {
        fun newInstance() = FragOne()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnFacebook.setOnClickListener {
            (activity as MainActivity).showFragTwo("facebook")
        }
        btnGit.setOnClickListener {
            (activity as MainActivity).showFragTwo("github")
        }
        btnPin.setOnClickListener {
            (activity as MainActivity).showFragTwo("pinterest")
        }
        btnGoogle.setOnClickListener {
            (activity as MainActivity).showFragTwo("google")
        }
        btnTwitter.setOnClickListener {
            (activity as MainActivity).showFragTwo("twitter")
        }
        btnUsbank.setOnClickListener {
            (activity as MainActivity).showFragTwo("usbank")
        }
        btnLogon.setOnClickListener {
            logon()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK){
            if (resultCode == AUTH_REQUEST_CODE)
                user =  FirebaseAuth.getInstance().currentUser
        }

    }


}