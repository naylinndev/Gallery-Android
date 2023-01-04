package dev.naylinn.gallery.database

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.naylinn.gallery.database.dao.CategoryDao
import dev.naylinn.gallery.database.dao.PhotoDao
import dev.naylinn.gallery.database.dao.RemoteKeyDao
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.database.model.RemoteKeyEntity

@Database(
    entities = [CategoryEntity::class, PhotoEntity::class,RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun photoDao(): PhotoDao
    abstract fun remoteKeyDao(): RemoteKeyDao


}