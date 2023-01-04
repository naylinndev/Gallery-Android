package dev.naylinn.gallery.di

import dev.naylinn.gallery.ui.detail.presentation.DetailViewModel
import dev.naylinn.gallery.ui.home.presentation.CategoryViewModel
import dev.naylinn.gallery.ui.home.presentation.FavoriteViewModel
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import dev.naylinn.gallery.ui.photosByCategoryId.presentation.PhotoByCategoryIdViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PhotoViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { PhotoByCategoryIdViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}