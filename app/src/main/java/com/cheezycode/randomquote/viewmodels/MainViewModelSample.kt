package com.cheezycode.randomquote.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.randomquote.models.QuoteList
import com.cheezycode.randomquote.repository.QuoteRepositorySample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModelSample(private val repository: QuoteRepositorySample) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<QuoteList>
    get() = repository.quotes
}