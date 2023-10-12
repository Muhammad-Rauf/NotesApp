package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


var lemdafunction: (() -> Unit)? = null
class EditNotesFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
val notesArgument by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priorityTick:String="1"
    var fav:Boolean = false
    private val viewModel: NotesViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(view).navigate(R.id.action_editNotesFragment_to_homeFragment)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=  FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        fav=notesArgument.data.isFavourite


        if (fav){
            binding.editFavouriteId.setImageResource(R.drawable.favourite_filled)
        }
        else{
            binding.editFavouriteId.setImageResource(R.drawable.favourite_empty)
        }
        //setBackButtonDispatcher()
        ///////////////
        binding.imgEidtGreen.setBackgroundResource(R.drawable.green_dot)
        binding.imgEditYellow.setBackgroundResource(R.drawable.yellow_dots)
        binding.imgEditRed.setBackgroundResource(R.drawable.red_dots)


        binding.edtxtEditTitle.setText(notesArgument.data.title)
        binding.edtxtEditSubtitle.setText(notesArgument.data.sub_title)
        binding.edtxtEditNotes.setText(notesArgument.data.notes)
/////before edit
        when(notesArgument.data.priority){
            "1" ->{
                priorityTick="1"
                binding.imgEidtGreen.setImageResource(R.drawable.done_24)
                binding.imgEditRed.setImageResource(0)
                binding.imgEditYellow.setImageResource(0)

                binding.imgEidtGreen.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                priorityTick="2"
                binding.imgEditYellow.setImageResource(R.drawable.done_24)
                binding.imgEidtGreen.setImageResource(0)
                binding.imgEditRed.setImageResource(0)

                binding.imgEditYellow.setBackgroundResource(R.drawable.yellow_dots)
            }
            "3" ->{
                priorityTick="3"
                binding.imgEditRed.setImageResource(R.drawable.done_24)
                binding.imgEidtGreen.setImageResource(0)
                binding.imgEditYellow.setImageResource(0)

                binding.imgEditRed.setBackgroundResource(R.drawable.red_dots)

            }
        }

  ///////////////////after edit click listerner

        binding.imgEidtGreen.setOnClickListener {
            priorityTick="1"
            binding.imgEidtGreen.setImageResource(R.drawable.done_24)
            binding.imgEditRed.setImageResource(0)
            binding.imgEditYellow.setImageResource(0)


            binding.imgEidtGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgEditYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgEditRed.setBackgroundResource(R.drawable.red_dots)

        }

        binding.imgEditYellow.setOnClickListener {
            priorityTick="2"
            binding.imgEditYellow.setImageResource(R.drawable.done_24)
            binding.imgEidtGreen.setImageResource(0)
            binding.imgEditRed.setImageResource(0)

            binding.imgEidtGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgEditYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgEditRed.setBackgroundResource(R.drawable.red_dots)

        }

        binding.imgEditRed.setOnClickListener {
            priorityTick="3"
            binding.imgEditRed.setImageResource(R.drawable.done_24)
            binding.imgEidtGreen.setImageResource(0)
            binding.imgEditYellow.setImageResource(0)

            binding.imgEidtGreen.setBackgroundResource(R.drawable.green_dot)
            binding.imgEditYellow.setBackgroundResource(R.drawable.yellow_dots)
            binding.imgEditRed.setBackgroundResource(R.drawable.red_dots)


        }

        binding.btnEditsaveNotes.setOnClickListener{
            updateNotes(it)
        }


        lemdafunction={
            Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)

        }

        ////////for edit favourite
        binding.editFavouriteId.setOnClickListener {
            fav = if (fav) {
                binding.editFavouriteId.setImageResource(R.drawable.favourite_empty)
                Toast.makeText(requireContext(), "Item marked as Unfavourite", Toast.LENGTH_SHORT).show()
                false
            } else {
                binding.editFavouriteId.setImageResource(R.drawable.favourite_filled)
                Toast.makeText(requireContext(), "Item marked as Favourite", Toast.LENGTH_SHORT).show()
                true
            }
        }

        return binding.root


    }


    private fun updateNotes(it: View?) {

        var title=binding.edtxtEditTitle.text.toString()
        var subtitle=binding.edtxtEditSubtitle.text.toString()
        var notes=binding.edtxtEditNotes.text.toString()
        var d= Date()
        var currentDate:CharSequence=android.text.format.DateFormat.format("dd-MM-yyyy",d.time)
        Log.e("DATE", "createNotes: "+currentDate)
        val datenotes= Notes(notesArgument.data.notes_id,title,subtitle,notes,currentDate.toString(),priorityTick,fav)
       viewModel.updateNotes(datenotes)
       Toast.makeText(requireContext(), "Notes Edit Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }
////set has option menu true
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_item_id)
        {
            var bottomsheet:BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.bottom_sheet_style)
            bottomsheet.setContentView(R.layout.dialog_delete)

            var textViewYes=bottomsheet.findViewById<TextView>(R.id.text_yes)
            var textViewNo=bottomsheet.findViewById<TextView>(R.id.text_no)

            textViewYes?.setOnClickListener {
                notesArgument.data.notes_id?.let { it1 -> viewModel.deleteNotes(it1) }
                bottomsheet.dismiss()
                lemdafunction?.invoke()
            }

            textViewNo?.setOnClickListener {
                bottomsheet.dismiss()

            }

            bottomsheet.show()


        }

        return super.onOptionsItemSelected(item)
    }

    private fun onBackPressed() {
        Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)
        // Work your magic! Show dialog etc.
    }




}