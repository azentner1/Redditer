package com.demo.redditer.ui.details

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.demo.redditer.R
import kotlinx.android.synthetic.main.fragment_post_details.*
import java.lang.IllegalStateException

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {

    override fun onStart() {
        super.onStart()
        val url = arguments?.let {
            PostDetailsFragmentArgs.fromBundle(it).url
        } ?: throw IllegalStateException("Ooooops, seems you forgot to pass the posts URL here!")

        arguments?.let {
            PostDetailsFragmentArgs.fromBundle(it).title
        } ?: throw IllegalStateException("You'll need a title also!")

        initData(url)
    }

    @SuppressLint("SetJavaScriptEnabled") // We'll ignore XSS warnings for now :)
    private fun initData(url: String) {
        webViewPostDetails.webViewClient = WebViewClient()
        webViewPostDetails.settings.javaScriptEnabled = true
        webViewPostDetails.loadUrl(url)
    }

}