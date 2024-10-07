package com.thangavel.sqlite.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thangavel.sqlite.databinding.NotesListItemBinding
import com.thangavel.sqlite.dto.NotesDto

class NotesAdapter(private var notes: List<NotesDto>, private val fragment: AllNotesFragment) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(private val binding: NotesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(notesDto: NotesDto, fragment: AllNotesFragment) {
            binding.apply {
                title.text = notesDto.title
                content.text = notesDto.content
                editNote.setOnClickListener {
                    fragment.updateNote(notesDto.id)
                }
                deleteNote.setOnClickListener {
                    fragment.deleteNote(notesDto.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindItems(notes.reversed()[position], fragment)
    }

    override fun getItemCount() = notes.size

    fun refreshData(newNotes: List<NotesDto>) {
        notes = newNotes
        notifyDataSetChanged()
    }

}