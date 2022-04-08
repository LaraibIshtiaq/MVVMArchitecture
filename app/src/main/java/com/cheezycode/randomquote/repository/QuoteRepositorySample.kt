package com.cheezycode.randomquote.repository

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.randomquote.api.QuoteServiceSample
import com.cheezycode.randomquote.db.QuoteDatabaseSample
import com.cheezycode.randomquote.models.QuoteList
import com.cheezycode.randomquote.utils.NetworkUtils

class QuoteRepositorySample(
    private val quoteService: QuoteServiceSample,
    private val quoteDatabase: QuoteDatabaseSample,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteService.getQuotes(page)
            if(result?.body() != null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }

    }

    suspend fun getNotificationForQuotes(){
        Log.d("WORKER CLASS DATA","HELOOOOO BUDDDS")
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, "HELLO BUDDY",Toast.LENGTH_SHORT).show()
            }
    }
}