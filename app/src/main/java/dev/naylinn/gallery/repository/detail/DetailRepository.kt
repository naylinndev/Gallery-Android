package dev.naylinn.gallery.repository.detail

import dev.naylinn.gallery.database.model.PhotoEntity

interface DetailRepository {
    suspend fun getPhoto(photoId: Int) : PhotoEntity?
}