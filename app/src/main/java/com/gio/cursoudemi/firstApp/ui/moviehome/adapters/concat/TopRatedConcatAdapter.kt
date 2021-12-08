package com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gio.cursoudemi.databinding.TopRatedMoviesBinding
import com.gio.cursoudemi.firstApp.core.BaseConcatHolder

class TopRatedConcatAdapter (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bin(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding : TopRatedMoviesBinding): BaseConcatHolder<MovieAdapter>(binding.root)
    {
        override fun bin(adapter: MovieAdapter) {
            binding.rvTopRatedMoviews.adapter = adapter
        }

    }
}