package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states

sealed class DeleteNoteState {

    object Success: DeleteNoteState()
    data class Error(val message: String): DeleteNoteState()

}