package com.cheezycode.randomquote

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.cheezycode.randomquote.api.QuoteServiceSample
import com.cheezycode.randomquote.api.RetrofitHelper
import com.cheezycode.randomquote.db.QuoteDatabaseSample
import com.cheezycode.randomquote.repository.NotificationRepository
import com.cheezycode.randomquote.repository.QuoteRepositorySample
import com.cheezycode.randomquote.worker.QuoteWorkerSample
import java.util.concurrent.TimeUnit

class ApplicationSample : Application() {

    lateinit var quoteRepository: QuoteRepositorySample
    lateinit var notificationRepository: NotificationRepository


    override fun onCreate() {
        super.onCreate()
        initialize()
        createNotificationChannel()

        ///------Any task that you need to run on background either the app is in foreground
        ///------or background state periodically should be implemented via WorkManager
        //setupWorker()
    }

    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "drinkwaterreminderhannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("drinkwater", name, importance)
            channel.description = description

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun setupWorker() {
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorkerSample::class.java, 5, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteServiceSample::class.java)
        val database = QuoteDatabaseSample.getDatabase(applicationContext)
        quoteRepository = QuoteRepositorySample(quoteService, database, applicationContext)

        notificationRepository = NotificationRepository(applicationContext)
    }
}