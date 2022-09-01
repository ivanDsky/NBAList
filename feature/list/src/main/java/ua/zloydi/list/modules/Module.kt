package ua.zloydi.list.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Scope
import retrofit2.Retrofit
import retrofit2.create
import ua.zloydi.di.Dependencies
import ua.zloydi.di.ViewModelFactory
import ua.zloydi.di.ViewModelKey
import ua.zloydi.di.ViewModelMap
import ua.zloydi.list.network.IService
import ua.zloydi.list.ui.PlayerListFragment
import ua.zloydi.list.ui.PlayerListViewModel

@dagger.Module
internal class Module {
    @[PlayerListScope Provides]
    fun provideService(deps: ListFeatureDeps) = deps.retrofit.create<IService>()
}

@dagger.Module
internal interface ViewModelModule {
    @[Binds IntoMap ViewModelKey(PlayerListViewModel::class)]
    fun providePlayerListViewModel(viewModel: PlayerListViewModel): ViewModel
    companion object {
        @Provides
        fun provideViewModelFactory(viewModelMap: ViewModelMap) = ViewModelFactory(viewModelMap)
    }
}

@Scope
internal annotation class PlayerListScope

@PlayerListScope
@dagger.Component(modules = [Module::class, ViewModelModule::class])
internal interface Component {

    abstract fun inject(playerListFragment: PlayerListFragment)

    @dagger.Component.Factory
    interface Factory {
        fun create(@BindsInstance deps: ListFeatureDeps): Component
    }
}

interface ListFeatureDeps : Dependencies {
    val retrofit: Retrofit
}
