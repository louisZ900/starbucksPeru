package com.starbucks.peru.core.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.starbucks.peru.R
import com.starbucks.peru.core.constants.*

@Synchronized fun AppCompatActivity.goToFragment(fragmentTag: String) {
    val wantedFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
    val currentFragment = supportFragmentManager.fragments
        .filter { SB_HOME_LIST_FRAGMENTS.any { name -> name == it.javaClass.name } }
        .firstOrNull {
            it.isHidden.not()
        }

    if(wantedFragment == null) {
        addSBFragment(fragmentTag)
        currentFragment?.let {
            supportFragmentManager.beginTransaction()
                .hide(it).commit()
        }
    } else {
        if (SB_TAGS_RELOAD_FRAGMENTS.any { it == fragmentTag }) {
            supportFragmentManager.beginTransaction().remove(wantedFragment).commit()
            addSBFragment(fragmentTag)

            currentFragment?.let {
                supportFragmentManager.beginTransaction()
                    .hide(it).commit()
            }
        } else {
            currentFragment?.let {
                supportFragmentManager.beginTransaction().hide(it).show(wantedFragment).commit()
            }
        }
    }
}

private fun AppCompatActivity.addSBFragment(fragmentId: String) {
    getSBFragment(fragmentId)?.let {
        supportFragmentManager.beginTransaction().add(
            R.id.navHostFragment,
            it,
            fragmentId
        ).commitNow()
    }
}

private fun getSBFragment(fragmentId: String): Fragment? {
    return when(fragmentId) {
        SB_TAG_FRAGMENT_HOME_CUSTOMER -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_HOME_CUSTOMER)
        SB_TAG_FRAGMENT_CARDS_CUSTOMER -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_CARDS_CUSTOMER)
        SB_TAG_FRAGMENT_SETTINGS_CUSTOMER -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_SETTINGS_CUSTOMER)
        SB_TAG_FRAGMENT_HOME_CLIENT -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_HOME_CLIENT)
        SB_TAG_FRAGMENT_CARDS_CLIENT -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_CARDS_CLIENT_V2)
        SB_TAG_FRAGMENT_SETTINGS_CLIENT -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_SETTINGS_CLIENT)
        SB_TAG_FRAGMENT_STORES_SHARED -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_STORES_SHARED)
        SB_TAG_FRAGMENT_ORDER_SHARED -> getFragmentClass(SB_CLASS_NAME_FRAGMENT_ORDER)
        else -> null
    }
}

private fun getFragmentClass(classFragmentName: String): Fragment? {
    return try {
        Class.forName(classFragmentName).newInstance() as? Fragment
    } catch (e: ClassNotFoundException) {
        null
    }
}