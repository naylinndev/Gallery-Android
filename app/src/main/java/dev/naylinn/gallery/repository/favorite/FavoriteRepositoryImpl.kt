package dev.naylinn.gallery.repository.favorite

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.naylinn.gallery.common.PHOTO_PAGE_SIZE
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(private val appDatabase: AppDatabase, private val netApi: NetApi) :
    FavoriteRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(): Flow<PagingData<PhotoEntity>> = Pager(
        config = PagingConfig(PHOTO_PAGE_SIZE),
    ) {
        appDatabase.photoDao().getFavoritePhotoLists()
    }.flow

    override suspend fun updatePhoto(photoEntity: PhotoEntity) {
        appDatabase.photoDao().savePhoto(photoEntity = photoEntity)
    }
}