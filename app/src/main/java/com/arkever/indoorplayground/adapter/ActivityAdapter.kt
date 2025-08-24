package com.arkever.indoorplayground.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.model.ActivityItem

class ActivityAdapter(
    private var activityList: List<ActivityItem>,
    private val onSelectClick: (ActivityItem) -> Unit
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.activity_header)
        val ivImage: ImageView = itemView.findViewById(R.id.activity_image)

        val btnSelect: Button = itemView.findViewById(R.id.activity_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }




    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activityList[position]
        holder.tvName.text = activity.name


        if (activity.photo.isNotEmpty()) {
            try {
                val decodedBytes = Base64.decode(activity.photo, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
                holder.ivImage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                holder.ivImage.setImageResource(R.drawable.progressbar_circle)
            }
        } else {
            holder.ivImage.setImageResource(R.drawable.progressbar_circle)
        }


        holder.btnSelect.setOnClickListener { onSelectClick(activity) }
    }

    override fun getItemCount(): Int = activityList.size



    fun updateList(newList: List<ActivityItem>) {
        activityList = newList
        notifyDataSetChanged()
    }
}
