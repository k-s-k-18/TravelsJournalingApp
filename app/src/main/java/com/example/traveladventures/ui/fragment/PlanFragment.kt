package com.example.traveladventures.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveladventures.R
import com.example.traveladventures.databinding.FragmentPlanBinding
import com.example.traveladventures.model.viewmodel.PlanViewModel
import com.example.traveladventures.ui.PlanListAdapter
import com.example.traveladventures.ui.activity.LoginActivity
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.launch
import java.util.UUID


class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val planViewModel: PlanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)

        binding.planRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = UUID.fromString(arguments?.getString("userId"))

        binding.apply {
            // Go to add plan page
            addButton.setOnClickListener {
                Log.i("PlanFragment", "Add button clicked")
                (activity as? MainActivity)?.loadAddPlan(null, userId)
            }
        }

        // Load plans from the ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                planViewModel.getPlans(userId).collect { plans ->
                    binding.planRecyclerView.adapter = PlanListAdapter(plans, this@PlanFragment)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
