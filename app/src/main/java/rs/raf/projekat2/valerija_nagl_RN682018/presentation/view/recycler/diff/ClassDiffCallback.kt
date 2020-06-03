package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class

class ClassDiffCallback : DiffUtil.ItemCallback<Class>() {

    override fun areItemsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.predmet == newItem.predmet &&
                oldItem.tip == newItem.tip &&
                oldItem.nastavnik == newItem.nastavnik &&
                oldItem.ucionica == newItem.ucionica &&
                oldItem.grupe == newItem.grupe &&
                oldItem.dan == newItem.dan &&
                oldItem.termin == newItem.termin
    }

}