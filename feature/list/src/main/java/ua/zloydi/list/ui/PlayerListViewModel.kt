package ua.zloydi.list.ui

import androidx.lifecycle.ViewModel
import ua.zloydi.list.pagination.PlayersPagingSource
import javax.inject.Inject

internal class PlayerListViewModel @Inject constructor(
	private val pagingSource: PlayersPagingSource
) : ViewModel() {

}