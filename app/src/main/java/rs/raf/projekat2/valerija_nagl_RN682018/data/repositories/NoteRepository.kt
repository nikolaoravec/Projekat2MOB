package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteFilter

interface NoteRepository {

    fun insert(note: Note): Completable

    fun getAll(): Observable<List<Note>>

    fun delete(id:Long): Completable

    fun getAllByFilter(noteFilter:NoteFilter): Observable<List<Note>>

    fun updateTitleAndContentById(id:Long,title:String,content:String):Completable

    fun update(id:Long, isArchived:Boolean):Completable

    fun getAllChartData(): Observable<List<Int>>

}