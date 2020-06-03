package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.viewholder.ClassViewHolder
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.viewholder.NoteViewHolder
import timber.log.Timber

class NoteAdapter (diff : NoteDiffCallback,
private val onNoteClicked: (Note,String) -> Unit) : ListAdapter<Note, NoteViewHolder>(diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_notes, parent, false)
        return NoteViewHolder(view) { adapterPosition: Int , response : String->
            val note = getItem(adapterPosition)
            onNoteClicked.invoke(note, response)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        Timber.e("Bind ${getItem(position)}")
        holder.bind(getItem(position))
    }

}