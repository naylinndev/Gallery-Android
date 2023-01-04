package dev.naylinn.gallery.ui.home.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class PhotoViewModel(
    private val appDatabase: AppDatabase,
    private val netApi: NetApi,
    private val photoRepository: PhotoRepository
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val photos = photoRepository.getPhotos()
}
