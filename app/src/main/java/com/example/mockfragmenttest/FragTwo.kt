package com.example.mockfragmenttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_two.*

class FragTwo: Fragment() {

    companion object {
        fun newInstance() = FragTwo()
    }

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

        btnBack.setOnClickListener {
            // save users input (username and password)
            usernameInput = username_input.text.toString()
            passwordInput = password_input.text.toString()
            notesInput = note_input.text.toString()


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

}