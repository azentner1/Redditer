package com.demo.redditer.data.api

import androidx.lifecycle.LiveData
import com.demo.redditer.data.model.ResponseType

interface ApiDataSource {
    fun fetchFrontPage(): LiveData<ResponseType>
}