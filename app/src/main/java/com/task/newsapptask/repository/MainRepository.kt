package com.task.newsapptask.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.task.newsapptask.models.ArticlesItem
import com.task.newsapptask.network.RetrofitService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val service: RetrofitService
) {

    fun getAllArticles(
        query: String = "apple"
    ): Flow<PagingData<ArticlesItem>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ArticlesPagingSource(
                service, query
            )
        }
    ).flow

    companion object{
        const val NETWORK_PAGE_SIZE = 20
    }
}