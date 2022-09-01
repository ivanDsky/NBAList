package ua.zloydi.nbalist.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ua.zloydi.core.network.NetworkModule
import ua.zloydi.detail.modules.DetailFeatureDeps
import ua.zloydi.di.Dependencies
import ua.zloydi.di.DependencyKey
import ua.zloydi.list.modules.ListFeatureDeps
import ua.zloydi.nbalist.Application


@dagger.Module
interface ApplicationModule{
	@[Binds IntoMap DependencyKey(ListFeatureDeps::class)]
	fun bindListFeatureDeps(component: ApplicationComponent): Dependencies
	
	@[Binds IntoMap DependencyKey(DetailFeatureDeps::class)]
	fun bindDetailFeatureDeps(component: ApplicationComponent): Dependencies
}

@dagger.Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent : ListFeatureDeps, DetailFeatureDeps {
	fun inject(application: Application)
}
