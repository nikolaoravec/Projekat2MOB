package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.NoteDao
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteFilter
import timber.log.Timber
import java.util.*

class NoteRepositoryImpl (private val noteDao: NoteDao) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(note.id,note.title,note.content,note.isArchived,note.date)
        return noteDao.insert(noteEntity)
    }

    override fun getAll(): Observable<List<Note>> {
        return noteDao
            .getAll()
            .map {
               it.map {
                   Note(it.id, it.title, it.content, it.isArchived, it.date)
               }
            }
    }

    override fun delete(id: Long): Completable {
        return noteDao.delete(id)
    }

    override fun getAllByFilter(noteFilter: NoteFilter): Observable<List<Note>> {
        return noteDao
            .getByFilter(noteFilter.titleContent, noteFilter.isArchived!!)
            .map {
                it.map {
                    Note(it.id, it.title, it.content, it.isArchived, it.date)
                }
            }
    }

    override fun updateTitleAndContentById(id: Long, title: String, content: String): Completable {
        return noteDao.updateTitleAndContentById(id, title, content)
    }

    override fun update(id: Long, isArchived: Boolean): Completable {
        return noteDao.update(id, isArchived)
    }

//    @SuppressLint("CheckResult")
    override fun getAllChartData(): Observable<List<Int>> {
        val ints: MutableList<Int> = mutableListOf()
        val list: MutableList<Note> = mutableListOf()

        return noteDao.getAllChartData().map {
            Timber.e("Usao sam")
            Timber.e(it.toString())
            it.map {
                val note = Note(it.id, it.title, it.content, it.isArchived, it.date)
                list.add(note)
            }
                val listToSubmit2 : MutableList<Note> = mutableListOf()
                listToSubmit2.addAll(list)
                Timber.e(listToSubmit2.toString())

                Timber.e(list.toString())

                var int1 = 0
                var int2 = 0
                var int3 = 0
                var int4 = 0
                var int5 = 0

                for(i in 0 until listToSubmit2.size){
                    Timber.e(i.toString())
                    val date : Date = listToSubmit2[i].date
                    val day = date.day
                    if(day == Date().day){
                        int1+=1
                    }
                    if(day == Date().day-1){
                        int2+=1
                    }
                    if(day == Date().day-2){
                        int3+=1
                    }
                    if(day == Date().day-3){
                        int4+=1
                    }
                    if(day == Date().day-4){
                        int5+=1
                    }
                }

                ints.add(int1)
                ints.add(int2)
                ints.add(int3)
                ints.add(int4)
                ints.add(int5)

                ints
        }
    }
}


