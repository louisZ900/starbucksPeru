package com.starbucks.peru.ui.flows.sign_off.home.actions

//import com.starbucks.peru.flows.sign_on.wallet.models.SBNiubizDataModel

sealed class SBHomeAction {
    data class ShowSection(val section: String) : SBHomeAction()
    object UpdateHomeInfo: SBHomeAction()
    object ReturnToHome: SBHomeAction()
    object ShowErrorToken: SBHomeAction()
    object ShowPayInStore: SBHomeAction()
    data class ShowUpdate(val option: String, val description: String): SBHomeAction()
    //data class ShowNiubizScreen(val niubizData: SBNiubizDataModel) : SBHomeAction()
}