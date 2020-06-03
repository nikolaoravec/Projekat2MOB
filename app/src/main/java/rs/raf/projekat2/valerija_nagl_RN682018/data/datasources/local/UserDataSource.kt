package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.UserData


interface UserDataSource {
    fun getUserData(): Observable<UserData>
    fun setUserData(name:String): Observable<Unit>
}