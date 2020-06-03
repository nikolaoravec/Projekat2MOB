package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.diff.ClassDiffCallback
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.viewholder.ClassViewHolder

class ClassAdapter : ListAdapter<Class, ClassViewHolder>(ClassDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_class, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}