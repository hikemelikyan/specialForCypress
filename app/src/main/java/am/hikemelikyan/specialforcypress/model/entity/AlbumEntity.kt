package am.hikemelikyan.specialforcypress.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("gallery_holder")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title: String,
    val images: String
)