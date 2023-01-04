package dev.naylinn.gallery.ui.home.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class PhotoViewModel(
    private val photoRepository: PhotoRepository
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val photos = photoRepository.getPhotos()

    suspend fun switchFavorite(photoEntity: PhotoEntity){
        photoEntity.isFavorite = !photoEntity.isFavorite
        photoRepository.updatePhoto(photoEntity = photoEntity)
    }
}
