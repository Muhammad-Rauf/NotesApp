package com.example.notesapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.ui.fragments.HomeFragmentDirections


class NotesAdapters(var requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapters.NotesViewHolder>() {
    public fun newFiltering(newFilteredList: ArrayList<Notes>) {
        notesList=newFilteredList
        notifyDataSetChanged()

    }

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapters.NotesViewHolder {
       return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: NotesAdapters.NotesViewHolder, position: Int) {
        var data=notesList[position]
     holder.binding.textTitle.text=data.title
     holder.binding.textSubtitle.text=data.sub_title
     holder.binding.textDate.text=data.date
        when(data.priority){
            "1" ->{
     holder.binding.itemviewPriority.setBackgroundResource(R.drawable.green_dot)
     holder.binding.cardConstraintId.setBackgroundColor(Color.parseColor("#ff00e8"))
            }
            "2" ->{
                holder.binding.itemviewPriority.setBackgroundResource(R.drawable.yellow_dots)
                holder.binding.cardConstraintId.setBackgroundColor(Color.parseColor("#00b9fb"))

            }
            "3" ->{
                holder.binding.itemviewPriority.setBackgroundResource(R.drawable.red_dots)
                holder.binding.cardConstraintId.setBackgroundColor(Color.parseColor("#00ff5e"))

            }
        }
        if(data.isFavourite){
            holder.binding.rvFavouriteId.setImageResource(R.drawable.favourite_filled)

        }
        else
        {
            holder.binding.rvFavouriteId.setImageResource(R.drawable.favourite_empty)
        }

        holder.binding.root.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount()=
      notesList.size


}