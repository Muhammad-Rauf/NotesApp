package com.example.notesapp.ui.Activities

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.R
import com.example.notesapp.Utills.openDrawerMenu
import com.example.notesapp.ui.fragments.HomeFragment
import com.google.android.material.navigation.NavigationView
import java.lang.reflect.Array.get


class MainActivity : AppCompatActivity() {
   // lateinit var toggle: ActionBarDrawerToggle
   // lateinit var drawerLayout: DrawerLayout
   //  lateinit var binding: ActivityMainBinding
    lateinit var navContoler:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding=ActivityMainBinding.inflate(LayoutInflater.from(this))
      //  setContentView(binding.root)
        setContentView(R.layout.activity_main)
      //  drawerLayout=findViewById(R.id.mainlayout)
      //  val navigationView: NavigationView =findViewById(R.id.navigtionView)
      //  toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
      //  drawerLayout.addDrawerListener(toggle)
     //  toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //navHostFragment= supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navContoler=findNavController(R.id.fragmentContainerView)
      //  navContoler=navHostFragment.findNavController()
        setupActionBarWithNavController(navContoler)
/*
        openDrawerMenu={
            drawerLayout.openDrawer(GravityCompat.START)
        }*/


    }
/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }*/

    override fun onSupportNavigateUp(): Boolean {
        return navContoler.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onBackPressed() {


        if (navContoler.currentDestination?.id == R.id.homeFragment)
        {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("EXIT APP ! ")
                .setMessage("Are you sure you want to close this App?")
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which -> finishAffinity() })
                .setNegativeButton("No", null)
                .show()
        }
        else if (navContoler.currentDestination?.id == R.id.createNotesFragment)
        {
           // Toast.makeText(this, "createNotesFragment", Toast.LENGTH_SHORT).show()
            // Navigation.findNavController(this).navigate(R.id.action_editNotesFragment_to_homeFragment)
            Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.action_createNotesFragment_to_homeFragment)

        }
        else {

          //  Toast.makeText(this, "else Called", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.action_editNotesFragment_to_homeFragment)
        }



    }

}
