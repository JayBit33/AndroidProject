package com.example.mockfragmenttest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_one.*

class FragOne: Fragment() {

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
    }
}