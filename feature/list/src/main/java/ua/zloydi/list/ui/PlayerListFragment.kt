package ua.zloydi.list.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ua.zloydi.core.utils.addTo
import ua.zloydi.core.utils.compositeDisposable
import ua.zloydi.di.ViewModelFactory
import ua.zloydi.di.findDependencies
import ua.zloydi.di.scopedComponent
import ua.zloydi.list.R
import ua.zloydi.list.databinding.FragmentPlayerListBinding
import ua.zloydi.list.modules.DaggerComponent
import javax.inject.Inject

class PlayerListFragment : Fragment(R.layout.fragment_player_list) {
	private val component by scopedComponent {
		DaggerComponent.factory().create(findDependencies())
	}
	private val binding: FragmentPlayerListBinding by viewBinding()
	private val viewModel: PlayerListViewModel by viewModels { viewModelFactory }
	private val compositeDisposable by compositeDisposable()
	private val pagingAdapter = PlayerListAdapter()
	private val concatAdapter = pagingAdapter
		.withLoadStateFooter(LoadingAdapter())
	
	@Inject
	lateinit var viewModelFactory: ViewModelFactory
	
	override fun onAttach(context: Context) {
		super.onAttach(context)
		component.inject(this)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.recycler.adapter = concatAdapter
		binding.recycler.layoutManager = LinearLayoutManager(requireContext())
		viewModel.pagingFlow.subscribe {
			pagingAdapter.submitData(lifecycle,it)
		}.addTo(compositeDisposable)
	}
	
}