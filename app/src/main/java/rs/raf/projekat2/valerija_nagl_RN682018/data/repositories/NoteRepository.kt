package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity

interface NoteRepository {

    fun insert(note: Note): Completable

    fun getAll(): Observable<List<Note>>

    fun delete(id:Long): Completable

    fun getByFilter(titleContent:String): Observable<List<Note>>

    fun updateTitleAndContentById(id:Long,title:String,content:String):Completable

    fun update(id:Long, isArchived:Boolean):Completable

    fun getByFilterArchive(archive: Int): Observable<List<Note>>


}