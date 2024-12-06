package com.example.traveladventures.ui.fragment

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.databinding.FragmentAddNoteBinding
import com.example.traveladventures.model.Note
import com.example.traveladventures.model.viewmodel.NoteViewModel
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.launch
import java.util.UUID

class AddNoteFragment: Fragment() {
    private lateinit var binding: FragmentAddNoteBinding

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name:String = ""
        var formattedAddress: String
        var lat:Double = 0.0
        var long: Double = 0.0
        val mapId: UUID = UUID.randomUUID()
        val tripId = UUID.fromString(arguments?.getString("tripId"))
        val userId = UUID.fromString(arguments?.getString("userId"))

        val noteId = arguments?.getString("noteId")?.let { UUID.fromString(it) }
        // If noteId is not null, then we are editing an existing note and load note details
        noteId?.let { loadNoteDetails(it) }
        // Change the wording of the button based on whether we are adding or editing a note
        if (noteId == null) {
            binding.addNote.text = "Add"
            binding.deleteButton.text = ""
        } else {
            binding.addNote.text = "Save"
        }

        setFragmentResultListener("requestKey") { _, bundle ->
            name = bundle.getString("name").toString()
            formattedAddress = bundle.getString("formatted_address").toString()
            lat = bundle.getDouble("lat")
            long = bundle.getDouble("long")

            binding.noteLocation.text = name

            Log.d("AddNoteLog", "Name: $name, Address: $formattedAddress, Lat: $lat, Long: $long")
        }

        binding.noteLocation.setOnClickListener{
            val searchLocationDialog = SearchLocationDialogFragment()
            val connectivityManager = context?.getSystemService(ConnectivityManager::class.java)

            val activeNetwork = connectivityManager?.activeNetwork

            if(activeNetwork==null){
                //Toast.makeText(context,"No Network Available",Toast.LENGTH_SHORT).show()

                val alertDialogBuilder = AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle("No Network Connection")
                alertDialogBuilder.setMessage("No Network Connection. Please ensure that a network connection is available to add location")
                alertDialogBuilder.setPositiveButton("OK"){dialog,_->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
            else {
                searchLocationDialog.show(parentFragmentManager, "SearchLocationDialog")
            }
        }

        binding.backButton.setOnClickListener{
            (activity as? MainActivity)?.loadIndividualTrip(tripId, userId)
        }

        binding.deleteButton.setOnClickListener{
            if (noteId != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val noteTitle = binding.noteTitle.text.toString()
                    val noteLocation = binding.noteLocation.text.toString()
                    val noteText = binding.noteText.text.toString()
                    val note = Note(
                        noteId,
                        noteTitle,
                        noteText,
                        noteLocation,
                        mapId,
                        tripId,
                        userId,
                        lat,
                        long)
                    noteViewModel.deleteNote(note)
                    (activity as? MainActivity)?.loadIndividualTrip(tripId, userId)
                }
            }
        }

        binding.addNote.setOnClickListener{
            val noteTitle = binding.noteTitle.text.toString()
            val noteLocation = binding.noteLocation.text.toString()
            val noteText = binding.noteText.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                // Create new note
                if (noteId == null) {
                    val newNoteId = UUID.randomUUID()
                    val newNote = Note(
                        newNoteId,
                        noteTitle,
                        noteText,
                        noteLocation,
                        mapId,
                        tripId,
                        userId,
                        lat,
                        long)
                    noteViewModel.insertNote(newNote)
                }
                // Update existing note
                else {
                    val note = Note(
                        noteId,
                        noteTitle,
                        noteText,
                        noteLocation,
                        mapId,
                        tripId,
                        userId,
                        lat,
                        long)
                    noteViewModel.updateNote(note)
                }

                // Return back to list of notes
                (activity as? MainActivity)?.loadIndividualTrip(tripId, userId)
            }
        }
    }

    private fun loadNoteDetails(noteId: UUID) {
        viewLifecycleOwner.lifecycleScope.launch {
            val note = noteViewModel.getNote(noteId)
            note?.let {
                binding.noteTitle.setText(note.noteTitle)
                binding.noteLocation.setText(note.noteLocationName)
                binding.noteText.setText(note.noteText)
            }
        }
    }
}