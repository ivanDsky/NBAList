package ua.zloydi.list.ui

import android.content.Context
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ua.zloydi.coreui.viewmodel.ViewModelFactory
import ua.zloydi.di.findDependencies
import ua.zloydi.di.scopedComponent
import ua.zloydi.list.R
import ua.zloydi.list.databinding.FragmentPlayerListBinding
import ua.zloydi.list.modules.DaggerComponent
import javax.inject.Inject

class PlayerListFragment : Fragment(R.layout.fragment_player_list) {
	private val component by scopedComponent { DaggerComponent.factory().create(findDependencies()) }
	private val binding: FragmentPlayerListBinding by viewBinding()
	private val viewModel: PlayerListViewModel by viewModels { viewModelFactory }
	
	@Inject lateinit var viewModelFactory: ViewModelFactory
	
	override fun onAttach(context: Context) {
		super.onAttach(context)
		component.inject(this)
		println(component)
	}
}