package com.cheezycode.randomquote.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheezycode.randomquote.R
import com.cheezycode.randomquote.models.Notification
import com.cheezycode.randomquote.repository.NotificationRepository

/**
 * A simple [Fragment] subclass.
 * Use the [HomeBodyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeBodyFragment(repository: NotificationRepository) : Fragment(R.layout.fragment_home_body), View.OnClickListener {

    val repository = repository

    val waterLevelUpperFragment = WaterLevelUpperFragment()
    val waterLevelBodyFragment = WaterLevelBodyFragment(repository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater!!.inflate(R.layout.fragment_home_body, container, false)
        val btn: Button = view.findViewById<Button>(R.id.get_started_button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.get_started_button -> {
                val fragmentTransaction: FragmentTransaction = getParentFragmentManager().beginTransaction()
                fragmentTransaction.replace(R.id.fl_fragment_one, waterLevelUpperFragment).addToBackStack(null)
                fragmentTransaction.replace(R.id.fl_fragment_two,waterLevelBodyFragment).addToBackStack(null).commit()
            }
            else -> {
                println("Cannot load the Water Level Fragment at the moment.")
            }
        }
    }
}