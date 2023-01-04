package dev.naylinn.gallery.ui.home.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.favorite.FavoriteRepository
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val photos = favoriteRepository.getPhotos()

    suspend fun switchFavorite(photoEntity: PhotoEntity){
        photoEntity.isFavorite = !photoEntity.isFavorite
        favoriteRepository.updatePhoto(photoEntity = photoEntity)
    }
}
