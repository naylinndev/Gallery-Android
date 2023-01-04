package dev.naylinn.gallery.di

import androidx.appcompat.app.AppCompatActivity
import dev.naylinn.gallery.common.coroutine.CoroutineContextProvider
import dev.naylinn.gallery.common.utils.Connectivity
import dev.naylinn.gallery.common.utils.ConnectivityImpl
import dev.naylinn.gallery.routing.AppNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
  single { CoroutineContextProvider() }
  single { (activity: AppCompatActivity) -> AppNavigator(activity) }
  factory<Connectivity> { ConnectivityImpl(androidContext()) }
}