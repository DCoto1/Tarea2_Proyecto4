package com.gquesada.notes.ui.notes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gquesada.notes.data.datasources.LocalNoteDataSource
import com.gquesada.notes.data.repositories.NoteRepositoryImpl
import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.domain.repositories.NoteRepository
import com.gquesada.notes.domain.usecases.CreateNotesUseCase
import com.gquesada.notes.domain.usecases.DeleteNotesUseCase
import com.gquesada.notes.domain.usecases.GetNotesUseCase

class NoteListViewModel : ViewModel() {
    private val dataSource = LocalNoteDataSource
    private val repository: NoteRepository = NoteRepositoryImpl(dataSource)
    private val getNotesUseCase = GetNotesUseCase(repository)
    private val delNotesUseCase = DeleteNotesUseCase(repository)
    private val createNotesUseCase = CreateNotesUseCase(repository)

    private val _noteListLiveData = MutableLiveData<List<NoteModel>>()
    val noteListLiveData: LiveData<List<NoteModel>>
        get() = _noteListLiveData

    fun onViewReady() {
        val list = getNotesUseCase.execute()
        _noteListLiveData.value = list
    }

    fun deleteNote(noteModel: NoteModel) {
        delNotesUseCase.execute(noteModel);
    }

    fun addNote(noteModel: NoteModel){
        createNotesUseCase.execute(noteModel)
    }

//    class NoteAddViewModel : ViewModel() {
//        private val dataSource = LocalNoteDataSource()
//        private val repository: NoteRepository = NoteRepositoryImpl(dataSource)
//        private val getNotesUseCase = GetNotesUseCase(repository)
//        private val createNotesUseCase = CreateNotesUseCase(repository)
//        private val _noteListLiveData = MutableLiveData<List<NoteModel>>()
//        val noteListLiveData: LiveData<List<NoteModel>>
//            get() = _noteListLiveData
//        fun onViewReady() {
//            val list = getNotesUseCase.execute()
//            _noteListLiveData.value = list
//        }
//
//        fun onButtonClicked(noteModel: NoteModel) {
//            createNotesUseCase.execute(noteModel)
//        }
}