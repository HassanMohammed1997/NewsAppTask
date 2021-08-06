package com.task.newsapptask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.task.newsapptask.databinding.FragmentArticleDetailsBinding
import com.task.newsapptask.listeners.ArticleDetailsListeners
import android.content.Intent
import android.net.Uri


class ArticleDetailsFragment : Fragment(), ArticleDetailsListeners {
    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ArticleDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.article = this.args.article

    }

    override fun onReadMoreClicked(view: View, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

}