package com.arkever.indoorplayground.view.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.adapter.ActivityAdapter
import com.arkever.indoorplayground.model.ActivityItem
import com.arkever.indoorplayground.viewmodel.ActivityViewModel


class HomeFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.activityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        adapter = ActivityAdapter(emptyList()) { activity ->
            Toast.makeText(context, "Selected: ${activity.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        observeViewModel()


        viewModel.loadActivities()

        return view
    }

    private fun observeViewModel() {
        viewModel.activities.observe(viewLifecycleOwner) { activities ->
            adapter.updateList(activities)
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
