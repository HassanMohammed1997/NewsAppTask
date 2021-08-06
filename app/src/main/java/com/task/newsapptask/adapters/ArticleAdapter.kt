package com.task.newsapptask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.newsapptask.databinding.ListItemNewsBinding
import com.task.newsapptask.models.ArticlesItem
import com.task.newsapptask.ui.ArticleListFragmentDirections


class ArticleAdapter() :
    PagingDataAdapter<ArticlesItem, ArticleAdapter.NewViewHolder>(ArticleDiffUtil()) {
    class NewViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun getViewHolder(parent: ViewGroup): NewViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemNewsBinding.inflate(inflater, parent, false)
                return NewViewHolder(binding)
            }
        }

        fun bindTo(article: ArticlesItem?) {
            binding.article = article
            itemView.setOnClickListener {
                val action = article?.let { article ->
                    ArticleListFragmentDirections.actionNewsListFragmentToArticleDetailsFragment(
                        article
                    )
                }

                if (action != null) {
                    it.findNavController().navigate(action)
                }
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder =
        NewViewHolder.getViewHolder(parent)

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) =
        holder.bindTo(getItem(position))
}