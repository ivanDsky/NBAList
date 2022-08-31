package ua.zloydi.list.network

import ua.zloydi.list.modules.PlayerListScope
import javax.inject.Inject

@PlayerListScope
internal class PlayersRepository @Inject constructor(private val service: IService){
	fun getPlayers(page: Int, size: Int) = service.getPlayers(page, size)
}