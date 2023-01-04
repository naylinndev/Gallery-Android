package dev.naylinn.gallery.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.PHOTO_TABLE_NAME
import dev.naylinn.gallery.networking.base.DomainMapper
import dev.naylinn.gallery.networking.model.Category
import dev.naylinn.gallery.networking.model.Photo
import org.jetbrains.annotations.NotNull

@Entity(tableName = PHOTO_TABLE_NAME)
data class PhotoEntity(
    @NotNull @PrimaryKey
    val id: Int = 0,
    val title: String? = "",
    val description: String? = "",
    val make: String? = "",
    val model: String? = "",
    val image: String? = "",
    val updatedAt: Long = 0,
    val categories: List<CategoryEntity>,
    val isFavorite : Boolean = false
) : DomainMapper<Photo> {
    override fun mapToDomainModel() = Photo(
        id = id,
        title = title,
        description = description,
        make = make,
        model = model,
        image = image,
        updatedAt = updatedAt,
        categories = categories.map { it.mapToDomainModel() }
    )
}