package dev.naylinn.gallery.di

import androidx.room.Room
import dev.naylinn.gallery.database.AppDatabase
import dev.naylinn.gallery.database.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

}
