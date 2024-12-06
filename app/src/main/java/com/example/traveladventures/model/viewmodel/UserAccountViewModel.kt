package com.example.traveladventures.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.UserAccount
import java.security.MessageDigest
import java.util.UUID

class UserAccountViewModel(private val travelAdventuresRepository: TravelAdventuresRepository) : ViewModel() {
    //private val travelAdventuresRepository = TravelAdventuresRepository.get()
    /*
    lateinit var travelAdventuresRepository: TravelAdventuresRepository

    fun setUserAccountRepository(repository: TravelAdventuresRepository) {
        this.travelAdventuresRepository = repository
    }
     */

    suspend fun insert(userAccount: UserAccount) {
        travelAdventuresRepository.insert(userAccount)
    }

    suspend fun getUserAccount(email: String): UserAccount? {
        return travelAdventuresRepository.getUserAccount(email)
    }

    suspend fun registerUser(email: String, password: String, confirmPassword: String): String {
        var result: String = ""
        val userAccount = travelAdventuresRepository.getUserAccount(email)

        // User already exists
        if (userAccount != null) {
            result = "User already exists"
        }
        // User does not exist and passwords do not match
        else if (password != confirmPassword) {
            result = "Password does not match"
        }
        // User does not exist and passwords do match
        else if (password == confirmPassword) {
            // Check if password is secure
            if(checkPassword(password)){ // Is secure
                val hashedPassword: String = toHash(password)
                val newUser = UserAccount(UUID.randomUUID(), email = email, password = hashedPassword)
                travelAdventuresRepository.insert(newUser)
                result = "Successfully created user"
            }
            else { // Not secure
                result = "Passwords has to be min 8 characters, contains alphanumeric characters and contains least one special character '$','#','@', or '!''"
            }
            //val newUser = UserAccount(UUID.randomUUID(), email = email, password = password)
        }
        return result
    }

    private fun checkPassword(password:String):Boolean{
        val status:Boolean = false
        return if(password.length>=8){
            password.any { it.isLetterOrDigit() } &&  password.contains('$') || password.contains('#') || password.contains('@') || password.contains('!')
        }
        else{
            false
        }
    }

    private fun toHash(password: String):String{
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

class UserAccountViewModelFactory(private val travelAdventuresRepository: TravelAdventuresRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserAccountViewModel::class.java)) {
            return UserAccountViewModel(travelAdventuresRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}