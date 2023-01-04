package dev.naylinn.gallery.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.PHOTO_TABLE_NAME
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity

@Dao
interface PhotoDao {
    @Transaction
    suspend fun savePhotos(photoEntities: List<PhotoEntity>){
        photoEntities.map {
            val photoEntity = getPhoto(id = it.id)
            if (photoEntity != null) {
                it.isFavorite = photoEntity.isFavorite
            }
            savePhoto(photoEntity = it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhoto(photoEntity: PhotoEntity)

    @Query("SELECT * FROM $PHOTO_TABLE_NAME ORDER BY updatedAt DESC")
    fun getPhotoLists(): PagingSource<Int, PhotoEntity>

    @Query("SELECT * FROM $PHOTO_TABLE_NAME WHERE id= :id LIMIT 1")
    fun getPhoto(id: Int):  PhotoEntity?
}