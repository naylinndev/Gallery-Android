package dev.naylinn.gallery.repository.category

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.naylinn.gallery.common.CATEGORY_PAGE_SIZE
import dev.naylinn.gallery.common.PHOTO_PAGE_SIZE
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.photo.PagePhotoRemoteMediator
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val appDatabase: AppDatabase, private val netApi: NetApi) : CategoryRepository{

    @OptIn(ExperimentalPagingApi::class)
    override fun getCategories(): Flow<PagingData<CategoryEntity>> = Pager(
        config = PagingConfig(CATEGORY_PAGE_SIZE),
        remoteMediator = PageCategoryRemoteMediator(appDatabase, netApi)
    ) {
        appDatabase.categoryDao().getCategoryLists()
    }.flow


}