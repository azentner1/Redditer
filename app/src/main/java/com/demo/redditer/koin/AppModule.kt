package com.demo.redditer.koin

import com.demo.redditer.data.api.ApiDataSource
import com.demo.redditer.data.api.ApiDataSourceImpl
import com.demo.redditer.data.api.ApiService
import com.demo.redditer.data.repository.PostRepository
import com.demo.redditer.data.repository.PostRepositoryImpl
import com.demo.redditer.ui.main.frontpage.FrontPageFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    // api
    single<ApiService> { ApiService() }

    // data sources
    single<ApiDataSource> { ApiDataSourceImpl(get()) }

    // repositories
    single<PostRepository> { PostRepositoryImpl(get()) }

    // view models
    viewModel { FrontPageFragmentViewModel(get()) }
}