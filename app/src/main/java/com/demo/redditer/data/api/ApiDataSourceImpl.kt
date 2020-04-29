package com.demo.redditer.data.api

import androidx.lifecycle.liveData
import com.demo.redditer.data.response.FrontPageResponse
import com.demo.redditer.data.model.ErrorMessage
import com.demo.redditer.data.model.ResponseType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ApiDataSourceImpl(private val apiService: ApiService) : ApiDataSource {

    override fun fetchFrontPage() = liveData {
        emit(suspendCoroutine<ResponseType> {
            apiService.fetchFrontPage().enqueue(object : Callback<FrontPageResponse> {
            override fun onResponse(call: Call<FrontPageResponse>, response: Response<FrontPageResponse>) {
                if (response.body() != null && response.isSuccessful) {
                    it.resume(response.body()!!.data)
                } else {
                    it.resume(ErrorMessage("An error has occurred."))
                }
            }

            override fun onFailure(call: Call<FrontPageResponse>, t: Throwable) {
                it.resume(ErrorMessage("An error has occurred."))
            }
        })})
    }

}