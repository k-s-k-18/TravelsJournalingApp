package com.example.traveladventures.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveladventures.databinding.ActivityLoginBinding

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: ActivityLoginBinding

    // TODO: is the logic correct? delete comment if so
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}