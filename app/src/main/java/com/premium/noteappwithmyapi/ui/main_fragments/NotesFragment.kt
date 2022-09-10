package com.premium.noteappwithmyapi.ui.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.premium.noteappwithmyapi.R
import com.premium.noteappwithmyapi.databinding.FragmentNotesFramgentBinding


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesFramgentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentNotesFramgentBinding.inflate(inflater, container, false)
        return binding.root
    }



}