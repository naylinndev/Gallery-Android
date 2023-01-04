package dev.naylinn.gallery.repository.photo

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.naylinn.gallery.common.TYPE_PHOTO
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.dao.PhotoDao
import dev.naylinn.gallery.database.dao.RemoteKeyDao
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.database.model.RemoteKeyEntity
import dev.naylinn.gallery.networking.APP_SECRET
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.networking.base.onFailure
import dev.naylinn.gallery.networking.base.onSuccess
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PagePhotoRemoteMediator(
    private val appDatabase: AppDatabase,
    private val netApi: NetApi,
) : RemoteMediator<Int, PhotoEntity>() {

    private val remoteKeyDao: RemoteKeyDao = appDatabase.remoteKeyDao()
    private val photoDao: PhotoDao = appDatabase.photoDao()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {

        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    val remoteKey = appDatabase.withTransaction {
                        remoteKeyDao.getRemoteKeyByType(type = TYPE_PHOTO)
                    }


                    if (!remoteKey.hasNextPage) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPage
                }
            }

            var isLastPage = false
            val data = netApi.getPhotos(appSecret = APP_SECRET, page = loadKey)
            data
                .onSuccess { response ->
                    val result = response.body()!!
                    val photoEntities = result.data.map { it.mapToRoomEntity() }
                    isLastPage = !result.hasMorePages
                    appDatabase.withTransaction {

                        if (loadType == LoadType.REFRESH) {
                            remoteKeyDao.deleteByType(type = TYPE_PHOTO)
                        }
                        remoteKeyDao.saveRemoteKey(
                            RemoteKeyEntity(
                                type = TYPE_PHOTO,
                                nextPage = if (result.hasMorePages) result.currentPage + 1 else result.currentPage,
                                hasNextPage = result.hasMorePages
                            )
                        )

                        photoDao.savePhotos(photoEntities = photoEntities)

                    }
                }
                .onFailure {
                }

            return MediatorResult.Success(endOfPaginationReached = isLastPage)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}