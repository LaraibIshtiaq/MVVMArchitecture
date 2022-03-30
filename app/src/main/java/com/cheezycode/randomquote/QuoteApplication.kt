package com.cheezycode.randomquote

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.cheezycode.randomquote.api.QuoteService
import com.cheezycode.randomquote.api.RetrofitHelper
import com.cheezycode.randomquote.db.QuoteDatabase
import com.cheezycode.randomquote.repository.QuoteRepository
import com.cheezycode.randomquote.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()

        ///------Any task that you need to run on background either the app is in foreground
        ///------or background state periodically should be implemented via WorkManager
        //setupWorker()
    }

    private fun setupWorker() {
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 5, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}