package com.example.taskmanager

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TaskManager)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_task,
                R.id.navigation_menu,
                R.id.navigation_quick,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        changeVisibilityNavElements(navController)
        supportActionBar?.hide()

        binding.buttonAdd.setOnClickListener {
            val builder = AlertDialog.Builder(binding.root.context, R.style.MyAlertDialogTheme)
            builder.setItems(R.array.creation_options,
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        0 -> navController.navigate(R.id.newTaskFragment)
                        1 -> navController.navigate(R.id.newQuickFragment)
                    }
                })
            builder.show()
        }

    }

    private fun changeVisibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_task,
                R.id.navigation_menu,
                R.id.navigation_quick,
                R.id.navigation_profile -> {
                    binding.navView.visibility = View.VISIBLE
                    binding.buttonAdd.visibility = View.VISIBLE
                }
                else -> {
                    binding.navView.visibility = View.GONE
                    binding.buttonAdd.visibility = View.GONE
                }
            }
        }
    }

}