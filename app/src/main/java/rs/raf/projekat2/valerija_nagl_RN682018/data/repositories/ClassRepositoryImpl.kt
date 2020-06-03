package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.ClassDao
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.remote.ClassService
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassEntity
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassFilter
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Resource
import timber.log.Timber

class ClassRepositoryImpl(private val localDataSource: ClassDao, private val remoteDataSource: ClassService) : ClassRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    ClassEntity(
                        0,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Class>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Class(it.id, it.predmet, it.tip,it.nastavnik,it.grupe,it.dan,it.termin,it.ucionica)
                }
            }
    }

    override fun insert(c: Class): Completable {
        val classEntity = ClassEntity(c.id, c.predmet, c.tip,c.nastavnik,c.grupe,c.dan,c.termin,c.ucionica)
        return localDataSource
            .insert(classEntity)
    }

    override fun getAllByFilter(
        classFilter: ClassFilter
    ): Observable<List<Class>> {
        return localDataSource
            .getAllByFilter(classFilter.grupe,classFilter.dan,classFilter.profesorIliPredmet)
            .map {
                it.map {
                    Class(it.id, it.predmet, it.tip,it.nastavnik,it.grupe,it.dan,it.termin,it.ucionica)
                }
            }
    }


}