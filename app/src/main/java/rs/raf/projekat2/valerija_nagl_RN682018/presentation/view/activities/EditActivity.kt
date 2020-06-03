package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_change_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.NoteContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.ChangeNoteState
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.states.NotesState
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.NoteViewModel

class EditActivity: AppCompatActivity(R.layout.activity_change_note) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    private var note : Note? = null

    companion object{
        const val EDIT_KEY = "editKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        parseIntent()
        initListeners()
        initObserver()
    }

    private fun initObserver() {
        noteViewModel.changeDone.observe(this, Observer {
            when(it) {
                is ChangeNoteState.Success -> {
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initListeners(){
        odustaniBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        izmeniBtn.setOnClickListener {
            if (check()){
                val title = et_title_change.text.toString()
                val content = et_content_change.text.toString()
                noteViewModel.updateTitleAndContentById(note!!.id, title, content)
            }
        }
    }

    private fun parseIntent() {
        intent?.let {

            note = it.getParcelableExtra(EDIT_KEY)

            val title : String? = note?.title
            val content : String? = note?.content

            et_title_change.setText(title)
            et_content_change.setText(content)
        }
    }



    private fun check():Boolean{
        if (et_content_change.text.isEmpty() || et_title_change.text.isEmpty()) {
            Toast.makeText(this, "Sva polja moraju da budu popunjena!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}