package dev.naylinn.gallery.ui.photosByCategoryId.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.repository.photoByCategoryId.PhotoByCategoryIdRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class PhotoByCategoryIdViewModel(
    private val photoByCategoryIdRepository: PhotoByCategoryIdRepository
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getPhotosByCategoryId(categoryId: Int) =
        photoByCategoryIdRepository.getPhotos(categoryId = categoryId)

    suspend fun switchFavorite(photoEntity: PhotoEntity) {
        photoEntity.isFavorite = !photoEntity.isFavorite
        photoByCategoryIdRepository.updatePhoto(photoEntity = photoEntity)
    }

    suspend fun getCategory(categoryId: Int) : CategoryEntity? {
       return photoByCategoryIdRepository.getCategory(categoryId = categoryId)
    }
}
