package com.gio.cursoudemi.blogApp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.*
import com.gio.cursoudemi.blogApp.data.models.Post
import com.gio.cursoudemi.databinding.PostItemViewBinding

class HomeScreenAdapter(private val postList: List<Post>,private val onPostClickListener: OnPostClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var postClickListener : OnPostClickListener? = null

    init {
        postClickListener = onPostClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PostItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeScreenViewHolder(itemBinding,parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeScreenViewHolder-> holder.bin(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private inner class HomeScreenViewHolder
        (val binding: PostItemViewBinding,
         val context: Context):BaseViewHolder<Post>(binding.root){
        override fun bin(item: Post) {

            setupProfileInfo(item)
            addPostTimeStamp(item)
            setupPostImage(item)
            setupPostDescription(item)
            tintHeartIcon(item)
            setupLikeCount(item)


        }



        private fun setupProfileInfo(post : Post){
            Glide.with(context).load(post.poster?.profile_picture).centerCrop().into(binding.profilePicture)
            binding.profileName.text = post.poster?.userName
        }
        private fun addPostTimeStamp(post: Post){
            val createAt = post.created_at?.time?.div(1000L)?.let { TimeUtils.getTimeAgo(it.toInt()) }

            binding.postTimeStamp.text = createAt
        }
        private fun setupPostImage(post : Post){
            Glide.with(context).load(post.post_image).centerCrop().into(binding.postImage)
        }
        private fun setupPostDescription(post : Post){

            if(post.post_description.isEmpty()){
                binding.postDescription.hide()
            }else{
                binding.postDescription.text = post.post_description
            }
        }
        private fun tintHeartIcon(post : Post){
            if (post.liked){
                binding.likeButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_favorite_24))
                binding.likeButton.setColorFilter(ContextCompat.getColor(context,R.color.red_like))
            }else{
                binding.likeButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_favorite_border_24))
                binding.likeButton.setColorFilter(ContextCompat.getColor(context,R.color.black))
            }
        }
        private fun setupLikeCount(post : Post) {
            if (post.likes>0){
                binding.likeCount.hide()
                binding.likeCount.text = "${post.likes} likes"
            }else{
                binding.likeCount.show()
            }

        }
        private fun setLikeClickAction(post : Post){
            binding.likeButton.setOnClickListener {
                if (post.liked) post.apply { liked = true } else post.apply { liked = false }

                tintHeartIcon(post)

                postClickListener?.onLikedButtonClick(post,post.liked)
            }
        }
    }


}

interface OnPostClickListener{
    fun onLikedButtonClick(post: Post,liked : Boolean)
}