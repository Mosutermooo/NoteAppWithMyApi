package com.premium.noteappwithmyapi.ui.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.premium.noteappwithmyapi.R
import com.premium.noteappwithmyapi.databinding.FragmentAddNoteBinding
import com.premium.noteappwithmyapi.utils.Resources


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Resources.setupToolBar(
            binding.toolbar,
            requireActivity() as AppCompatActivity,
            getString(R.string.addNote),
            null
        )

    }


}