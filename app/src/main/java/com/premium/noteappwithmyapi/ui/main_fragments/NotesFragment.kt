package com.premium.noteappwithmyapi.ui.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.premium.noteappwithmyapi.R
import com.premium.noteappwithmyapi.adapters.NotesAdapter
import com.premium.noteappwithmyapi.databinding.FragmentNotesFramgentBinding
import com.premium.noteappwithmyapi.utils.Resource
import com.premium.noteappwithmyapi.utils.Resources.showProgressDialog
import com.premium.noteappwithmyapi.utils.Resources.showSnackBar
import com.premium.noteappwithmyapi.utils.Resources.stopProgressDialog
import com.premium.noteappwithmyapi.view_models.AuthViewModel
import com.premium.noteappwithmyapi.view_models.NoteViewModel


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesFramgentBinding
    private lateinit var viewAdapter: NotesAdapter
    private lateinit var notesViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentNotesFramgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(NoteViewModel::class.java)

        setupRecyclerView()
        getNotes()

        binding.AddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
        }

        binding.refresh.setOnRefreshListener {
            getNotes()
        }





    }


    private fun getNotes() = lifecycleScope.launchWhenStarted {
        notesViewModel.getNotes()
        notesViewModel.getNotesState.collect{
            when(it){
                is Resource.Error -> {
                    stopProgressDialog()
                    binding.refresh.isRefreshing = false
                    it.message?.let { message ->
                        showSnackBar(message)
                    }
                }
                is Resource.Loading -> {
                    binding.refresh.isRefreshing = true
                }
                is Resource.Success -> {
                    stopProgressDialog()
                    binding.refresh.isRefreshing = false
                    it.data?.let { notes ->
                        viewAdapter.differ.submitList(notes.notes)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        viewAdapter = NotesAdapter()
        binding.notesRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }
    }


}