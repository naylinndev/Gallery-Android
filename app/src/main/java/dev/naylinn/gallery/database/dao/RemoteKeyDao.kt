package dev.naylinn.gallery.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.naylinn.gallery.database.CATEGORY_TABLE_NAME
import dev.naylinn.gallery.database.REMOTE_KEY_TABLE_NAME
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRemoteKey(remoteKeyEntity: RemoteKeyEntity)

    @Query("SELECT * FROM $REMOTE_KEY_TABLE_NAME WHERE type = :type")
    suspend fun getRemoteKeyByType(type : String): RemoteKeyEntity

    @Query("DELETE FROM $REMOTE_KEY_TABLE_NAME WHERE type = :type")
    suspend fun deleteByType(type: String)
}