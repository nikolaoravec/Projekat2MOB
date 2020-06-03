package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states


sealed class AddClassState {
    object Success: AddClassState()
    data class Error(val message: String): AddClassState()
}