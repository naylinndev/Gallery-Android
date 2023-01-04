package dev.naylinn.gallery.database.dao

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.PHOTO_TABLE_NAME
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity

interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotos(photoEntities: List<PhotoEntity>)

    @Query("SELECT * FROM $PHOTO_TABLE_NAME ORDER BY updatedAt ASC")
    fun getPhotoLists(): PagingSource<Int, PhotoEntity>
}