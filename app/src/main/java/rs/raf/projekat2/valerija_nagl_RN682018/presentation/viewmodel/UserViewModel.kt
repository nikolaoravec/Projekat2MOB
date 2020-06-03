package rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.User
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.UserRepository
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.UserContract
import timber.log.Timber

class UserViewModel(private val userRepository: UserRepository) : ViewModel(), UserContract.ViewModel{

    override val user: MutableLiveData<User> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun getUser() {
        val subscription = userRepository
            .getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    user.value = it
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("On complete")
                }
            )
        subscriptions.add(subscription)
    }

    override fun setUser(name: String) {
        val subscription = userRepository
            .setUser(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(" User inserted")
                },
                {
                    Timber.e(" User inserted error")
                },
                {
                    Timber.e("On complete")
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}