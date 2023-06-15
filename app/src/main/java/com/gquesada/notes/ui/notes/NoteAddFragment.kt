package com.gquesada.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gquesada.notes.R
import com.gquesada.notes.ui.notes.viewmodels.NoteAddViewModel
import com.gquesada.notes.ui.notes.viewmodels.NoteListViewModel

class NoteAddFragment : Fragment() {
    private lateinit var viewModel: NoteAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_add, container, false)
        viewModel = ViewModelProvider(this)[NoteAddViewModel::class.java]

        return view
    }
}