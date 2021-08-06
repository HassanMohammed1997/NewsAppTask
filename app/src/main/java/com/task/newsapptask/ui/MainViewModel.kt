package com.task.newsapptask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.task.newsapptask.models.ArticlesItem
import com.task.newsapptask.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepository
) : ViewModel() {
    private val _articlesLiveData: MutableLiveData<PagingData<ArticlesItem>> = MutableLiveData()
    val articles: LiveData<PagingData<ArticlesItem>> = _articlesLiveData


    fun getArticles(query: String = "apple") = viewModelScope.launch {
        repo.getAllArticles(query).collect { pagingData ->
            _articlesLiveData.value = pagingData
        }
    }
}