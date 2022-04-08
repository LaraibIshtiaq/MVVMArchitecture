package com.cheezycode.randomquote.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheezycode.randomquote.ApplicationSample
import com.cheezycode.randomquote.R
import com.cheezycode.randomquote.viewmodels.DrinkWaterViewModel
import com.cheezycode.randomquote.viewmodels.DrinkWaterViewModelFactory
import com.cheezycode.randomquote.viewmodels.MainViewModelSample
import kotlinx.android.synthetic.main.fragment_water_level_body.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModelSample
    lateinit var drinkWaterViewModel: DrinkWaterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val repository = (application as ApplicationSample).notificationRepository

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_fragment_one, HomeFragment())
        fragmentTransaction.add(R.id.fl_fragment_two, HomeBodyFragment(repository)).commit()

//        val repository = (application as QuoteApplication).quoteRepository
//
//        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
//
//        mainViewModel.quotes.observe(this, Observer {
//            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
//        })
    }

}