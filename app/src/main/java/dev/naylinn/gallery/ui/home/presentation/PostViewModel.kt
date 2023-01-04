package dev.naylinn.gallery.ui.home.presentation

import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class PostViewModel(
    private val appDatabase: AppDatabase,
    private val netApi: NetApi,
    private val photoRepository: PhotoRepository
) : BaseViewModel() {

}
