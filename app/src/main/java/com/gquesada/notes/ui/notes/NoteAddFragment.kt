package com.gquesada.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.gquesada.notes.R
import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.domain.models.TagModel
import com.gquesada.notes.ui.notes.viewmodels.NoteListViewModel
import kotlin.math.roundToInt

class NoteAddFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: NoteListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_add, container, false)
        viewModel = ViewModelProvider(this)[NoteListViewModel::class.java]

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_add)?.setOnClickListener(this)
        viewModel.onViewReady()
    }

    override fun onClick(v: View?) {
        val title = view?.findViewById<EditText>(R.id.title)?.text.toString()
        val description = view?.findViewById<EditText>(R.id.description)?.text.toString()
        val tag = TagModel((Math.random()*10).roundToInt(), view?.findViewById<EditText>(R.id.tag)?.text.toString())
        val date = view?.findViewById<EditText>(R.id.date)?.text.toString()
        viewModel.addNote(noteModel = NoteModel(id = (Math.random()*10).roundToInt(), title = title, description = description, tag = tag, date = Integer.parseInt(date)))
        Toast.makeText(context, "El item: ${title}, fue agregado!", Toast.LENGTH_LONG).show()
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, NoteListFragment()).commit()
    }


}