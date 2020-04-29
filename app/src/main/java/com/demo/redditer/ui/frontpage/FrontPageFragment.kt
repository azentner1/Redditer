package com.demo.redditer.ui.main.frontpage

import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.redditer.R
import com.demo.redditer.data.model.Post
import com.demo.redditer.data.model.ErrorMessage
import com.demo.redditer.data.model.PostData
import com.demo.redditer.data.model.PostListData
import com.demo.redditer.ui.main.frontpage.adapter.FrontPageAdapter
import com.demo.redditer.util.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_front_page.*
import org.koin.android.viewmodel.ext.android.viewModel

class FrontPageFragment : Fragment(R.layout.fragment_front_page) {

    private val frontPageFragmentViewModel: FrontPageFragmentViewModel by viewModel()

    private val frontPageAdapter by lazy {
        FrontPageAdapter(onPostSelected = {
            onPostSelected(it)
        })
    }

    override fun onResume() {
        super.onResume()
        initUi()
        fetchData()
    }

    private fun initUi() {
        rvPosts.layoutManager = LinearLayoutManager(requireContext())
        rvPosts.addItemDecoration(DividerItemDecoration(ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null)))
        rvPosts.adapter = frontPageAdapter
    }

    private fun fetchData() {
        frontPageFragmentViewModel.fetchFrontPage().observe(this@FrontPageFragment, Observer {
            if (it == null) {
                showError(getString(R.string.err_posts_fetch))
                return@Observer
            }
            when (it) {
                is PostListData -> {
                    loadData(it.children)
                }
                is ErrorMessage -> {
                    showError(it.errorMessage)
                }
            }
        })
    }

    private fun showError(message: String) {
        Snackbar.make(clPostsRoot, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun loadData(posts: List<PostData>) {
        frontPageAdapter.setData(frontPageFragmentViewModel.transformPostsData(posts))
    }

    // open post in WebView
    private fun onPostSelected(post: Post) {
        findNavController().navigate(FrontPageFragmentDirections.actionHomeFragmentToPostDetailsFragment(url = post.url, title = post.title))
    }

}