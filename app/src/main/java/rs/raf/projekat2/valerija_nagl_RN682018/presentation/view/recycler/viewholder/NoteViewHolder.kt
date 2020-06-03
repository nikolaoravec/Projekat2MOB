package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_class.*
import kotlinx.android.synthetic.main.layout_item_notes.*
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import timber.log.Timber

class NoteViewHolder(override val containerView: View, onItemClicked: (Int,String) -> Unit) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    init{
        iw_archive.setOnClickListener{
            onItemClicked.invoke(adapterPosition,"archive")
        }
        iw_delete.setOnClickListener {
            onItemClicked.invoke(adapterPosition,"delete")
        }
        iw_edit.setOnClickListener {
            onItemClicked.invoke(adapterPosition,"edit")
        }
    }

    fun bind(note: Note) {
        note_title.text = note.title
        note_content.text = note.content
        if (note.archive == 0){
            iw_archive.setImageResource(R.drawable.archive)
        }else{
            iw_archive.setImageResource(R.drawable.unarchive)
        }
    }
}