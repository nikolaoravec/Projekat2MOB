package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import android.content.SharedPreferences
import io.reactivex.Observable
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.UserData

class SharedPreferancesUser  (private val sharedPreferences: SharedPreferences
    ) : UserDataSource {

        companion object {
            const val USER_NAME_KEY = "userName"
        }

        override fun setUserData(name:String): Observable<Unit> {
            return Observable.fromCallable {
                val editor = sharedPreferences.edit()
                editor.putString(USER_NAME_KEY, name)
                editor.apply()
            }
        }

        override fun getUserData(): Observable<UserData> {
            return Observable.fromCallable {
                val name = sharedPreferences.getString(USER_NAME_KEY, "") ?: ""
                UserData(name)
            }
        }
}