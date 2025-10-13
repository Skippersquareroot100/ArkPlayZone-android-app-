package com.arkever.indoorplayground.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.model.NotificationItem

class NotificationAdapter(
    private var notificationList: List<NotificationItem>
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notification: TextView = itemView.findViewById(R.id.notificatonHolder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = notificationList[position]
        holder.notification.text = item.message
    }

    override fun getItemCount(): Int = notificationList.size

    fun updateList(newList: List<NotificationItem>) {
        notificationList = newList
        notifyDataSetChanged()
    }
}
