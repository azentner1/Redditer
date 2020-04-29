package com.demo.redditer.data.model

data class PostListData(val children: List<PostData>) : ResponseType
data class PostData(val data: Post)