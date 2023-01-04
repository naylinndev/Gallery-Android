package dev.naylinn.gallery.ui.home.presentation

import androidx.paging.ExperimentalPagingApi
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.networking.NetApi
import dev.naylinn.gallery.repository.category.CategoryRepository
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.ui.base.BaseViewModel

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val categories = categoryRepository.getCategories()

}
