package ua.zloydi.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ua.zloydi.coreui.DefaultDiff
import ua.zloydi.list.databinding.ItemLoadingBinding
import ua.zloydi.list.databinding.ItemPlayerBinding

data class PlayerItemUI(
	val fullName: String,
	val position: String? = null,
	val team: String? = null,
	val onClick: () -> Unit
)

class PlayerListAdapter : PagingDataAdapter<PlayerItemUI, PlayerListVH>(DefaultDiff()) {
	override fun onBindViewHolder(holder: PlayerListVH, position: Int) {
		holder.onBind(getItem(position))
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayerListVH(
		ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
	)
}

class PlayerListVH(private val binding: ItemPlayerBinding) : ViewHolder(binding.root) {
	fun onBind(item: PlayerItemUI?) = binding.apply {
		if (item == null) return@apply
		tvName.text = item.fullName
		tvPosition.text = item.position
		tvTeam.text = item.team
		root.setOnClickListener { item.onClick() }
	}
}

class LoadingAdapter : LoadStateAdapter<LoadingVH>(){
	override fun onBindViewHolder(holder: LoadingVH, loadState: LoadState) {}
	
	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadingVH(
		ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
	)
}

class LoadingVH(binding: ItemLoadingBinding) : ViewHolder(binding.root)