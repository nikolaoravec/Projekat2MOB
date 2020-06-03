package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_class.*
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Class


class ClassViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(c: Class) {
        classPredmet.text = c.predmet
        classTip.text = c.tip
        classProfesor.text = c.nastavnik
        classUcionica.text = c.ucionica
        classGrupa.text = c.grupe
        classDan.text = c.dan
        classVreme.text = c.termin
    }

}