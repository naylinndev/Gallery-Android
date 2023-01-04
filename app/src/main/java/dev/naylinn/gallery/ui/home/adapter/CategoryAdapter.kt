package dev.naylinn.gallery.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.naylinn.gallery.database.model.CategoryEntity
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ItemCategoryBinding
import dev.naylinn.gallery.databinding.ItemPhotoBinding
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener

class CategoryAdapter() :
    PagingDataAdapter<CategoryEntity, CategoryViewHolder>(CATEGORY_COMPARATOR) {
    private lateinit var binding: ItemCategoryBinding

    companion object {
        val CATEGORY_COMPARATOR = object : DiffUtil.ItemCallback<CategoryEntity>() {
            override fun areContentsTheSame(
                oldItem: CategoryEntity,
                newItem: CategoryEntity
            ): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: CategoryEntity,
                newItem: CategoryEntity
            ): Boolean =
                oldItem.id == newItem.id

        }

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                categoryEntity = it,
                position = position
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

}


class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(categoryEntity: CategoryEntity, position: Int) {
        binding.category = categoryEntity
    }
}