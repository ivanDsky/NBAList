package ua.zloydi.list.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.zloydi.list.network.models.PlayerListResponse

internal interface IService {
	@GET("/api/v1//players")
	fun getPlayers(@Query("page") page: Int, @Query("per_page") size: Int): Single<PlayerListResponse>
}