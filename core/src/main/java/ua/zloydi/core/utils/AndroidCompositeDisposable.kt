package ua.zloydi.core.utils

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

private class ClearDisposableObserver(
	private val compositeDisposable: CompositeDisposable
) : DefaultLifecycleObserver {
	override fun onDestroy(owner: LifecycleOwner) {
		compositeDisposable.clear()
	}
}

fun Fragment.compositeDisposable() = lazy(LazyThreadSafetyMode.NONE) {
	val disposable = CompositeDisposable()
	viewLifecycleOwner.lifecycle.addObserver(ClearDisposableObserver(disposable))
	disposable
}

fun ComponentActivity.compositeDisposable() = lazy(LazyThreadSafetyMode.NONE) {
	val disposable = CompositeDisposable()
	lifecycle.addObserver(ClearDisposableObserver(disposable))
	disposable
}

fun ViewModel.compositeDisposable() = lazy(LazyThreadSafetyMode.NONE) {
	val disposable = CompositeDisposable()
	addCloseable { disposable.clear() }
	disposable
}


