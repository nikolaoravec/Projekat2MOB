package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassEntity


@Database(
    entities = [ClassEntity::class],
    version = 5,
    exportSchema = false)
@TypeConverters()
abstract class ClassDataBase : RoomDatabase(){
    abstract fun getClassDao() : ClassDao
}

