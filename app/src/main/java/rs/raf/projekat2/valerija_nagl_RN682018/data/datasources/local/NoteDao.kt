package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity

@Dao
abstract class NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entity: List<NoteEntity>): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE ((title LIKE '%'|| :titleContent ||'%' AND isArchived = :isArchived) OR (content LIKE '%'|| :titleContent ||'%' AND isArchived = :isArchived))")
    abstract fun getByFilter(titleContent: String,isArchived: Boolean): Observable<List<NoteEntity>>

    @Query("DELETE FROM notes")
    abstract fun deleteAll() : Completable

    @Query("DELETE FROM notes WHERE id=:id")
    abstract  fun delete(id:Long) : Completable

    @Query("UPDATE notes SET title = :title,content = :content WHERE id == :id")
    abstract fun updateTitleAndContentById(id: Long, title: String, content:String): Completable

    @Query("UPDATE notes SET isArchived = :isArchived WHERE id == :id")
    abstract fun update(id: Long, isArchived:Boolean): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAllChartData(): Observable<List<NoteEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<NoteEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}