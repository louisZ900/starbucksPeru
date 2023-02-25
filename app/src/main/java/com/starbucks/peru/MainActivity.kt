package com.starbucks.peru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.starbucks.peru.databinding.ActivityMainBinding
import com.starbucks.peru.ui.flows.shared.home.listeners.SBHomeListener
import com.starbucks.peru.ui.flows.sign_off.signin.SBSignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SBHomeListener {

    private lateinit var binding: ActivityMainBinding


    private lateinit var signInRegisterLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createLaunchers()






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

    private fun resetAppIfNeeded(resultCode: Int) {
        if (resultCode == RESULT_OK) {
            resetSections()
        }
    }

    private fun resetSections() {
        /*viewModel.refreshData()
        removeAllFragments()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.bottomNavigationMainMenu.selectedItemId = R.id.action_home
        }, 500)*/
    }

    private fun createLaunchers() {
        signInRegisterLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            //resetAppIfNeeded(result.resultCode)
        }
    }

    override fun showSignIn() {
        signInRegisterLauncher.launch(Intent(this, SBSignInActivity::class.java))
    }

    override fun showProfile() {
        TODO("Not yet implemented")
    }

    override fun showInbox() {
        TODO("Not yet implemented")
    }

    override fun showReadMore() {
        TODO("Not yet implemented")
    }

    override fun showShops() {
        TODO("Not yet implemented")
    }

    override fun showRewardDetail() {
        TODO("Not yet implemented")
    }

    override fun showRegister() {
        TODO("Not yet implemented")
    }

    override fun showRewardCoupons() {
        TODO("Not yet implemented")
    }

    override fun showAddCard() {
        TODO("Not yet implemented")
    }

    override fun showRechargeCard() {
        TODO("Not yet implemented")
    }

    override fun showOrdersHistory() {
        TODO("Not yet implemented")
    }

    override fun showMap() {
        TODO("Not yet implemented")
    }
}