package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.NoteContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities.AddNoteActivity
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities.EditActivity
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.*
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class BeleskeFragment : Fragment(R.layout.fragment_beleske) {

    companion object {
        const val REQUEST_CODE = 1
        const val RECEVED_KEY = "requestCode"
        const val ADD_KEY = "fromAdd"
    }

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    private lateinit var noteAdapter: NoteAdapter

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
        rv_beleske.layoutManager = LinearLayoutManager(context)
        noteAdapter =
            NoteAdapter(NoteDiffCallback()) { note: Note ,response : String ->
                if (response=="archive"){
                    if (note.archive == 0) {
                        note.archive = 1;
//                    this.iw_archive.setImageResource(R.drawable.unarchive)
                        noteViewModel.update(note.id,note.archive)
                    } else if (note.archive == 1) {
                        note.archive = 0;
//                    this.iw_archive.setImageResource(R.drawable.archive)
                        noteViewModel.update(note.id,note.archive)
                    }
                }else if(response=="delete"){
                    noteViewModel.deleteNote(note.id)
                }else{
                    val intent = Intent(this.activity,EditActivity::class.java)
                    intent.putExtra(EditActivity.EDIT_KEY, note)
                    startActivityForResult(intent, REQUEST_CODE)
                }

            }
        rv_beleske.adapter = noteAdapter
    }

    private fun initListeners() {

        search_titleCo.addTextChangedListener {
            noteViewModel.getAllByFilter(search_titleCo.text.toString())
        }

        btn_plus.setOnClickListener {
            val intent = Intent(this.activity,AddNoteActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        
        switch_archive.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                noteViewModel.getAllNotes()
            }
            if (!isChecked){
                noteViewModel.getAllByArchive(0)
            }
        }
    }

    private fun initObservers() {

        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        noteViewModel.changeDone.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderAddState(it)
        })

        noteViewModel.getAllNotes()
    }

    private fun renderState(state: NotesState) {
        when (state) {
            is NotesState.Success -> {
                showLoadingState(false)
                noteAdapter.submitList(state.notes)
            }
            is NotesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NotesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun renderAddState(state: ChangeNoteState) {
        when (state) {
            is ChangeNoteState.Success -> {
                showLoadingState(false)
            }
            is ChangeNoteState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showLoadingState(loading: Boolean) {
        layoutSearch.isVisible = !loading
        rv_beleske.isVisible = !loading
        btn_plus.isVisible = !loading
        loadingPb.isVisible = loading
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    if (resultCode == Activity.RESULT_OK) {
                        val activity : String? = intent?.extras?.getString(ADD_KEY)
                        if(activity == "add"){
                            intent?.let {

                                val note : Note? = intent.extras?.getParcelable(RECEVED_KEY)
                                if (note!=null){
                                    noteViewModel.addNote(note)
                                }
                            }
                        }else if(activity == "edit"){
                            intent?.let {
                                val note : Note? = intent.extras?.getParcelable(RECEVED_KEY)
                                if (note!=null){
                                    noteViewModel.updateTitleAndContentById(note.id,note.title,note.content)
                                }
                            }
                        }
                    }else{
                        return
                    }
                }
            }
        }
    }
}