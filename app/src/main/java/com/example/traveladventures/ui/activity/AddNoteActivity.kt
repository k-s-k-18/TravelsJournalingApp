package com.example.traveladventures.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.traveladventures.R
import com.example.traveladventures.databinding.ActivityAddNoteBinding
import com.example.traveladventures.ui.fragment.AddNoteFragment

class AddNoteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddNoteBinding.inflate(layoutInflater)


        val fragment = AddNoteFragment()
        val bundle = Bundle()
        bundle.putString("tripId", intent.getStringExtra("tripId") )
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.add_note_fragment, fragment)
            .commit()


        setContentView(binding.root)


    }
}