package com.gquesada.notes.domain.usecases

import com.gquesada.notes.domain.models.NoteModel
import com.gquesada.notes.domain.repositories.NoteRepository

class CreateNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(noteModel: NoteModel) = noteRepository.addNote(noteModel = noteModel)
}