package com.gquesada.notes.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gquesada.notes.R
import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.ui.notes.adapters.NoteListAdapter
import com.gquesada.notes.ui.notes.viewmodels.NoteListViewModel


class NoteListFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: NoteListViewModel
    private lateinit var notesRecyclerView: RecyclerView
    private val adapter by lazy {
        NoteListAdapter(
            onItemLongClicked = { item -> onListItemClicked(item) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        initViews(view)


        viewModel = ViewModelProvider(this)[NoteListViewModel::class.java]
        observe()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       view.findViewById<Button>(R.id.button_redirect)?.setOnClickListener(this)
        viewModel.onViewReady()
    }

    private fun initViews(view: View) {
        with(view) {
            notesRecyclerView = findViewById(R.id.notes_list)
            notesRecyclerView.adapter = adapter
            notesRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            notesRecyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun observe() {
        viewModel.noteListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }
    }

    private fun onListItemClicked(noteModel: NoteModel) {
        Toast.makeText(context, "El item: ${noteModel.title}, fue eliminado", Toast.LENGTH_LONG).show()
        viewModel.deleteNote(noteModel)
        viewModel.onViewReady()
    }

    override fun onClick(v: View?) {

        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, NoteAddFragment()).commit()
    }
}