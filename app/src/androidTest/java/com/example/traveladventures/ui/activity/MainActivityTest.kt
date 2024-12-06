package com.example.traveladventures.ui.activity

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.traveladventures.R
import com.example.traveladventures.ui.fragment.MapFragment
import com.example.traveladventures.ui.fragment.PlanFragment
import com.example.traveladventures.ui.fragment.ProfileFragment
import com.example.traveladventures.ui.fragment.TripGridFragment
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        val intent = Intent().apply {
            setClass(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
            putExtra("userId", UUID.randomUUID().toString())
            putExtra("tripId", UUID.randomUUID().toString())
            putExtra("noteId", UUID.randomUUID().toString())
            putExtra("planId", UUID.randomUUID().toString())
        }
        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        scenario?.onActivity { activity ->
            activity.finish()
        }
        scenario?.close()
    }

    @Test
    fun testNavigateToTripGridFragment() {
        // Simulate the action of clicking the "Trips" navigation button
        scenario?.onActivity { activity ->
            // Manually trigger the fragment load logic
            activity.loadFragment(TripGridFragment(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)

            // Assert if fragment container is visible
            assertNotNull(fragmentContainer)  // Check if the fragment container is not null
            assert(fragmentContainer?.visibility == View.VISIBLE)  // Assert if it's visible
        }
    }

    @Test
    fun testNavigateToPlanFragment() {
        // Simulate the action of clicking the "Plans" navigation button
        scenario?.onActivity { activity ->
            // Manually trigger the fragment load logic
            activity.loadFragment(PlanFragment(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)

            // Assert if fragment container is visible
            assertNotNull(fragmentContainer)  // Check if the fragment container is not null
            assert(fragmentContainer?.visibility == View.VISIBLE)  // Assert if it's visible
        }
    }

    @Test
    fun testNavigateToMapFragment() {
        // Simulate the action of clicking the "Map" navigation button
        scenario?.onActivity { activity ->
            // Manually trigger the fragment load logic
            activity.loadFragment(MapFragment(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)

            // Assert if fragment container is visible
            assertNotNull(fragmentContainer)  // Check if the fragment container is not null
            assert(fragmentContainer?.visibility == View.VISIBLE)  // Assert if it's visible
        }
    }

    @Test
    fun testNavigateToProfileFragment() {
        // Simulate the action of clicking the "Profile" navigation button
        scenario?.onActivity { activity ->
            // Manually trigger the fragment load logic
            activity.loadFragment(ProfileFragment(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)

            // Assert if fragment container is visible
            assertNotNull(fragmentContainer)  // Check if the fragment container is not null
            assert(fragmentContainer?.visibility == View.VISIBLE)  // Assert if it's visible
        }
    }

    @Test
    fun testLoadAddPlanFragment() {
        // Simulate the action of clicking on the add plan button
        scenario?.onActivity { activity ->
            activity.loadAddPlan(UUID.randomUUID(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)
            assertNotNull(fragmentContainer)
            assert(fragmentContainer?.visibility == View.VISIBLE)
        }
    }

    @Test
    fun testLoadUpdateTripFragment() {
        // Simulate the action of clicking on the add plan button
        scenario?.onActivity { activity ->
            activity.loadUpdateTrip(UUID.randomUUID(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)
            assertNotNull(fragmentContainer)
            assert(fragmentContainer?.visibility == View.VISIBLE)
        }
    }

    @Test
    fun testLoadAddNoteFragment() {
        // Simulate the action of clicking on the add note button
        scenario?.onActivity { activity ->
            activity.loadAddNote(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
            val fragmentContainer = activity.findViewById<View>(R.id.fragment_container)
            assertNotNull(fragmentContainer)
            assert(fragmentContainer?.visibility == View.VISIBLE)
        }
    }
}