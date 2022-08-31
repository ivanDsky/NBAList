package ua.zloydi.detail.modules

import dagger.BindsInstance
import retrofit2.Retrofit
import retrofit2.create
import ua.zloydi.detail.network.IService
import ua.zloydi.di.Dependencies

interface DetailFeatureDeps: Dependencies{
	val retrofit: Retrofit
}

@dagger.Module
internal class Module {
	fun provideService(retrofit: Retrofit) = retrofit.create<IService>()
}

@dagger.Component(modules = [Module::class])
internal interface Component{
	
	@dagger.Component.Factory
	interface Factory{
		fun create(@BindsInstance deps: DetailFeatureDeps): Component
	}
}