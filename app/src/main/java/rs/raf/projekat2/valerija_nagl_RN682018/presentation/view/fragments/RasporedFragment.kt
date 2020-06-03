package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassFilter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.MainContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.adapter.ClassAdapter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.ClassesState
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.MainViewModel
import timber.log.Timber

class RasporedFragment : Fragment(R.layout.fragment_list) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private lateinit var adapter: ClassAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        rv_raspored.layoutManager = LinearLayoutManager(context)
        adapter = ClassAdapter()
        rv_raspored.adapter = adapter
    }

    private fun initListeners() {

        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.days_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDan.adapter = adapter
            }
        }

        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.gruops_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerGroup.adapter = adapter
            }
        }

        btn_find.setOnClickListener {
            val grupe = spinnerGroup.selectedItem.toString()
            val dan = spinnerDan.selectedItem.toString()
            val pp = profesorPredmet.text.toString()

            val classFilter = ClassFilter(grupe,dan,pp)
            mainViewModel.getAllByFilter(classFilter)
        }
    }

    private fun initObservers() {
        mainViewModel.classesState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.getAllClasses()
        mainViewModel.fetchAllClasses()

    }

    private fun renderState(state: ClassesState) {
        when (state) {
            is ClassesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.classes)
            }
            is ClassesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ClassesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is ClassesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        layout1.isVisible = !loading
        rv_raspored.isVisible = !loading
        layout2.isVisible = !loading
        loadingPb.isVisible = loading
    }
}