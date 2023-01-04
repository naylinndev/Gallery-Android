package dev.naylinn.gallery.repository.photoByCategoryId

import androidx.paging.PagingData
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoByCategoryIdRepository {
    fun getPhotos(categoryId : Int)  : Flow<PagingData<PhotoEntity>>
    suspend fun updatePhoto(photoEntity: PhotoEntity)
    suspend fun getCategory(categoryId : Int) : CategoryEntity?
}