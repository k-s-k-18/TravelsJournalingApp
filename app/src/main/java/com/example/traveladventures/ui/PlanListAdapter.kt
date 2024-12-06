package com.example.traveladventures.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveladventures.databinding.ListItemPlanBinding
import com.example.traveladventures.model.Plan
import com.example.traveladventures.ui.activity.MainActivity
import com.example.traveladventures.ui.fragment.PlanFragment

class PlanHolder(val binding: ListItemPlanBinding, private val context: PlanFragment) : RecyclerView.ViewHolder(binding.root){
    fun bind(plan: Plan) {
        binding.listTitle.text = plan.planName

        // Go to the "add" plan page when a page is clicked from the list
        binding.root.setOnClickListener {
            val activity = context.activity as? MainActivity
            activity?.loadAddPlan(plan.planId, plan.userId)
        }
    }
}

class PlanListAdapter(private val plans: List<Plan>, val context: PlanFragment) : RecyclerView.Adapter<PlanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlanBinding.inflate(inflater, parent, false)
        return PlanHolder(binding, context)
    }

    override fun getItemCount() = plans.size

    override fun onBindViewHolder(holder: PlanHolder, position: Int) {
        val plan = plans[position]
        holder.bind(plan)
    }
}