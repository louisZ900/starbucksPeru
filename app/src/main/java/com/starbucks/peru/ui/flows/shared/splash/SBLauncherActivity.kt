package com.starbucks.peru.ui.flows.shared.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.starbucks.peru.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBLauncherActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        showHome()
    }



    private fun showHome(/*typeEnter: SBTypeAccess*/) {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
        /*when (typeEnter) {
            SBTypeAccess.SB_ACCESS_NORMAL -> {
                startActivity(
                    Intent(this, SBHomeActivity::class.java)
                )
            }
            SBTypeAccess.SB_ACCESS_LOCK -> {
                startActivity(
                    Intent(this, SBLockPwActivity::class.java).putExtra(
                        SB_PW_TYPE_ACTION,
                        SB_PW_ENTER_PW_OPTION
                    )
                )
            }
        }*/
        finish()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}