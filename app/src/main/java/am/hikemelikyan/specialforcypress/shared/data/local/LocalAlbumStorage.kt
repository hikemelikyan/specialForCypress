package am.hikemelikyan.specialforcypress.shared.data.local

import am.hikemelikyan.specialforcypress.model.dao.AlbumDao
import am.hikemelikyan.specialforcypress.model.entity.AlbumEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class LocalAlbumStorage : RoomDatabase() {
    abstract fun imageDao(): AlbumDao

    companion object {
        @Volatile
        private var INSTANCE: LocalAlbumStorage? = null

        fun getDatabase(context: Context): LocalAlbumStorage {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalAlbumStorage::class.java,
                    "cypress_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}