package com.cheezycode.randomquote.viewmodels

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheezycode.randomquote.repository.NotificationRepository

class DrinkWaterViewModelFactory (
    private val repository: NotificationRepository,
    val context: Context, val fragmentManager: FragmentManager
    ) : ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DrinkWaterViewModel(repository, context, fragmentManager) as T
        }

}