package com.thangavel.sqlite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thangavel.sqlite.NotesDatabaseHelper
import com.thangavel.sqlite.R
import com.thangavel.sqlite.databinding.AddNotesFragmentBinding
import com.thangavel.sqlite.dto.NotesDto
import com.thangavel.sqlite.helper.HelperFunctions

class UpdateFragment : Fragment() {
    private lateinit var binding: AddNotesFragmentBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNotesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDatabaseHelper(requireContext())
        val noteDetails = arguments?.getInt("noteId")?.let { db.getNoteByID(it) }

        binding.apply {
            addNotes.text = getString(R.string.update_note)
            submit.text = getString(R.string.update)
            etTitle.setText(noteDetails?.title)
            etContent.setText(noteDetails?.content)

            submit.setOnClickListener {
                if (!etTitle.text.isNullOrEmpty() && !etContent.text.isNullOrEmpty()) {
                    db.updateNotes(
                        NotesDto(
                            arguments?.getInt("noteId") ?: 0,
                            etTitle.text.toString(), etContent.text.toString()
                        )
                    )
                    HelperFunctions.showShortToast(
                        requireContext(),
                        "Notes Updated!!!"
                    )
                    parentFragmentManager.popBackStack()
                } else {
                    HelperFunctions.showShortToast(
                        requireContext(),
                        "Title & Content Should not empty"
                    )
                }
            }
        }
    }
}