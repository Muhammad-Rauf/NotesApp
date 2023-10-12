package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.util.*

class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    var priorityTick:String="1"
    private val viewModel: NotesViewModel by activityViewModels()
    var fav:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
       // binding.imgGreen.setImageResource(R.drawable.done_24)
      //  binding.imgGreen.setBackgroundResource(R.drawable.green_dot)

        binding.imgGreen.setOnClickListener {
//            Toast.makeText(requireContext(), "Green Image", Toast.LENGTH_SHORT).show()
            priorityTick="1"
            binding.imgGreen.setImageResource(R.drawable.done_24)

            binding.imgRed.setImageResource(0)
           binding.imgYellow.setImageResource(0)

            binding.imgGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgRed.setBackgroundResource(R.drawable.red_dots)

        }

        binding.imgYellow.setOnClickListener{
        //    Toast.makeText(requireContext(), "Yellow Image", Toast.LENGTH_SHORT).show()

            priorityTick="2"
            binding.imgYellow.setImageResource(R.drawable.done_24)

            binding.imgGreen.setImageResource(0)
            binding.imgRed.setImageResource(0)

            binding.imgGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgRed.setBackgroundResource(R.drawable.red_dots)


        }

        binding.imgRed.setOnClickListener {

            priorityTick="3"
            binding.imgRed.setImageResource(R.drawable.done_24)
            binding.imgGreen.setImageResource(0)
            binding.imgYellow.setImageResource(0)

            binding.imgGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgRed.setBackgroundResource(R.drawable.red_dots)
        }


        binding.btnEditsaveNotes.setOnClickListener {
            createNotes(it)
        }





        binding.favouriteId.setOnClickListener {

            fav = if (fav) {
                binding.favouriteId.setImageResource(R.drawable.favourite_empty)
                Toast.makeText(requireContext(), "Item marked as Unfavourite ", Toast.LENGTH_SHORT).show()
                false
            } else {
                binding.favouriteId.setImageResource(R.drawable.favourite_filled)
                Toast.makeText(requireContext(), "Item marked as Favourite ", Toast.LENGTH_SHORT).show()
                true
            }
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        var title=binding.edtxtTitle.text.toString()
        var subtitle=binding.edtxtSubtitle.text.toString()
        var notes=binding.edtxtNotes.text.toString()
        var d=Date()
        var currentDate:CharSequence=android.text.format.DateFormat.format("dd-MM-yyyy",d.time)
        Log.e("DATE", "createNotes: "+currentDate)
        val datenotes=Notes(null,title,subtitle,notes,currentDate.toString(),priorityTick,fav)
        viewModel.addNotes(datenotes)
        Toast.makeText(requireContext(), "Notes Insert Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}