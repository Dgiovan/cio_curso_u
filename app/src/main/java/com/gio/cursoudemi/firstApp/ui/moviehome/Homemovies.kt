package com.gio.cursoudemi.firstApp.ui.moviehome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.gio.cursoudemi.R
import com.gio.cursoudemi.databinding.FragmentHomemoviesBinding
import com.gio.cursoudemi.firstApp.core.Resource
import com.gio.cursoudemi.firstApp.data.local.AppDatabase
import com.gio.cursoudemi.firstApp.data.local.LocalMovieDataSource
import com.gio.cursoudemi.firstApp.data.model.Movie
import com.gio.cursoudemi.firstApp.data.remote.RemoteMovieDataSource
import com.gio.cursoudemi.firstApp.presentation.MovieModelFactory
import com.gio.cursoudemi.firstApp.presentation.MovieViewModel
import com.gio.cursoudemi.firstApp.repository.MovieRepositoryImpl
import com.gio.cursoudemi.firstApp.repository.RetrofitClient
import com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat.MovieAdapter
import com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat.PopularConcatAdapter
import com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat.TopRatedConcatAdapter
import com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat.UpcomingConcatAdapter


class Homemovies : Fragment(R.layout.fragment_homemovies), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentHomemoviesBinding

    // lateinit representa una promesa
    //view model con inyeccion de dependencias
    private val viewModel by viewModels<MovieViewModel> {
        MovieModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice)
                , LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomemoviesBinding.bind(view)
        binding.progressLoading.visibility = View.GONE

        concatAdapter = ConcatAdapter()

        /*  viewModel.fetchUpComingMovies().observe(viewLifecycleOwner){result ->
              when(result){
                  is Resource.Loading ->{ Log.e("liveData","loading...")}
                  is Resource.Succes -> { Log.e("liveData", "${result.data.toString()}")}
                  is Resource.Failure ->{ Log.e("Error","${result.exepcion}")}
              }

          }*/
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressLoading.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    binding.progressLoading.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@Homemovies
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@Homemovies
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@Homemovies
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.e("fail","yes ${result.exepcion}")

                    binding.progressLoading.visibility = View.GONE
                }
            }
        }
    }

    override fun onMovieClick(movie: Movie) {
        TODO("Not yet implemented")
    }

    /*   override fun onMovieClick(movie: Movie) {
           val action = HomemoviesDirections.actionHomemoviesToDetailmovie(
               movie.poster_path,
               movie.backdrop_path?:"",
               movie.vote_average.toFloat(),
               movie.vote_count,
               movie.overview,
               movie.title,
               movie.original_language,
               movie.release_date
           )

           findNavController().navigate(action)
       }*/

}