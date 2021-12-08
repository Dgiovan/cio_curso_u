package com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gio.cursoudemi.databinding.PopularMoviesBinding
import com.gio.cursoudemi.firstApp.core.BaseConcatHolder

class PopularConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
    val itemBinding = PopularMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bin(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding : PopularMoviesBinding): BaseConcatHolder<MovieAdapter>(binding.root)
    {
        override fun bin(adapter: MovieAdapter) {
            binding.rvPopularMoviews.adapter =adapter
        }

    }
}