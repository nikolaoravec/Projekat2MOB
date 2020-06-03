package rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassFilter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.AddClassState
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.ClassesState

interface MainContract {

    interface ViewModel {

        val classesState: LiveData<ClassesState>
        val addDone: LiveData<AddClassState>

        fun fetchAllClasses()
        fun getAllClasses()
        fun addClass(c: Class)
        fun getAllByFilter(classFilter : ClassFilter)

    }

}