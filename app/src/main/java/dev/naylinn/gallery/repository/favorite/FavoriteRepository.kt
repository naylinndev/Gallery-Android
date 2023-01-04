package dev.naylinn.gallery.repository.favorite

import androidx.paging.PagingData
import dev.naylinn.gallery.database.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getPhotos()  : Flow<PagingData<PhotoEntity>>
    suspend fun updatePhoto(photoEntity: PhotoEntity)
}