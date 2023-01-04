package dev.naylinn.gallery.ui.home.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.FragmentPhotoBinding
import dev.naylinn.gallery.ui.home.adapter.PhotoAdapter
import dev.naylinn.gallery.ui.home.adapter.FooterLoadStateAdapter
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : Fragment(), FavoriteListener {
    private val viewModel: PhotoViewModel by viewModel()
    private lateinit var binding: FragmentPhotoBinding
    private lateinit var adapter: PhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeToRefresh()
    }

    override fun onSwitchFavorite(photoEntity: PhotoEntity,position : Int) {
        adapter.notifyItemChanged(position)
        lifecycleScope.launch {
            viewModel.switchFavorite(photoEntity = photoEntity)
        }
    }

    private fun initAdapter() {
        binding.recyclerView.itemAnimator = null
        adapter = PhotoAdapter(favoriteListener = this)
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
            viewModel.photos.collectLatest {
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