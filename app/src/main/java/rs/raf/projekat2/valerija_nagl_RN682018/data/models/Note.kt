package rs.raf.projekat2.valerija_nagl_RN682018.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Note(
    val id: Long,
    var title: String,
    var content: String,
    var archive: Int,
    var date: Date
) : Parcelable