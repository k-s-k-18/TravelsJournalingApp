package com.example.traveladventures.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.example.traveladventures.api.PlaceItem
import com.example.traveladventures.databinding.GridItemLocationBinding
import com.example.traveladventures.ui.fragment.SearchLocationDialogFragment


class SearchItemHolder(private val binding: GridItemLocationBinding, private val context: SearchLocationDialogFragment): RecyclerView.ViewHolder(binding.root){
    fun bind (searchItem: PlaceItem){
        binding.locationTextView.text = searchItem.formattedAddress

        binding.root.setOnClickListener {

            context.setFragmentResult(
                "requestKey",
                bundleOf(
                    "name" to searchItem.name,
                    "formatted_address" to searchItem.formattedAddress,
                    "lat" to searchItem.geometry.location.lat,
                    "long" to searchItem.geometry.location.long
                )
            )
            context.dismiss()
        }
    }
}

class SearchListAdapter(private var searchItems: List<PlaceItem>, val context: SearchLocationDialogFragment): RecyclerView.Adapter<SearchItemHolder>(){


    interface OnItemClickListener {
        fun onItemClicked(data: PlaceItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemLocationBinding.inflate(inflater, parent, false)
        return SearchItemHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: SearchItemHolder, position: Int) {
        val searchItem = searchItems[position]

        holder.bind(searchItem)


    }

}