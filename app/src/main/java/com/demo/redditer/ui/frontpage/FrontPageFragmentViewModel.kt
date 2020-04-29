package com.demo.redditer.ui.main.frontpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.redditer.data.model.Post
import com.demo.redditer.data.model.PostData
import com.demo.redditer.data.repository.PostRepository

class FrontPageFragmentViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun fetchFrontPage() = liveData {
        emitSource(postRepository.fetchFrontPage())
    }

    // transform received list to List<Post> and sort descending by number of upvotes
    fun transformPostsData(posts: List<PostData>): List<Post> {
        return posts.map {
            it.data
        }.sortedByDescending { p ->
            p.upvotes
        }.toList()
    }
}