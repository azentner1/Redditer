package com.demo.redditer

import android.content.Context
import com.demo.redditer.koin.appModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import com.demo.redditer.data.api.ApiDataSourceImpl
import com.demo.redditer.data.api.ApiService

// verify that front page response is returned and is correctly mapped
class FrontPageResponseTest : KoinTest {

    val apiService: ApiService by inject()

    @Before
    fun setUp() {
        startKoin{
            androidContext(mock(Context::class.java))
            modules(appModules)}
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun verifyResponse() = runBlocking {
        val response = apiService.fetchFrontPage().execute()
        val frontPageResponse = response.body()
        if (frontPageResponse == null) {
            assert(false)
            return@runBlocking
        }
        val postList = frontPageResponse.data.children
        for (post in postList) {
            assert(post.data.title.isNotEmpty())
            assert(post.data.author.isNotEmpty())
            assert(post.data.upvotes > 0)
        }
    }
}