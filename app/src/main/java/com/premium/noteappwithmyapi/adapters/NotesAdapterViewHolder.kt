package com.premium.noteappwithmyapi.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.model.notes.Note
import com.premium.noteappwithmyapi.databinding.NotesItemViewBinding

sealed class NotesAdapterViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class NotesViewAdapter(
        private val binding: NotesItemViewBinding
    ) : NotesAdapterViewHolder(binding) {
        fun bind(note: Note){
            binding.NoteText.text = note.note
        }
    }

}