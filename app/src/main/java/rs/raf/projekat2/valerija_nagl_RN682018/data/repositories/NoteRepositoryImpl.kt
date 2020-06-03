package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.NoteDao
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity

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

    override fun getByFilter(titleContent: String): Observable<List<Note>> {
        return noteDao
            .getByFilter(titleContent)
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

    override fun getByFilterArchive(archive: Int): Observable<List<Note>> {
        return noteDao
            .getByFilterArchive(archive)
            .map {
                it.map {
                    Note(it.id, it.title, it.content, it.isArchived, it.date)
                }
            }
    }
}