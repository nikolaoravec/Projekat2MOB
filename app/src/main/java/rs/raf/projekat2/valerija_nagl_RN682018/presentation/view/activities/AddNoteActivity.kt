package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments.BeleskeFragment
import java.util.*


class AddNoteActivity : AppCompatActivity(R.layout.activity_add_note) {

    companion object{
        const val ADD_KEY = "addKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){
        odustani_btn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        dodaj_btn.setOnClickListener {
            if (check()){
                val intent = Intent()
                val note = Note(0,et_title_add.text.toString(),et_content_add.text.toString(),false,
                    Date()
                )
                intent.putExtra(BeleskeFragment.RECEVED_KEY, note)
                intent.putExtra(BeleskeFragment.ADD_KEY, "add")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }



    private fun check():Boolean{
        if (et_content_add.text.isEmpty() || et_title_add.text.isEmpty()) {
            Toast.makeText(this, "Sva polja moraju da budu popunjena!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
