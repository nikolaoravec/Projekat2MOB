package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states

sealed class ChangeNoteState {
    object Success: ChangeNoteState()
    data class Error(val message: String): ChangeNoteState()
}