package com.task.newsapptask.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.newsapptask.models.ArticlesItem
import com.task.newsapptask.network.RetrofitService
import okio.IOException
import retrofit2.HttpException

const val DEFAULT_INDEX_PAGE = 1

class ArticlesPagingSource constructor(
    private val service: RetrofitService,
    private val query: String
) : PagingSource<Int, ArticlesItem>() {
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val page = params.key ?: DEFAULT_INDEX_PAGE
        return try {
            val result =
                service.getAllArticle(query = query, page = page, pageSize = params.loadSize)
            LoadResult.Page(
                data = result.articles,
                prevKey = if (page == DEFAULT_INDEX_PAGE) null else page.dec(),
                nextKey = if (result.articles.isEmpty()) null else page.inc()
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}