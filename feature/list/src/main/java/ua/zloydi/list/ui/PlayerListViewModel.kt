package ua.zloydi.list.ui

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import ua.zloydi.list.pagination.PlayersPagingSource

internal class PlayerListViewModel @Inject constructor(
    private val pagingSource: PlayersPagingSource
) : ViewModel()
