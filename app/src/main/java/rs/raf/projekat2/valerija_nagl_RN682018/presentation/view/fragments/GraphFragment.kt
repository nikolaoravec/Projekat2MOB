package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_chart.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.NoteContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.NotesState
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class GraphFragment : Fragment(R.layout.fragment_chart) {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
//            Timber.e("State observer ${it.toString()}")
            renderState(it)
        })
        noteViewModel.chartData.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            squareView.setData(it as MutableList<Int>)
            Timber.e(it.toString())
            day1.text = it[4].toString()
            day2.text = it[3].toString()
            day3.text = it[2].toString()
            day4.text = it[1].toString()
            day5.text = it[0].toString()
            squareView.invalidate()
        })

        noteViewModel.getAllNotes()
    }

    private fun renderState(state: NotesState) {
        when (state) {
            is NotesState.Success -> {
                noteViewModel.chartDataFetch()
                Timber.e("Render state ${state.notes.toString()}")
            }


        }
    }
}