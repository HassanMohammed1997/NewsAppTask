package com.task.newsapptask.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ArticleLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ArticlesLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ArticlesLoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ArticlesLoadStateViewHolder =
        ArticlesLoadStateViewHolder.create(parent, retry)
}