package dev.naylinn.gallery.repository.photoByCategoryId

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.naylinn.gallery.common.PHOTO_PAGE_SIZE
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import kotlinx.coroutines.flow.Flow

class PhotoByCategoryIdRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val netApi: NetApi
) :
    PhotoByCategoryIdRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(categoryId: Int): Flow<PagingData<PhotoEntity>> = Pager(
        config = PagingConfig(PHOTO_PAGE_SIZE),
        remoteMediator = PagePhotoByCategoryIdRemoteMediator(categoryId, appDatabase, netApi)
    ) {
        appDatabase.photoDao().getPhotoListsByCategoryId()
    }.flow

    override suspend fun updatePhoto(photoEntity: PhotoEntity) {
        appDatabase.photoDao().savePhoto(photoEntity = photoEntity)
    }

    override suspend fun getCategory(categoryId: Int) : CategoryEntity? {
      return appDatabase.categoryDao().getCategory(id = categoryId)
    }
}