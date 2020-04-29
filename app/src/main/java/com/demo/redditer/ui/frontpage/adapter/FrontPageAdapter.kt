package com.demo.redditer.ui.main.frontpage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.demo.redditer.R
import com.demo.redditer.data.model.Post
import com.demo.redditer.extensions.loadPostPlaceholder
import com.demo.redditer.extensions.loadPostPreview
import kotlinx.android.synthetic.main.post_list_item.view.*


class FrontPageAdapter(private var posts: List<Post> = listOf(), private val onPostSelected: (post: Post) -> Unit) : RecyclerView.Adapter<FrontPageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position], onPostSelected)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post, onPostSelected: (post: Post) -> Unit) {
            itemView.txtPostTitle.text = post.title
            itemView.txtPostAuthor.text = itemView.context.getString(R.string.prefixed_author, post.author)
            itemView.txtPostSubreddit.text = itemView.context.getString(R.string.prefixed_subreddit, post.subreddit)
            itemView.txtPostCommentCount.text = itemView.context.getString(R.string.number_of_comments, post.commentCount, post.upvotes)
            itemView.setOnClickListener {
                onPostSelected(post)
            }
            if(post.thumbnail.isNotEmpty() && post.thumbnail.startsWith("https")) {
                itemView.ivPostPreview.loadPostPreview(post.thumbnail)
            } else {
                itemView.ivPostPreview.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_image_placeholder))
            }
        }
    }
}