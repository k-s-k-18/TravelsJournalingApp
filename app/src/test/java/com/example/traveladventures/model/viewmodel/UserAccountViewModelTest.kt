package com.example.traveladventures.model.viewmodel

import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.UserAccount
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.UUID

class UserAccountViewModelTest {
    private lateinit var userAccountViewModel: UserAccountViewModel
    private val travelAdventuresRepository = mock(TravelAdventuresRepository::class.java)

    @Before
    fun setup() {
        userAccountViewModel = UserAccountViewModel(travelAdventuresRepository)
    }

    @Test
    fun `registerUser should already have existing user`() = runBlocking {
        val email = "james.iredell@examplepetstore.com"
        val password = "password123"
        val confirmPassword = "password123"
        val existingUser = UserAccount(UUID.randomUUID(), email, password)
        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(existingUser)

        val result = userAccountViewModel.registerUser(email, password, confirmPassword)

        assertEquals("User already exists", result)
    }

    @Test
    fun `registerUser should not have matching passwords`() = runBlocking {
        val email = "william.johnson@example-pet-store.com"
        val password = "password123"
        val confirmPassword = "differentPassword"

        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(null)

        val result = userAccountViewModel.registerUser(email, password, confirmPassword)

        assertEquals("Password does not match", result)
    }

    @Test
    fun `registerUser should create a new user account`() = runBlocking {
        val email = "william.henry.moody@my-own-personal-domain.com"
        val password = "password123"
        val confirmPassword = "password123"

        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(null)

        val result = userAccountViewModel.registerUser(email, password, confirmPassword)

        assertEquals("Successfully created user", result)
    }

    @Test
    fun `getUserAccount should return a user account`() = runBlocking {
        val email = "james.wilson@example-pet-store.com"
        val password = "password123"
        val userAccount = UserAccount(UUID.randomUUID(), email, password)

        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(userAccount)
        val result = userAccountViewModel.getUserAccount(email)
        assertEquals(userAccount, result)
    }

    @Test
    fun `getUserAccount should return null if user account does not exist`() = runBlocking {
        val email = "james.monroe@examplepetstore.com"
        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(null)
        val result = userAccountViewModel.getUserAccount(email)
        assertEquals(null, result)
    }

    @Test
    fun `insert should insert a user account`() = runBlocking {
        val email = "james.c.mcreynolds@example-pet-store.com"
        val password = "password123"
        val userAccount = UserAccount(UUID.randomUUID(), email, password)
        userAccountViewModel.insert(userAccount)
        whenever(travelAdventuresRepository.getUserAccount(email)).thenReturn(userAccount)
        val result = userAccountViewModel.getUserAccount(email)
        assertEquals(userAccount, result)
    }
}