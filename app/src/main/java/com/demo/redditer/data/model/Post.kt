package com.demo.redditer.data.model

import com.google.gson.annotations.SerializedName


data class Post(val id: String, val subreddit: String, val author: String, val title: String,
                @SerializedName("ups") val upvotes: Int, val url: String, @SerializedName("num_comments") val commentCount: Int,
                val preview: PostImageData?, val permalink: String, val selftext: String, val thumbnail: String)

data class PostImageData(val images: List<PostImages>?)

data class PostImages(val source: ImageSource, val resolutions: List<ImageSource>)

data class ImageSource(val url: String, val width: Int, val height: Int)