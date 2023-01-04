package dev.naylinn.gallery.repository.photo

import androidx.paging.PagingData
import dev.naylinn.gallery.database.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos()  : Flow<PagingData<PhotoEntity>>
}