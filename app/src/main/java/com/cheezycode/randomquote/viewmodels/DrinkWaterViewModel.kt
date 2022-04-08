package com.cheezycode.randomquote.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Switch
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.randomquote.models.Notification
import com.cheezycode.randomquote.repository.NotificationRepository
import com.cheezycode.randomquote.utils.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DrinkWaterViewModel(private val repository: NotificationRepository,val context: Context,val fragmentManager: FragmentManager) : ViewModel(){

    lateinit var calendar: Calendar
    lateinit var alarmManager: AlarmManager
    lateinit var picker: MaterialTimePicker
    lateinit var pendingIntent: PendingIntent
    lateinit var switch_button : Switch

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getNotifications()
        }
    }

    val notification : LiveData<MutableList<Notification>>
        get() = repository.notification

    fun setAlarm(position: Int){
        var item = notification.value?.get(position)
            if(picker.hour>12){
                item!!.hour = picker.hour - 12
                item.minutes = picker.minute
            }
            else{
                item!!.hour = picker.hour
                item.minutes = picker.minute
            }
    }

    fun showTimePicker(position: Int) {
        var item = notification.value?.get(position)

        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(item!!.hour)
            .setMinute(item.minutes)
            .setTitleText("Set Alarm")
            .build()
        picker.show(fragmentManager, "drinkwater")
    }

    fun switchAlarm(position: Int) {
        var item = notification.value?.get(position)

        println(item!!.checked)
        if (!item.checked) {
            item.checked = true
            println("*******Alarm Turned On******")
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = item.hour
            calendar[Calendar.MINUTE] = item.minutes
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
            val PARAM_NAME = "position"
            val name = position
            alarmManager = (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager)!!
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(PARAM_NAME, name)
            pendingIntent = PendingIntent.getBroadcast(context, position, intent, 0)

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        } else {
            println("*******Alarm Turned Off******")
            item.checked = false
        }
    }
}