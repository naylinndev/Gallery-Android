package dev.naylinn.gallery.repository.detail

import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity

class DetailRepositoryImpl(private val appDatabase: AppDatabase) : DetailRepository {
    override suspend fun getPhoto(photoId: Int): PhotoEntity? {
      return appDatabase.photoDao().getPhoto(id = photoId)
    }

}