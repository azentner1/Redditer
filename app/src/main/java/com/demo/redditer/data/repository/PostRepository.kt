package com.demo.redditer.data.repository

import androidx.lifecycle.LiveData
import com.demo.redditer.data.model.ResponseType

interface PostRepository {
    fun fetchFrontPage(): LiveData<ResponseType>
}