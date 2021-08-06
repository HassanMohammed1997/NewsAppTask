package com.task.newsapptask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.task.newsapptask.adapters.ArticleAdapter
import com.task.newsapptask.adapters.ArticleLoadStateAdapter
import com.task.newsapptask.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val viewModel by viewModels<MainViewModel>()
    private val binding get() = _binding!!
    private val adapter by lazy { ArticleAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticles()
        binding.articleRecycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ArticleLoadStateAdapter { adapter.retry() },
            footer = ArticleLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            showOrHideShimmerLayoutAnimation(loadState.source.refresh is LoadState.Loading)
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.articles.observe(viewLifecycleOwner, { articles ->
            lifecycleScope.launch {
                adapter.submitData(articles)
            }

        })


    }

    private fun showOrHideShimmerLayoutAnimation(isLoading: Boolean) {
        if (isLoading)
            binding.articleRecycler.showShimmer()
        else
            binding.articleRecycler.hideShimmer()
    }

}