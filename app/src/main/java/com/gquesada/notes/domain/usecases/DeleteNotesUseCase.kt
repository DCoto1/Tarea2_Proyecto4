package com.gquesada.notes.domain.usecases

import com.gquesada.notes.data.models.LocalNote
import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.domain.repositories.NoteRepository

class DeleteNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(noteModel: NoteModel) = noteRepository.deleteNote(noteModel = noteModel)
}