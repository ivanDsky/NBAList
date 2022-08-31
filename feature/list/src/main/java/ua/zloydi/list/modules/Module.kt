package ua.zloydi.list.modules

import dagger.BindsInstance
import retrofit2.Retrofit
import retrofit2.create
import ua.zloydi.di.Dependencies
import ua.zloydi.list.network.IService
import javax.inject.Scope

interface ListFeatureDeps: Dependencies {
	val retrofit: Retrofit
}

@dagger.Module
internal class Module {
	@PlayerListScope
	fun provideService(retrofit: Retrofit) = retrofit.create<IService>()
}

@Scope
internal annotation class PlayerListScope

@dagger.Component(modules = [Module::class])
internal interface Component{
	
	@dagger.Component.Factory
	interface Factory{
		fun create(@BindsInstance deps: ListFeatureDeps): Component
	}
}