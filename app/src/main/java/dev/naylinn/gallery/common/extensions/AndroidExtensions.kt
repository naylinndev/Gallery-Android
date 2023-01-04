package dev.naylinn.gallery.common.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.naylinn.gallery.common.coroutine.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

val Number.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

inline fun <T> LiveData<T>.subscribe(
    owner: LifecycleOwner,
    crossinline onDataReceived: (T) -> Unit
) =
    observe(owner, Observer { onDataReceived(it) })

fun snackbar(@StringRes message: Int, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun snackbar(message: String, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}


inline fun ViewModel.launch(
    coroutineContext: CoroutineContext = CoroutineContextProvider().main,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) { block() }
}


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
    setOnClickListener { onClick() }
}
