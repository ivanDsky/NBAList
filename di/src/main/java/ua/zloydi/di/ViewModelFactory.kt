package ua.zloydi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Provider
import kotlin.reflect.KClass

typealias ViewModelMap = @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>

@MapKey
annotation class ViewModelKey(val key: KClass<out ViewModel>)

class ViewModelFactory(
    private val viewModelMap: ViewModelMap
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return (viewModelMap[modelClass]?.get() as? T) ?: error("No viewModel found")
    }
}
