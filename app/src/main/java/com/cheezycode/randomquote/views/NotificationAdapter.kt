package com.cheezycode.randomquote.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView;
import com.cheezycode.randomquote.R
import com.cheezycode.randomquote.databinding.NotificationViewBinding
import com.cheezycode.randomquote.models.Notification

class NotificationAdapter(val notifications : List<Notification>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

        private lateinit var listener: OnItemClickListener
        private lateinit var switchListener: OnSwitchClickListener


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                NotificationViewHolder(
                        DataBindingUtil.inflate(
                                LayoutInflater.from(parent.context),
                                R.layout.notification_view,
                                parent,
                                false
                        )
                )

        override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
                holder.notificationViewBinding.notification = notifications[position]
        }

        override fun getItemCount() = notifications.size

        ///For Having on click listener on items of Recycler View
        interface OnItemClickListener{
                fun onItemClick(position: Int)
        }
        fun setOnItemClickListener(listener: OnItemClickListener){
                this.listener = listener
        }


        ///For Having on click listener on items of Recycler View
        interface OnSwitchClickListener{
                fun onSwitchClick(position: Int)
        }
        fun setOnSwitchClickListener(listener: OnSwitchClickListener){
                this.switchListener = listener
        }

        inner class NotificationViewHolder(val notificationViewBinding: NotificationViewBinding
        ) : RecyclerView.ViewHolder(notificationViewBinding.root){


                ///For Having on click listener on items of Recycler View
                init {
                        itemView.findViewById<TextView>(R.id.time_text).setOnClickListener {
                                listener.onItemClick(absoluteAdapterPosition)
                        }

                        itemView.findViewById<Switch>(R.id.switch_button).setOnClickListener{
                                switchListener.onSwitchClick(absoluteAdapterPosition)
                        }
                }
        }
}