package dev.naylinn.gallery.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.PHOTO_TABLE_NAME
import dev.naylinn.gallery.database.REMOTE_KEY_TABLE_NAME
import dev.naylinn.gallery.networking.base.DomainMapper
import dev.naylinn.gallery.networking.model.Category
import dev.naylinn.gallery.networking.model.Photo
import org.jetbrains.annotations.NotNull

@Entity(tableName = REMOTE_KEY_TABLE_NAME)
data class RemoteKeyEntity(
    @NotNull @PrimaryKey
    val type: String = "",
    val nextPage: Int = 1,
    val hasNextPage: Boolean = false

)