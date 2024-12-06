package com.example.traveladventures.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.databinding.FragmentAddPlanBinding
import com.example.traveladventures.model.Plan
import com.example.traveladventures.model.viewmodel.PlanViewModel
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.launch
import java.util.UUID

class AddPlanFragment : Fragment() {
    private var _binding: FragmentAddPlanBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val planViewModel: PlanViewModel by viewModels()

    private var planId : UUID? = null
    //private var userId: UUID? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlanBinding.inflate(inflater, container, false)

        planId = arguments?.getString("planId")?.let { UUID.fromString(it) }
        // If planId is not null, load the plan details
        planId?.let { loadPlanDetails(it) }

        Log.i("AddPlanFragment", "onCreateView planId: $planId")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("AddPlanFragment", "onViewCreated() called")

        val userId = UUID.fromString(arguments?.getString("userId"))

        // If updating an existing plan, changing the wording of the title and button
        if (planId != null) {
            binding.addPlanTitle.text = "Plan"
            binding.addButton.text = "save"
        }
        // Disable delete button
        else {
            binding.deleteButton.visibility = View.GONE
        }

        binding.apply {
            // Add button
            addButton.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    Log.i("AddPlanFragment", "Add button clicked")
                    val planName = binding.planName.text.toString()
                    val planContent = binding.body.text.toString()

                    // Check for duplicate plan names
                    val existingPlan = planViewModel.getPlanByNameAndUserId(planName, userId)
                    if (existingPlan != null) {
                        // Duplicate found, show a confirmation dialog
                        Log.e("AddPlanFragment", "Plan with name $planName already exists.")

                        // Show confirmation dialog
                        AlertDialog.Builder(requireContext())
                            .setTitle("Duplicate Plan Name")
                            .setMessage("A plan with this name already exists. Are you sure you want to add a duplicate?")
                            .setPositiveButton("Yes") { dialog, _ ->
                                dialog.dismiss()
                                savePlan(planName, planContent) // Proceed to save duplicate
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss() // Do nothing
                            }
                            .show()
                    } else {
                        // No duplicate, save the plan
                        savePlan(planName, planContent)
                    }
                }
            }

            // Back button
            backButton.setOnClickListener {
                /*
                val intent = Intent(context, PlanActivity::class.java)
                startActivity(intent)
                 */
                //parentFragmentManager.popBackStack()
                Log.i("AddPlanFragment", "back button right before activity as?")
                (activity as? MainActivity)?.loadFragment(PlanFragment(), userId)
            }

            deleteButton.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val planName = binding.planName.text.toString()
                    val planContent = binding.body.text.toString()
                    val plan = Plan(planId!!, userId, planName, planContent)
                    planViewModel.deletePlan(plan)
                    (activity as? MainActivity)?.loadFragment(PlanFragment(), userId)
                }
            }
        }
    }

    private fun loadPlanDetails(planId: UUID) {
        viewLifecycleOwner.lifecycleScope.launch {
            val plan = planViewModel.getPlan(planId)
            plan?.let {
                binding.planName.setText(it.planName)
                binding.body.setText(it.content)
            }
        }
    }

    // Function to save the plan (new or updated)
    private fun savePlan(planName: String, planContent: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            val userId = UUID.fromString(arguments?.getString("userId"))

            if (planId == null) {
                Log.i("AddPlanFragment", "if statement - saving new plan")
                val newPlanId = UUID.randomUUID()
                val newPlan = Plan(newPlanId, userId, planName, planContent)
                planViewModel.insertPlan(newPlan)
            } else {
                Log.i("AddPlanFragment", "else statement - updating existing plan")
                val plan = Plan(planId!!, userId, planName, planContent)
                planViewModel.updatePlan(plan)
            }

            Log.i("AddPlanFragment", "Plan saved successfully.")
            (activity as? MainActivity)?.loadFragment(PlanFragment(), userId)
        }
    }
}