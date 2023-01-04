package dev.naylinn.gallery.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.naylinn.gallery.databinding.ItemFooterLoadmoreBinding

class FooterLoadStateAdapter() :
    LoadStateAdapter<ItemFooterLoadMoreViewHolder>() {

    private lateinit var binding: ItemFooterLoadmoreBinding

    override fun onBindViewHolder(holder: ItemFooterLoadMoreViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ItemFooterLoadMoreViewHolder {
        binding = ItemFooterLoadmoreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemFooterLoadMoreViewHolder(binding)
    }


}


class ItemFooterLoadMoreViewHolder(
    private val binding: ItemFooterLoadmoreBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val progressBar = binding.progressBar
    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
    }
}