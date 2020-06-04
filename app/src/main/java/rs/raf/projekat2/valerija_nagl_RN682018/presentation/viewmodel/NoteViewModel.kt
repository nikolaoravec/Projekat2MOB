package rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteFilter
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.NoteRepository
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.NoteContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(private val noteRepository: NoteRepository) :ViewModel(), NoteContract.ViewModel {

    override val changeDone: MutableLiveData<ChangeNoteState> = MutableLiveData()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    override val chartData: MutableLiveData<List<Int>> = MutableLiveData()
    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<NoteFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
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
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteNote(id: Long) {
        val subscription = noteRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Delete
                },
                {
                    notesState.value = NotesState.Error("Error happened while removing note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addNote(note: Note) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Add
                },
                {
                    notesState.value = NotesState.Error("Error happened while adding note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByFilter(noteFilter: NoteFilter) {
        publishSubject.onNext(noteFilter)
    }

    override fun updateTitleAndContentById(id: Long, title: String, content: String) {
        val subscription = noteRepository
            .updateTitleAndContentById(id, title, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Update
                },
                {
                    notesState.value = NotesState.Error("Error happened while updating note title/content")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(id: Long, archive: Boolean) {
        val subscription = noteRepository
            .update(id, archive)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Update
                },
                {
                    notesState.value = NotesState.Error("Error happened while updating note(Archive)")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun chartDataFetch() {
        val subscription = noteRepository
            .getAllChartData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
//                    notesState.value = NotesState.Success(it)
                    chartData.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}