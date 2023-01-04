package dev.naylinn.gallery.database.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.PHOTO_TABLE_NAME
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categoryEntities: List<CategoryEntity>)

    @Query("SELECT * FROM $CATEGORY_TABLE_NAME ORDER BY updatedAt DESC")
    fun getCategoryLists(): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM $CATEGORY_TABLE_NAME WHERE id= :id LIMIT 1")
    fun getCategory(id: Int):  CategoryEntity?
}