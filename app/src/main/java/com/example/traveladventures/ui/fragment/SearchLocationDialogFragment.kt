package com.example.traveladventures.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveladventures.api.PlaceItem
import com.example.traveladventures.api.PlacesRepository
import com.example.traveladventures.databinding.FragmentSearchLocationBinding
import com.example.traveladventures.ui.SearchListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchLocationDialogFragment: DialogFragment(){

    private lateinit var binding: FragmentSearchLocationBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchLocationBinding.inflate(inflater, container, false)

        binding.searchRecycleView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.locationText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                lifecycleScope.launch(Dispatchers.IO) {
                    val response = PlacesRepository().getLocation(text.toString())
                    Log.d("SearchLocation", response.toString())
                    lifecycleScope.launch(Dispatchers.Main) {
                        val adapter = com.example.traveladventures.ui.SearchListAdapter(response, this@SearchLocationDialogFragment)
                        binding.searchRecycleView.adapter = adapter
                    }

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }
}