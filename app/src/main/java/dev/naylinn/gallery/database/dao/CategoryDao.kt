package dev.naylinn.gallery.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.model.CategoryEntity

interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categoryEntities: List<CategoryEntity>)

    @Query("SELECT * FROM $CATEGORY_TABLE_NAME ORDER BY updatedAt ASC")
    suspend fun getCategoryLists(): List<CategoryEntity>
}