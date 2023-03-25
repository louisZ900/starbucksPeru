package com.starbucks.peru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.starbucks.peru.core.activities.goToFragment
import com.starbucks.peru.databinding.ActivityMainBinding
import com.starbucks.peru.ui.flows.shared.home.listeners.SBHomeListener
import com.starbucks.peru.ui.flows.shared.viewmodels.SBHomeViewModel
import com.starbucks.peru.ui.flows.sign_off.home.actions.SBHomeAction
import com.starbucks.peru.ui.flows.sign_off.signin.SBSignInActivity
import com.starbucks.peru.ui.flows.sign_off.signin.viewmodels.SBSignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SBHomeListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SBHomeViewModel by viewModels()

    private lateinit var signInRegisterLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createLaunchers()
        configureBottomNavigation()
        bindViewModel()

        val navView: BottomNavigationView = binding.navView
        binding.navView.inflateMenu(R.menu.bottom_nav_menu_sign_off);

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
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

    private fun bindViewModel() {
        viewModel.getAction().observe(this, Observer(this::handleAction))
        /*viewModelCard.getShowProgress().observe(this, Observer(this::showLoading))
        viewModelCard.getAction().observe(this, Observer(this::handleActionCard))*/
        //viewModel.getShowProgress().observe(this, Observer(this::showLoading))
        //viewModel.enableShake.observe(this, Observer(this::configureShakeListener))
    }

    private fun handleAction(action: SBHomeAction) {
        when(action) {
            is SBHomeAction.ShowSection -> showSection(action.section)
           /* SBHomeAction.UpdateHomeInfo -> updateWalletInfo()
            SBHomeAction.ReturnToHome -> goToHome()
            SBHomeAction.ShowErrorToken -> showTokenErrorMessage()
            SBHomeAction.ShowPayInStore -> showPayInStores()
            is SBHomeAction.ShowUpdate -> showUpdateInfo(action.option, action.description)
            is SBHomeAction.ShowNiubizScreen -> showNiubizScreen(action.niubizData)*/
            else -> {}
        }
    }

    private fun showSection(fragmentTag: String) {
        goToFragment(fragmentTag)
    }

    private fun configureBottomNavigation() {
        binding.navView.apply {
            //disableTooltipText()
            setOnItemSelectedListener {
                viewModel.getSection(it.itemId)
            }
        }
    }

    private fun resetAppIfNeeded(resultCode: Int) {
        if (resultCode == RESULT_OK) {
            resetSections()
        }
    }

    private fun resetSections() {
        viewModel.refreshData()
        /* removeAllFragments()
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