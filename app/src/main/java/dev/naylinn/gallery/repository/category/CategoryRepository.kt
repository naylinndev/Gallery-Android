package dev.naylinn.gallery.repository.category

import androidx.paging.PagingData
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories()  : Flow<PagingData<CategoryEntity>>

}