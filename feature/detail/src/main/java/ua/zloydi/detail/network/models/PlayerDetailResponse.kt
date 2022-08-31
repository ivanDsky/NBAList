package ua.zloydi.detail.network.models

import com.google.gson.annotations.SerializedName

data class PlayerDetailResponse(
	@field:SerializedName("data") val data: List<PlayerItem>
)

data class PlayerItem(
	@field:SerializedName("fg3a") val fg3a: Double? = null,
	
	@field:SerializedName("blk") val blk: Double? = null,
	
	@field:SerializedName("fga") val fga: Double? = null,
	
	@field:SerializedName("ast") val ast: Double? = null,
	
	@field:SerializedName("dreb") val dreb: Double? = null,
	
	@field:SerializedName("fg_pct") val fgPct: Double? = null,
	
	@field:SerializedName("games_played") val gamesPlayed: Int? = null,
	
	@field:SerializedName("stl") val stl: Double? = null,
	
	@field:SerializedName("fgm") val fgm: Double? = null,
	
	@field:SerializedName("ft_pct") val ftPct: Double? = null,
	
	@field:SerializedName("reb") val reb: Double? = null,
	
	@field:SerializedName("pts") val pts: Double? = null,
	
	@field:SerializedName("fg3_pct") val fg3Pct: Double? = null,
	
	@field:SerializedName("fta") val fta: Double? = null,
	
	@field:SerializedName("player_id") val playerId: Int? = null,
	
	@field:SerializedName("min") val min: String? = null,
	
	@field:SerializedName("fg3m") val fg3m: Double? = null,
	
	@field:SerializedName("oreb") val oreb: Double? = null,
	
	@field:SerializedName("pf") val pf: Double? = null,
	
	@field:SerializedName("season") val season: Int? = null,
	
	@field:SerializedName("turnover") val turnover: Double? = null,
	
	@field:SerializedName("ftm") val ftm: Double? = null
)
