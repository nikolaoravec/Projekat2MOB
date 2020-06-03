package rs.raf.projekat2.valerija_nagl_RN682018.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.UserDataSource
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.User
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.toUser

class UserRepositoryImpl (private val userDataSource: UserDataSource) : UserRepository {

    override fun getUser(): Observable<User> {
        return userDataSource
            .getUserData()
            .map {
                it.toUser()
            }
    }

    override fun setUser(name : String): Observable<Unit> {
        return userDataSource.setUserData(name)
    }
}