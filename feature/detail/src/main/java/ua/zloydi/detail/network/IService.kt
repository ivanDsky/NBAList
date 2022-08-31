package ua.zloydi.detail.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.zloydi.detail.network.models.PlayerDetailResponse

internal interface IService {
	@GET("/season_averages")
	fun getPlayerStats(@Query("player_ids[]") playerId: Int, @Query("season") season: Int): Single<PlayerDetailResponse>
}