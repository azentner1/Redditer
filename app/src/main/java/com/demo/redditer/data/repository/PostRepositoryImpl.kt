package com.demo.redditer.data.repository

import androidx.lifecycle.liveData
import com.demo.redditer.data.api.ApiDataSource


class PostRepositoryImpl(private val apiDataSource: ApiDataSource) : PostRepository {

    override fun fetchFrontPage() = liveData {
        emitSource(apiDataSource.fetchFrontPage())
    }

}