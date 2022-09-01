package ua.zloydi.nbalist

import ua.zloydi.di.HasDependencies
import ua.zloydi.di.MapDependencies
import ua.zloydi.nbalist.modules.DaggerApplicationComponent
import javax.inject.Inject

class Application : android.app.Application(), HasDependencies {
	@Inject
	override lateinit var dependencies: MapDependencies
	
	override fun onCreate() {
		super.onCreate()
		DaggerApplicationComponent.create()
			.inject(this)
	}
	
}