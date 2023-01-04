package dev.naylinn.gallery.ui.base

import androidx.lifecycle.ViewModel
import dev.naylinn.gallery.common.extensions.launch
import dev.naylinn.gallery.common.utils.Connectivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    val connectivity: Connectivity by inject()

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        if (connectivity.hasNetworkAccess()) {
            launch { action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        launch { action() }
    }
}