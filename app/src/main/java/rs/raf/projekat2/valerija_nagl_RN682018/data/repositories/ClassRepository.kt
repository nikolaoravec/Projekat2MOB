package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Resource
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassFilter


interface ClassRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Class>>
    fun insert(c: Class): Completable
    fun getAllByFilter(classFilter:ClassFilter): Observable<List<Class>>

}