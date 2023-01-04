package dev.naylinn.gallery.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ItemPhotoBinding
import dev.naylinn.gallery.ui.detail.view.DetailActivity
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener
import dev.naylinn.gallery.ui.photosByCategoryId.view.PhotoByCategoryIdActivity

class PhotoAdapter(private val favoriteListener: FavoriteListener) :
    PagingDataAdapter<PhotoEntity, PhotoViewHolder>(PHOTO_COMPARATOR) {
    private lateinit var binding: ItemPhotoBinding

    companion object {
        val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoEntity>() {
            override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean =
                oldItem.id == newItem.id

        }

    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                photoEntity = it,
                favoriteListener = favoriteListener,
                position = position
            )
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = DetailActivity.newInstance(it.context,getItem(position)!!.id)
            it.context.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhotoViewHolder(binding)
    }

}


class PhotoViewHolder(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photoEntity: PhotoEntity, favoriteListener: FavoriteListener, position: Int) {
        binding.photo = photoEntity
        binding.icFavorite.setOnClickListener(View.OnClickListener {
            favoriteListener.onSwitchFavorite(photoEntity = photoEntity, position = position)
        })
    }
}