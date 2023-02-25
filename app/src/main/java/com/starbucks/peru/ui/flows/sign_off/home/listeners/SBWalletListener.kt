package com.starbucks.peru.ui.flows.sign_off.home.listeners

interface SBWalletListener {
    fun showAddStarbucksCard()
    fun showSignIn()
    fun showRegister()
    fun showPayInStores(cardId: String = "")
    fun showEditStarbucksCard(cardId: String)
    fun showRechargeCard()
}