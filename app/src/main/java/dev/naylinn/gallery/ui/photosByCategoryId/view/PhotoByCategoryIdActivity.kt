package dev.naylinn.gallery.ui.photosByCategoryId.view

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgument
import androidx.paging.LoadState
import dev.naylinn.gallery.common.KEY_CATEGORY_ID
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ActivityPhotoByCategoryBinding
import dev.naylinn.gallery.databinding.FragmentPhotoBinding
import dev.naylinn.gallery.ui.base.BaseActivity
import dev.naylinn.gallery.ui.home.adapter.FooterLoadStateAdapter
import dev.naylinn.gallery.ui.home.adapter.PhotoAdapter
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener
import dev.naylinn.gallery.ui.photosByCategoryId.adapter.PhotoByCategoryIdAdapter
import dev.naylinn.gallery.ui.photosByCategoryId.presentation.PhotoByCategoryIdViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoByCategoryIdActivity : BaseActivity(), FavoriteListener {
    private val viewModel: PhotoByCategoryIdViewModel by viewModel()
    private lateinit var binding: ActivityPhotoByCategoryBinding
    private lateinit var adapter: PhotoByCategoryIdAdapter
    private var mCategoryId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoByCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mCategoryId = intent.getIntExtra(KEY_CATEGORY_ID, 0)

        initAdapter()
        initSwipeToRefresh()

    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }

    override fun onSwitchFavorite(photoEntity: PhotoEntity, position: Int) {
        Log.e("TAG", "onSwitchFavorite: ")
        adapter.notifyItemChanged(position)
        lifecycleScope.launch {
            viewModel.switchFavorite(photoEntity = photoEntity)
        }
    }

    private fun initAdapter() {
        lifecycleScope.launch {
         val categoryEntity = viewModel.getCategory(categoryId = mCategoryId)
            title = categoryEntity!!.categoryName
        }

        binding.recyclerView.itemAnimator = null
        adapter = PhotoByCategoryIdAdapter(favoriteListener = this)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = FooterLoadStateAdapter()
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.getPhotosByCategoryId(categoryId = mCategoryId).collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                // Scroll to top is synchronous with UI updates, even if remote load was triggered.
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }
}