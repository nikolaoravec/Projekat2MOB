package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.converters.DateConverter
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2,
    exportSchema = false)

@TypeConverters(DateConverter::class)
abstract class NoteDataBase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
}