package ua.zloydi.list.network

import javax.inject.Inject
import ua.zloydi.list.modules.PlayerListScope

@PlayerListScope
internal class PlayersRepository @Inject constructor(private val service: IService) {
    fun getPlayers(page: Int, size: Int) = service.getPlayers(page, size)
}
