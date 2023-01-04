package dev.naylinn.gallery.networking.model

import com.google.gson.annotations.SerializedName
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.networking.base.RoomMapper

data class CategoryResponse(
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("msg")
    val msg: String? = "",
    @SerializedName("current_page")
    val currentPage: Int = 0,
    @SerializedName("has_more_pages")
    val hasMorePages: Boolean = false,
    @SerializedName("data")
    val data: List<Category>
)

data class Category(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("category_name")
    val categoryName: String? = "",
    @SerializedName("category_image")
    val categoryImage: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
) : RoomMapper<CategoryEntity> {
    override fun mapToRoomEntity() = CategoryEntity(
        id = id,
        categoryName = categoryName,
        categoryImage = categoryImage,
        updatedAt = updatedAt
    )
}