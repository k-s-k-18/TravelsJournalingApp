package com.example.traveladventures.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.traveladventures.model.Note
import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.Trip
import java.util.UUID

class NoteViewModel: ViewModel() {
    private val travelAdventuresRepository = TravelAdventuresRepository.get()

    suspend fun getNote(noteId: UUID): Note {
        return travelAdventuresRepository.getNote(noteId)
    }

    suspend fun insertNote(note: Note){
        travelAdventuresRepository.insertNote(note)
    }

    suspend fun deleteNote(note: Note){
        travelAdventuresRepository.deleteNote(note)
    }

    suspend fun updateNote(note: Note){
        travelAdventuresRepository.updateNote(note)
    }

    suspend fun getNotes():List<Note>{
        return travelAdventuresRepository.getNotes()
    }
}