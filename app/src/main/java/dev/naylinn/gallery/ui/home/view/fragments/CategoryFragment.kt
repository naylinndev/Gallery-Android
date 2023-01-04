package dev.naylinn.gallery.ui.home.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.FragmentCategoryBinding
import dev.naylinn.gallery.databinding.FragmentPhotoBinding
import dev.naylinn.gallery.ui.home.adapter.CategoryAdapter
import dev.naylinn.gallery.ui.home.adapter.PhotoAdapter
import dev.naylinn.gallery.ui.home.adapter.FooterLoadStateAdapter
import dev.naylinn.gallery.ui.home.presentation.CategoryViewModel
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {
    private val viewModel: CategoryViewModel by viewModel()
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeToRefresh()
    }


    private fun initAdapter() {
        binding.recyclerView.itemAnimator = null
        adapter = CategoryAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

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
            viewModel.categories.collectLatest {
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