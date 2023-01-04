package dev.naylinn.gallery.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ItemPhotoBinding
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener

class FavoriteAdapter(private val favoriteListener: FavoriteListener) :
    PagingDataAdapter<PhotoEntity, FavoriteViewHolder>(PHOTO_COMPARATOR) {
    private lateinit var binding: ItemPhotoBinding

    companion object {
        val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoEntity>() {
            override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean =
                oldItem.id == newItem.id

        }

    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                photoEntity = it,
                favoriteListener = favoriteListener,
                position = position
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteViewHolder(binding)
    }

}


class FavoriteViewHolder(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photoEntity: PhotoEntity, favoriteListener: FavoriteListener, position: Int) {
        binding.photo = photoEntity
        binding.icFavorite.setOnClickListener(View.OnClickListener {
            favoriteListener.onSwitchFavorite(photoEntity = photoEntity, position = position)
        })
    }
}