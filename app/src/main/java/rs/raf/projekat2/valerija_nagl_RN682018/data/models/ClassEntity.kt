package rs.raf.projekat2.valerija_nagl_RN682018.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classes")
data class ClassEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val predmet : String,
    val tip : String,
    val nastavnik : String,
    val grupe : String,
    val dan : String,
    val termin : String,
    val ucionica : String
)
