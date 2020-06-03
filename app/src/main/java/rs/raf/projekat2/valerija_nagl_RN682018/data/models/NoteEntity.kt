package rs.raf.projekat2.valerija_nagl_RN682018.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val title:String,
    val content:String,
    val isArchived:Boolean,
    val date: Date
)