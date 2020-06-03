package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassEntity

@Dao
abstract class ClassDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: ClassEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<ClassEntity>): Completable

    @Query("SELECT * FROM classes")
    abstract fun getAll(): Observable<List<ClassEntity>>

    @Query("DELETE FROM classes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<ClassEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM classes WHERE (grupe LIKE '%' || :grupe || '%') AND (dan LIKE :dan || '%') AND ( (nastavnik LIKE '%' || :profesorIliPredmet || '%') OR (predmet LIKE '%' || :profesorIliPredmet || '%'))")
    abstract fun getAllByFilter(grupe:String,dan:String, profesorIliPredmet:String): Observable<List<ClassEntity>>

}
