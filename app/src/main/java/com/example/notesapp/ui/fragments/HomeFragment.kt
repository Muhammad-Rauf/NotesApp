package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.Utills.openDrawerMenu
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapters.NotesAdapters

class HomeFragment : Fragment() {
    private val viewModel: NotesViewModel by activityViewModels()
    lateinit var binding: FragmentHomeBinding
    var oldNotesList= arrayListOf<Notes>()
    lateinit var myadapter:NotesAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.allfilterId.setBackgroundResource(R.drawable.runtime_filter_shape)
        binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)


        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
         /*   for (i in notesList) {
                Log.e("NotesList", "onCreateView: ${i.title}")
            }*/

            oldNotesList=notesList as ArrayList<Notes>
            myadapter=NotesAdapters(requireContext(),notesList)
            binding.recyclerViewAllnotes.adapter=myadapter


            if (binding.recyclerViewAllnotes.adapter?.itemCount==0)
            {
                binding.noHistoryTxt.visibility=View.VISIBLE
            }
            else{
                binding.noHistoryTxt.visibility=View.GONE
            }


        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
//////////get All Notes click on Filter
        binding.allfilterId.setOnClickListener {
            binding.allfilterId.setBackgroundResource(R.drawable.runtime_filter_shape)
            binding.filterHigh.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterMedium.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterLow.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterFavourite.setBackgroundResource(R.drawable.filter_button_shape)
        //  openDrawerMenu?.invoke()
           viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotesList=notesList as ArrayList<Notes>
               // binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)
                myadapter=NotesAdapters(requireContext(),notesList)
                binding.recyclerViewAllnotes.adapter=myadapter
                if (binding.recyclerViewAllnotes.adapter?.itemCount==0) {
                    binding.noHistoryTxt.visibility=View.VISIBLE
                }
                else{
                    binding.noHistoryTxt.visibility=View.GONE
                }

            }

        }
//////////get High Notes

        binding.filterHigh.setOnClickListener {
            binding.filterHigh.setBackgroundResource(R.drawable.runtime_filter_shape)
            binding.filterMedium.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterLow.setBackgroundResource(R.drawable.filter_button_shape)
            binding.allfilterId.setBackgroundResource(R.drawable.filter_background)
            binding.filterFavourite.setBackgroundResource(R.drawable.filter_button_shape)

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotesList=notesList as ArrayList<Notes>
              //  binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)
                myadapter=NotesAdapters(requireContext(),notesList)
                binding.recyclerViewAllnotes.adapter=myadapter

                if (binding.recyclerViewAllnotes.adapter?.itemCount==0)
                {
                    binding.noHistoryTxt.visibility=View.VISIBLE
                }
                else{
                    binding.noHistoryTxt.visibility=View.GONE
                }

            }

        }
//////////get Medium Notes

        binding.filterMedium.setOnClickListener {
            binding.filterMedium.setBackgroundResource(R.drawable.runtime_filter_shape)
            binding.filterHigh.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterLow.setBackgroundResource(R.drawable.filter_button_shape)
            binding.allfilterId.setBackgroundResource(R.drawable.filter_background)
            binding.filterFavourite.setBackgroundResource(R.drawable.filter_button_shape)

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotesList=notesList as ArrayList<Notes>
              //  binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)
                myadapter=NotesAdapters(requireContext(),notesList)
                binding.recyclerViewAllnotes.adapter=myadapter

                if (binding.recyclerViewAllnotes.adapter?.itemCount==0)
                {
                    binding.noHistoryTxt.visibility=View.VISIBLE
                }
                else{
                    binding.noHistoryTxt.visibility=View.GONE
                }
            }
        }
//////////get Low Notes

        binding.filterLow.setOnClickListener {
            binding.filterLow.setBackgroundResource(R.drawable.runtime_filter_shape)
            binding.filterMedium.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterHigh.setBackgroundResource(R.drawable.filter_button_shape)
            binding.allfilterId.setBackgroundResource(R.drawable.filter_background)
            binding.filterFavourite.setBackgroundResource(R.drawable.filter_button_shape)

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotesList=notesList as ArrayList<Notes>
               // binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)
                myadapter=NotesAdapters(requireContext(),notesList)
                binding.recyclerViewAllnotes.adapter=myadapter

                if (binding.recyclerViewAllnotes.adapter?.itemCount==0)
                {
                    binding.noHistoryTxt.visibility=View.VISIBLE
                }
                else{
                    binding.noHistoryTxt.visibility=View.GONE
                }

            }
        }
        binding.filterFavourite.setOnClickListener {
            Toast.makeText(requireContext(), "You click favourite filter", Toast.LENGTH_SHORT).show()
            binding.filterLow.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterMedium.setBackgroundResource(R.drawable.filter_button_shape)
            binding.filterHigh.setBackgroundResource(R.drawable.filter_button_shape)
            binding.allfilterId.setBackgroundResource(R.drawable.filter_background)
            binding.filterFavourite.setBackgroundResource(R.drawable.runtime_filter_shape)

            //
            viewModel.getFavouriteNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotesList=notesList as ArrayList<Notes>
                //  binding.recyclerViewAllnotes.layoutManager=GridLayoutManager(requireContext(),1)
                myadapter=NotesAdapters(requireContext(),notesList)
                binding.recyclerViewAllnotes.adapter=myadapter

                if (binding.recyclerViewAllnotes.adapter?.itemCount==0)
                {
                    binding.noHistoryTxt.visibility=View.VISIBLE
                }
                else{
                    binding.noHistoryTxt.visibility=View.GONE
                }

            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        var item=menu.findItem(R.id.search_id)
        var searchView = item.actionView as SearchView
        searchView.queryHint="Enter Notes Name"
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchNotesfiltering(newText)
             return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchNotesfiltering(userText: String?) {
        Log.e("Search", "searchNotesfiltering:  $userText " )
        var newFilteredList= arrayListOf<Notes>()

        for (i in oldNotesList)
        {
           if ( i.title.contains(userText!!) || i.sub_title.contains(userText!!))
           {
               newFilteredList.add(i)

           }
        }
      myadapter.newFiltering(newFilteredList)
    }


}