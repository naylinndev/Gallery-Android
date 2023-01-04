package dev.naylinn.gallery.ui.detail.view

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgument
import androidx.paging.LoadState
import dev.naylinn.gallery.database.model.PhotoEntity
import dev.naylinn.gallery.databinding.ActivityDetailBinding
import dev.naylinn.gallery.databinding.ActivityPhotoByCategoryBinding
import dev.naylinn.gallery.databinding.FragmentPhotoBinding
import dev.naylinn.gallery.ui.base.BaseActivity
import dev.naylinn.gallery.ui.detail.presentation.DetailViewModel
import dev.naylinn.gallery.ui.home.adapter.FooterLoadStateAdapter
import dev.naylinn.gallery.ui.home.adapter.PhotoAdapter
import dev.naylinn.gallery.ui.home.presentation.PhotoViewModel
import dev.naylinn.gallery.ui.home.view.activities.FavoriteListener
import dev.naylinn.gallery.ui.photosByCategoryId.adapter.PhotoByCategoryIdAdapter
import dev.naylinn.gallery.ui.photosByCategoryId.presentation.PhotoByCategoryIdViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

 class DetailActivity : BaseActivity() {
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    private var mPhotoId: Int = 0

    companion object {
        private const val KEY_PHOTO_ID = "photo_id"
        fun newInstance(context: Context, categoryId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_PHOTO_ID, categoryId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mPhotoId = intent.getIntExtra(KEY_PHOTO_ID, 0)

        lifecycleScope.launchWhenCreated {
            viewModel.getPhoto(mPhotoId).let {
                binding.photo = it
                title = "${it!!.title}'s Photo"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}