package com.demo.redditer.ui.main.splash

import android.os.Bundle
import android.os.Handler
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.demo.redditer.R
import kotlinx.android.synthetic.main.activity_main.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }, SHOW_SPLASH_TIME)
    }

    companion object {
        private const val SHOW_SPLASH_TIME = 2000L
    }

}