package com.starbucks.peru

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.starbucks.peru.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        val navView: BottomNavigationView = binding.navView
        binding.navView.inflateMenu(R.menu.bottom_nav_menu_sign_off);

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
     /*   val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_sign_on, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )*/
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_sign_off, R.id.navigation_cards_sing_off, R.id.navigation_orders, R.id.navigation_setting
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



       // val navHostFragment = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment)
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation)
        //graph.addArgument("argument", NavArgument)
        graph.setStartDestination(R.id.navigation_home_sign_off)
        //or
        //graph.setStartDestination(R.id.fragment2)
        navController.graph = graph
    }
}