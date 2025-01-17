package com.thangavel.sqlite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.thangavel.sqlite.NotesDatabaseHelper
import com.thangavel.sqlite.databinding.AddNotesFragmentBinding
import com.thangavel.sqlite.dto.NotesDto
import com.thangavel.sqlite.helper.HelperFunctions

class AddNotesFragment : Fragment() {
    private lateinit var binding: AddNotesFragmentBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AddNotesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDatabaseHelper(requireContext())

        binding.apply {
            submit.setOnClickListener {
                if (!etTitle.text.isNullOrEmpty() && !etContent.text.isNullOrEmpty()) {
                    db.insertNotes(
                        NotesDto(
                            0,
                            etTitle.text.toString(), etContent.text.toString()
                        )
                    )
                    Toast.makeText(requireContext(), "Notes Saved!!!", Toast.LENGTH_SHORT).show()
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