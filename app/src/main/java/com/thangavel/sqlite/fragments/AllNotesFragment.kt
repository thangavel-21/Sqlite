package com.thangavel.sqlite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.thangavel.sqlite.NotesDatabaseHelper
import com.thangavel.sqlite.R
import com.thangavel.sqlite.databinding.AllNotesFragmentBinding
import com.thangavel.sqlite.helper.HelperFunctions
import com.thangavel.sqlite.interfaces.NoteDetail

class AllNotesFragment : Fragment(), NoteDetail {

    private lateinit var binding: AllNotesFragmentBinding
    private lateinit var db: NotesDatabaseHelper
    private var adapter: NotesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllNotesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDatabaseHelper(requireContext())
        loadAdapter()

        binding.addNotes.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_manager, AddNotesFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun loadAdapter() {
        adapter = NotesAdapter(db.getAllNotes(), this@AllNotesFragment)
        binding.notesList.layoutManager = LinearLayoutManager(requireContext())
        binding.notesList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter?.refreshData(db.getAllNotes())
    }

    override fun updateNote(id: Int) {
        val fragment = UpdateFragment()
        val bundle = Bundle().apply {
            putInt("noteId", id)
        }
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_manager, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun deleteNote(id: Int) {
        db.deleteNotes(id)
        adapter?.refreshData(db.getAllNotes())
        HelperFunctions.showShortToast(this.requireContext(), "Note Deleted")
    }
}