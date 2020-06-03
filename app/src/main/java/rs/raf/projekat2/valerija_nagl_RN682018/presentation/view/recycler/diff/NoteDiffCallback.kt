package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import timber.log.Timber

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
//        Timber.e("Usao sam u areItemsTheSame");

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
//        Timber.e((oldItem.title == newItem.title
//                && oldItem.content == newItem.content
//                && oldItem.archive == newItem.archive).toString());

        return oldItem.title == newItem.title
                && oldItem.content == newItem.content
                && oldItem.archive == newItem.archive
    }
}