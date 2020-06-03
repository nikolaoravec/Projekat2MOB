package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states

import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class


sealed class ClassesState {
    object Loading: ClassesState()
    object DataFetched: ClassesState()
    data class Success(val classes: List<Class>): ClassesState()
    data class Error(val message: String): ClassesState()
}