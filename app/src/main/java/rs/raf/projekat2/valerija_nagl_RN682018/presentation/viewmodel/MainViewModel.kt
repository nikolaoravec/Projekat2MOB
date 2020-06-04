package rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassFilter
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Resource
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.ClassRepository
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.MainContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.ClassesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val classRepository: ClassRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val classesState: MutableLiveData<ClassesState> = MutableLiveData()
//    override val addDone: MutableLiveData<AddClassState> = MutableLiveData()

    private val publishSubject: PublishSubject<ClassFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                classRepository
                    .getAllByFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    classesState.value = ClassesState.Success(it)
                },
                {
                    classesState.value = ClassesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllClasses() {
        val subscription = classRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> classesState.value = ClassesState.Loading
                        is Resource.Success -> classesState.value = ClassesState.DataFetched
                        is Resource.Error -> classesState.value = ClassesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    classesState.value = ClassesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllClasses() {
        val subscription = classRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    classesState.value = ClassesState.Success(it)
                },
                {
                    classesState.value = ClassesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

//    override fun addClass(c: Class) {
//        val subscription = classRepository
//            .insert(c)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    addDone.value = AddClassState.Success
//                },
//                {
//                    addDone.value = AddClassState.Error("Error happened while adding movie")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
//    }

    override fun getAllByFilter(classFilter : ClassFilter){
        publishSubject.onNext(classFilter)
//        val subscription = classRepository
//            .getAllByFilter(grupe, dan, profesorIliPredmet)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    classesState.value = ClassesState.Success(it)
//                },
//                {
//                    classesState.value = ClassesState.Error("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}