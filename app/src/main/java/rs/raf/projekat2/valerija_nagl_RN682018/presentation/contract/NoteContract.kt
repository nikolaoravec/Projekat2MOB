package rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteFilter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.*

interface NoteContract {

    interface ViewModel {

        val notesState: LiveData<NotesState>
        val changeDone: LiveData<ChangeNoteState>
        val chartData: MutableLiveData<List<Int>>

        fun getAllNotes()
        fun deleteNote(id : Long)
        fun addNote(note : Note)
        fun getAllByFilter(noteFilter:NoteFilter)
        fun updateTitleAndContentById(id:Long,title:String,content:String)
        fun update(id:Long,archive:Boolean)
        fun chartDataFetch()
    }

}