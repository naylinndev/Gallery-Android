package dev.naylinn.gallery.repository.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import kotlinx.coroutines.flow.Flow

class PhotoRepositoryImpl(private val appDatabase: AppDatabase, private val netApi: NetApi) :
    PhotoRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(): Flow<PagingData<PhotoEntity>> = Pager(
        config = PagingConfig(10),
        remoteMediator = PagePhotoRemoteMediator(appDatabase, netApi)
    ) {
        appDatabase.photoDao().getPhotoLists()
    }.flow
}