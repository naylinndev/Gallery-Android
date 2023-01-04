package dev.naylinn.gallery.networking.model

import com.google.gson.annotations.SerializedName
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.base.RoomMapper

data class PhotoResponse(
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("msg")
    val msg: String? = "",
    @SerializedName("current_page")
    val currentPage: Int = 0,
    @SerializedName("has_more_pages")
    val hasMorePages: Boolean = false,
    @SerializedName("data")
    val data: List<Photo>
)

data class Photo(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("make")
    val make: String? = "",
    @SerializedName("model")
    val model: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("categories")
    val categories: List<Category>
) : RoomMapper<PhotoEntity> {
    override fun mapToRoomEntity() = PhotoEntity(
        id = id,
        title = title,
        description = description,
        make = make,
        model = model,
        image = image,
        updatedAt = updatedAt,
        categories = categories.map { it.mapToRoomEntity() }
    )
}