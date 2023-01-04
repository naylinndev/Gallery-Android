package dev.naylinn.gallery.di

import dev.naylinn.gallery.repository.category.CategoryRepository
import dev.naylinn.gallery.repository.category.CategoryRepositoryImpl
import dev.naylinn.gallery.repository.favorite.FavoriteRepository
import dev.naylinn.gallery.repository.favorite.FavoriteRepositoryImpl
import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.repository.photo.PhotoRepositoryImpl
import dev.naylinn.gallery.repository.photoByCategoryId.PhotoByCategoryIdRepository
import dev.naylinn.gallery.repository.photoByCategoryId.PhotoByCategoryIdRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<PhotoRepository> { PhotoRepositoryImpl(get(), get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    factory<FavoriteRepository> { FavoriteRepositoryImpl(get(), get()) }
    factory<PhotoByCategoryIdRepository> { PhotoByCategoryIdRepositoryImpl(get(), get()) }

}