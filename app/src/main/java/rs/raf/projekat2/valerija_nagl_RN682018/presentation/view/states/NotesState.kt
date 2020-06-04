package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states

import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note

sealed class NotesState {

    object Loading: NotesState()
        data class Success(val notes: List<Note>): NotesState()
        data class Error(val message: String): NotesState()
    object Update: NotesState()
    object Add: NotesState()
    object Delete: NotesState()
}