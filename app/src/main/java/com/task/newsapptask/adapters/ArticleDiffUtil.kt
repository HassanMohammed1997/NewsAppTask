package com.task.newsapptask.adapters

import androidx.recyclerview.widget.DiffUtil
import com.task.newsapptask.models.ArticlesItem

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticlesItem>() {
    override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
        return oldItem.source?.id == newItem.source?.id
    }

    override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
        return oldItem == newItem
    }
}