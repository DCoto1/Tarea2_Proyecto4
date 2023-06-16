package com.gquesada.notes.data.repositories

import com.gquesada.notes.data.datasources.LocalNoteDataSource
import com.gquesada.notes.data.mappers.NoteMapper.toNote
import com.gquesada.notes.data.models.LocalNote
import com.gquesada.notes.data.models.LocalTag
import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.domain.repositories.NoteRepository

class NoteRepositoryImpl(
    private val localNoteDataSource: LocalNoteDataSource
) : NoteRepository {

    override fun getAllNotes(): List<NoteModel> =
        localNoteDataSource.getAllNotes()
            .map { item -> item.toNote() }

    override fun deleteNote(noteModel: NoteModel)  {
        localNoteDataSource.deleteNote(noteModel.id)
    }

    override fun addNote(noteModel: NoteModel) {
        val localNote = LocalNote(noteModel.id, noteModel.title, noteModel.description, LocalTag(noteModel.tag.id, noteModel.tag.title), noteModel.date)
        localNoteDataSource.addNote(localNote)
    }
}