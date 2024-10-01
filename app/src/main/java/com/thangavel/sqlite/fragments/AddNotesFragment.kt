package com.thangavel.sqlite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.thangavel.sqlite.databinding.AddNotesFragmentBinding

class AddNotesFragment : Fragment() {

    private lateinit var binding: AddNotesFragmentBinding

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

        binding.addNotes.setOnClickListener {
            Toast.makeText(
                requireContext(), "This is my Toast message!",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}