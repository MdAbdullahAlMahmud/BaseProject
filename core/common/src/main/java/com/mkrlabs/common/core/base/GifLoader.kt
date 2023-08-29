package com.mkrlabs.common.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mkrlabs.common.R
import com.mkrlabs.common.databinding.LayoutGifLoaderBinding


/**
 * Created by Abdullah on 7/9/2023.
 */

object GifLoader : DialogFragment() {

    lateinit var binding: LayoutGifLoaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = LayoutGifLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireActivity())
            .load(R.drawable.loader_gif_new)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivGifLoader)
    }
}