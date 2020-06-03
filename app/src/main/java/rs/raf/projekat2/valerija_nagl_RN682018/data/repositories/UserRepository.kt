package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.User

interface UserRepository {

    fun getUser(): Observable<User>
    fun setUser(name:String) :Observable<Unit>
}