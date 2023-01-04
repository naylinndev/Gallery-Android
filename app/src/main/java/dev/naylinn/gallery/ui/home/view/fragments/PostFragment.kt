package dev.naylinn.gallery.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.naylinn.gallery.databinding.FragmentPostBinding
import dev.naylinn.gallery.ui.home.presentation.PostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {
    private val viewModel: PostViewModel by viewModel()
    private lateinit var  binding : FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root

    }


}