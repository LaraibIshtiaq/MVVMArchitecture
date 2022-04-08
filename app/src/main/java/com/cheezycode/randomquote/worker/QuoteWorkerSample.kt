package com.cheezycode.randomquote.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cheezycode.randomquote.ApplicationSample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorkerSample(private val context: Context, private val params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        val repository = (context as ApplicationSample).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getNotificationForQuotes()
        }
        return Result.success()
    }

}