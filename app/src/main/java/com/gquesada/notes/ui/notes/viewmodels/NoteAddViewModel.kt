package com.gquesada.notes.ui.notes.viewmodels

import androidx.lifecycle.ViewModel
import com.gquesada.notes.data.datasources.LocalNoteDataSource
import com.gquesada.notes.data.repositories.NoteRepositoryImpl
import com.gquesada.notes.domain.repositories.NoteRepository

class NoteAddViewModel : ViewModel() {
    private val dataSource = LocalNoteDataSource()
    private val repository: NoteRepository = NoteRepositoryImpl(dataSource)
}