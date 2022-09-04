package ua.zloydi.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import ua.zloydi.list.network.models.PlayerItem
import javax.inject.Inject
import ua.zloydi.list.pagination.PlayersPagingSource
@OptIn(ExperimentalCoroutinesApi::class)
internal class PlayerListViewModel @Inject constructor(
	pagingSource: PlayersPagingSource
) : ViewModel() {
	private val pager = Pager(
		config = PagingConfig(20, 2, false, 40),
	) { pagingSource }
	
	val pagingFlow = pager.flowable.map {
		it.map { playerItem ->
			PlayerItemUI(
				fullName = "${playerItem.firstName} ${playerItem.lastName}",
				position = playerItem.position,
				team = playerItem.team?.fullName
			) {}
		}
	}.cachedIn(viewModelScope)
}
