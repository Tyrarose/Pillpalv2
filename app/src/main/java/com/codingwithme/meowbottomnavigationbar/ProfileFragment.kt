package com.codingwithme.meowbottomnavigationbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView




class ProfileFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        var isTextVisible = false


        val notificationBtn = view.findViewById<Button>(R.id.notificationBtn)
        val notificationContainer = view.findViewById<LinearLayout>(R.id.notificationContainer)
        notificationBtn.setOnClickListener(){
            if (isTextVisible) {
                notificationContainer.visibility = View.GONE
                isTextVisible = false
            } else {
                notificationContainer.visibility = View.VISIBLE
                isTextVisible = true
            }
        }

        val privacyPolicyInfo = view.findViewById<TextView>(R.id.privacyPolicyInfo)
        val privacyPolicyBtn = view.findViewById<Button>(R.id.privacyPolicyBtn)
        privacyPolicyBtn.setOnClickListener(){
            if (isTextVisible) {
                privacyPolicyInfo.text = ""
                privacyPolicyInfo.visibility = View.GONE
                isTextVisible = false
            } else {
                privacyPolicyInfo.setText(R.string.privacy_policy_info)
                privacyPolicyInfo.visibility = View.VISIBLE
                isTextVisible = true
            }
        }

        val termOfServicesInfo = view.findViewById<TextView>(R.id.termOfServicesInfo)
        val termsOfServiceBtn = view.findViewById<Button>(R.id.termsOfServiceBtn)
        termsOfServiceBtn.setOnClickListener(){
            if (isTextVisible) {
                termOfServicesInfo.text = ""
                termOfServicesInfo.visibility = View.GONE
                isTextVisible = false
            } else {
                termOfServicesInfo.setText(R.string.terms_of_service_info)
                termOfServicesInfo.visibility = View.VISIBLE
                isTextVisible = true
            }
        }

        val commGuideInfo = view.findViewById<TextView>(R.id.commGuideInfo)
        val commGuideBtn = view.findViewById<Button>(R.id.commGuideBtn)
        commGuideBtn.setOnClickListener(){
            if (isTextVisible) {
                commGuideInfo.text = ""
                commGuideInfo.visibility = View.GONE
                isTextVisible = false
            } else {
                commGuideInfo.setText(R.string.commGuideInfo)
                commGuideInfo.visibility = View.VISIBLE
                isTextVisible = true
            }
        }

        val suppInfo = view.findViewById<TextView>(R.id.suppInfo)
        val suppBtn = view.findViewById<Button>(R.id.suppBtn)
        suppBtn.setOnClickListener(){
            if (isTextVisible) {
                suppInfo.text = ""
                suppInfo.visibility = View.GONE
                isTextVisible = false
            } else {
                suppInfo.setText(R.string.supportInfo)
                suppInfo.visibility = View.VISIBLE
                isTextVisible = true
            }
        }

        val signOutBtn = view.findViewById<Button>(R.id.signOutBtn)
        val signOutContainer = view.findViewById<LinearLayout>(R.id.signOutContainer)
        signOutBtn.setOnClickListener(){
            if (isTextVisible) {
                signOutContainer.visibility = View.GONE
                isTextVisible = false
            } else {
                signOutContainer.visibility = View.VISIBLE
                isTextVisible = true
            }
        }
        return view

    }

    companion object {
       @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }




}




