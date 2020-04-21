package com.example.mockfragmenttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_two.*

class FragTwo: Fragment() {

    companion object {
        fun newInstance() = FragTwo()
    }

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
            (activity as MainActivity).showFragOne()
        }
    }

    fun handleImage(appName:String) {
        if(appName == "facebook") {
            logo.setImageResource(R.drawable.facebook)
        } else if (appName == "google") {
            logo.setImageResource(R.drawable.google_white)
        }
        else if (appName == "twitter") {
            logo.setImageResource(R.drawable.twitter)
    }
    }

}