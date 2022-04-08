package com.cheezycode.randomquote.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.randomquote.models.Notification

class NotificationRepository( private val applicationContext: Context ){

    private val _notificationLiveData = MutableLiveData<MutableList<Notification>>()
    val notification: LiveData<MutableList<Notification>>
        get() = _notificationLiveData

    suspend fun getNotifications(){
        var notifications : MutableList<Notification> = mutableListOf(
            Notification(hour = 3,2, quantity = 210, checked = true, timeMode="PM"),
            Notification(hour = 4,12,quantity = 250, checked = true, timeMode="AM"),
            Notification(hour = 6,2, quantity = 500, checked = false, timeMode="PM"),
            Notification(hour = 4,50, quantity = 250, checked = true, timeMode="AM"),
            Notification(hour = 12,22, quantity = 500, checked = false, timeMode="PM")
        )
        _notificationLiveData.postValue(notifications)
    }
}