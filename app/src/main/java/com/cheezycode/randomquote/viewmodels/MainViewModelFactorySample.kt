package com.cheezycode.randomquote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheezycode.randomquote.repository.QuoteRepositorySample

class MainViewModelFactorySample(private val repository: QuoteRepositorySample) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModelSample(repository) as T
    }
}