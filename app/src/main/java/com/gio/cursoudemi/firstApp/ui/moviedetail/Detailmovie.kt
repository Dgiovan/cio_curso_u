package com.gio.cursoudemi.firstApp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gio.cursoudemi.R
import com.gio.cursoudemi.application.AppConstants
import com.gio.cursoudemi.databinding.FragmentDetailmovieBinding


class detailMovie : Fragment(R.layout.fragment_detailmovie) {

    private lateinit var binding : FragmentDetailmovieBinding
    private val args by navArgs<detailMovieArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailmovieBinding.bind(view)

        Glide.with(requireContext()).load(AppConstants.BASE_IMG+args.posterImageUrl).centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load(AppConstants.BASE_IMG+args.backgoundImageUrl).centerCrop().into(binding.imageBackground)

        binding.description.text = args.overview
        binding.title.text =args.title
        binding.txtLanguage.text = "language ${args.language}"
        binding.txtRaiting.text = "${args.voteAverage} (${args.voteCount} reviews)"
        binding.txtRelease.text = "release ${args.relaseData}"


    }


}