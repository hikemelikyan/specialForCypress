package am.hikemelikyan.specialforcypress.model.dao

import am.hikemelikyan.specialforcypress.model.entity.AlbumEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert
    suspend fun insert(album: AlbumEntity)

    @Query("SELECT * FROM gallery_holder")
    fun getAllEntities(): List<AlbumEntity>

    @Query("SELECT * FROM gallery_holder ORDER BY id LIMIT :offset, :limit")
    fun getPagedEntities(offset: Int, limit: Int): List<AlbumEntity>

}