package dev.naylinn.gallery.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.networking.base.DomainMapper
import dev.naylinn.gallery.networking.model.Category
import org.jetbrains.annotations.NotNull

@Entity(tableName = CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @NotNull @PrimaryKey
    val id: Int = 0,
    val categoryName: String? = "",
    val categoryImage: String? = "",
    val updatedAt: String? = "",
) : DomainMapper<Category> {
    override fun mapToDomainModel() = Category(
        id = id,
        categoryName = categoryName,
        categoryImage = categoryImage,
        updatedAt = updatedAt
    )
}