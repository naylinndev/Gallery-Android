package dev.naylinn.gallery.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ItemPhotoBinding

class PhotoAdapter : PagingDataAdapter<PhotoEntity,PhotoViewHolder>(PHOTO_COMPARATOR) {
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
       holder.bind(getItem(position)!!)
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
    fun bind(photoEntity: PhotoEntity) {
        binding.photo = photoEntity
    }
}