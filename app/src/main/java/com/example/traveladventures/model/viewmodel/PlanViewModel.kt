package com.example.traveladventures.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveladventures.model.Plan
import com.example.traveladventures.model.TravelAdventuresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class PlanViewModel: ViewModel() {
    private val travelAdventuresRepository = TravelAdventuresRepository.get()

    suspend fun insertPlan(plan: Plan) {
        travelAdventuresRepository.insertPlan(plan)
    }
    private val _plans: MutableStateFlow<List<Plan>> = MutableStateFlow(emptyList())
    val plans: StateFlow<List<Plan>>
        get() = _plans.asStateFlow()

    suspend fun getPlans(userId: UUID): Flow<List<Plan>> {
        return travelAdventuresRepository.getPlans(userId)
    }

    suspend fun getPlan(planId: UUID): Plan? {
        return travelAdventuresRepository.getPlan(planId)
    }

    suspend fun deletePlan(plan: Plan) {
        travelAdventuresRepository.deletePlan(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        travelAdventuresRepository.updatePlan(plan)
    }

    suspend fun getPlanByNameAndUserId(planName: String, userId: UUID): Plan? {
        return travelAdventuresRepository.getPlanByNameAndUserId(planName, userId)
    }
}