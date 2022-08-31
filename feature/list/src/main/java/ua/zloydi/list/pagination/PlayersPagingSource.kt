package ua.zloydi.list.pagination

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import ua.zloydi.list.modules.PlayerListScope
import ua.zloydi.list.network.PlayersRepository
import ua.zloydi.list.network.models.PlayerItem

@PlayerListScope
internal class PlayersPagingSource @Inject constructor(private val repository: PlayersRepository) :
    RxPagingSource<Int, PlayerItem>() {
    override fun getRefreshKey(state: PagingState<Int, PlayerItem>): Int? =
        state.anchorPosition?.div(state.config.pageSize)

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PlayerItem>> {
        val page = params.key ?: 1
        return repository.getPlayers(page, params.loadSize).subscribeOn(Schedulers.io())
            .map<LoadResult<Int, PlayerItem>> {
                LoadResult.Page(
                    data = it.data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = it.meta.nextPage
                )
            }.onErrorReturn { LoadResult.Error(it) }
    }
}
