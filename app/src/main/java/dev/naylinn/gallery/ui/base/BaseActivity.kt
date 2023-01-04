package dev.naylinn.gallery.ui.base

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.naylinn.gallery.common.extensions.goBack
import dev.naylinn.gallery.common.extensions.snackbar
import dev.naylinn.gallery.routing.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : AppCompatActivity() {

  protected val appNavigator: AppNavigator by inject { parametersOf(this) }

  fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)

}