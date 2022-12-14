package com.premium.noteappwithmyapi.utils

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.premium.noteappwithmyapi.R

object Resources {

    private lateinit var dialog: Dialog


    fun Fragment.showSnackBar(text: String){
        val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    fun Fragment.initProgressDialog(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
    fun Fragment.showProgressDialog(){
        dialog.show()
    }
    fun Fragment.stopProgressDialog(){
        dialog.dismiss()
    }

    fun setupToolBar(toolbar: Toolbar, activity: AppCompatActivity, title: String? = null, subTitle: String? = null){
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            actionBar.title = title
            actionBar.subtitle = subTitle
        }
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
    }



}