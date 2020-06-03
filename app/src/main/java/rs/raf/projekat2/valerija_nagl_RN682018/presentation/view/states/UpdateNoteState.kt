package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states

 sealed class UpdateNoteState {

    object Success: UpdateNoteState()
    data class Error(val message: String): UpdateNoteState()

}