package rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.User

interface UserContract {

        interface ViewModel {

            val user: LiveData<User>

            fun getUser()
            fun setUser(name:String)

        }

}