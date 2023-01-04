package dev.naylinn.gallery.di

import dev.naylinn.gallery.repository.photo.PhotoRepository
import dev.naylinn.gallery.repository.photo.PhotoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<PhotoRepository> { PhotoRepositoryImpl(get(),get()) }

}