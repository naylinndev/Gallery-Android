package dev.naylinn.gallery.ui.detail.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.detail.DetailRepository
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.repository.photoByCategoryId.PhotoByCategoryIdRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class DetailViewModel(
    private val detailRepository: DetailRepository
) : BaseViewModel() {


    suspend fun getPhoto(photoId : Int) : PhotoEntity? {
        return detailRepository.getPhoto(photoId)
    }

}
