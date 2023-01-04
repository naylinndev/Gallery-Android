package dev.naylinn.gallery.di

import dev.naylinn.gallery.ui.home.presentation.CategoryViewModel
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PhotoViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}