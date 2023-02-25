package com.starbucks.peru.ui.flows.sign_off.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.starbucks.peru.R
import com.starbucks.peru.ui.flows.sign_off.signin.fragments.SBForgotPasswordConfirmFragment
import com.starbucks.peru.ui.flows.sign_off.signin.fragments.SBSignInCustomerFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SBSignInActivityy:  AppCompatActivity(),
    SBSignInCustomerFragment.SBSignInCustomerListener,
    SBForgotPasswordConfirmFragment.SBSignInPasswordConfirmationListener {

    private fun currentNavController(): NavController = findNavController(R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sb_activity_sign_in)
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        return when (currentNavController().currentDestination?.id) {
            R.id.sb_fragment_sing_in,
            R.id.sb_fragment_forgot_pass_confirmation-> {
                finish()
                false
            }
            R.id.sb_fragment_forgot_password -> {
                currentNavController().navigateUp()
            }
            else -> currentNavController().navigateUp()
        }
    }

    override fun showHome() {
        setResult(RESULT_OK)
        finish()
    }

    override fun onDonePressed() {
        finish()
    }

}