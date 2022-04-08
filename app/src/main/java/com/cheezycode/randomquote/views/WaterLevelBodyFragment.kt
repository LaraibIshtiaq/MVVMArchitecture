package com.cheezycode.randomquote.views

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheezycode.randomquote.R
import com.cheezycode.randomquote.repository.NotificationRepository
import com.cheezycode.randomquote.utils.AlarmReceiver
import com.cheezycode.randomquote.viewmodels.DrinkWaterViewModel
import com.cheezycode.randomquote.viewmodels.DrinkWaterViewModelFactory
import kotlinx.android.synthetic.main.fragment_water_level_body.*
import kotlinx.coroutines.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [WaterLevelBodyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WaterLevelBodyFragment(repository: NotificationRepository) : Fragment(R.layout.fragment_water_level_body){

    val repository = repository
    lateinit var drinkWaterViewModel: DrinkWaterViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater!!.inflate(R.layout.fragment_water_level_body, container, false)

        var progress_bar = view.findViewById<ProgressBar>(R.id.water_level_indicator)
        progress_bar.progress = 30
        var progress_bar_percentage = view.findViewById<TextView>(R.id.PercentageText)
        progress_bar_percentage.text = "${progress_bar.progress}"+"%"

        lateinit var switchOk: Switch

        drinkWaterViewModel = ViewModelProvider(this, DrinkWaterViewModelFactory(repository, requireContext(), parentFragmentManager  )).get(DrinkWaterViewModel::class.java)

        drinkWaterViewModel.notification.observe(viewLifecycleOwner, Observer { notifications->
            notification_list.also{
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                val adapter = NotificationAdapter(notifications)
                it.adapter = adapter
                //For Setting time on clock
                adapter.setOnItemClickListener(object : NotificationAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int, ) {
                        CoroutineScope(Dispatchers.IO).launch {
                            drinkWaterViewModel.showTimePicker(position)
                            drinkWaterViewModel.picker.addOnPositiveButtonClickListener{
                                drinkWaterViewModel.setAlarm(position)
                                adapter.notifyItemChanged(position)
                                println(notifications)
                            }
                        }
                    }
                })

                //For Switch Enable Disable
                    adapter.setOnSwitchClickListener(object : NotificationAdapter.OnSwitchClickListener {
                        override fun onSwitchClick(position: Int) {
                            drinkWaterViewModel.switchAlarm(position)
                            adapter.notifyItemChanged(position)

                        }
                    })
            }
        })
        return view
    }
}