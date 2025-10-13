package com.arkever.indoorplayground.view.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkever.indoorplayground.R
import com.arkever.indoorplayground.adapter.NotificationAdapter
import com.arkever.indoorplayground.viewmodel.NotificationViewModel

class NotificationFragment : Fragment() {

    private val viewModel: NotificationViewModel by viewModels()
    private lateinit var adapter: NotificationAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerView = view.findViewById(R.id.activityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.setHasFixedSize(true)

        adapter = NotificationAdapter(emptyList())
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe notifications
        viewModel.notification.observe(viewLifecycleOwner) { notifications ->
            adapter.updateList(notifications)
        }

        // Observe error messages
        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        // Call API with sample email (replace with logged-in user's email)
        viewModel.loadNotifications("hasanmaruf0055@gmail.com")
    }
}
