
package com.gio.cursoudemi.firstApp.ui.moviehome.adapters.concat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gio.cursoudemi.application.AppConstants
import com.gio.cursoudemi.databinding.MoviewItemBinding
import com.gio.cursoudemi.firstApp.core.BaseViewHolder
import com.gio.cursoudemi.firstApp.data.model.Movie

class MovieAdapter(private val movieList: List<Movie>,
                   private val itemClickListener : OnMovieClickListener
                   ) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBiding = MoviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MoviesViewHolder(itemBiding,parent.context)
        itemBiding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it !=  DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    private inner class MoviesViewHolder(
        val binding : MoviewItemBinding,
        val context :Context
        ) : BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {

            Glide.with(context).load(AppConstants.BASE_IMG+item.poster_path).centerCrop().into(binding.imgMovie)
        }
    }
}