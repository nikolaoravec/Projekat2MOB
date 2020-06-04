package rs.raf.projekat2.valerija_nagl_RN682018.extensions

import android.content.res.Resources

fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()