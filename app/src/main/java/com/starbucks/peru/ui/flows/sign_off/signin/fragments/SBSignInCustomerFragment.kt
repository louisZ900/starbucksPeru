package com.starbucks.peru.ui.flows.sign_off.signin.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.starbucks.peru.R
import com.starbucks.peru.core.fragments.configureToolbar
import com.starbucks.peru.core.fragments.disableScrollParent
import com.starbucks.peru.core.fragments.setSbTitle
import com.starbucks.peru.core.utils.SBBlockScreenshots
import com.starbucks.peru.core.utils.afterTextChanged
import com.starbucks.peru.core.utils.animateOnPress
import com.starbucks.peru.core.utils.removeCopyPaste
import com.starbucks.peru.databinding.SbFragmentSignInBinding
import com.starbucks.peru.ui.flows.sign_off.signin.viewmodels.SBSignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBSignInCustomerFragment: Fragment() {
    private lateinit var binding : SbFragmentSignInBinding

    private val viewModel: SBSignInViewModel by viewModels()

    private var listener: SBSignInCustomerListener? = null

    private fun currentNavController(): NavController = findNavController()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SBSignInCustomerListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SbFragmentSignInBinding.inflate(inflater)
        SBBlockScreenshots.blockScreenshots(activity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setSbTitle(R.string.sb_customer_sing_in)
        disableScrollParent()

        binding.apply {
            editTextEmail.removeCopyPaste()
            editTextPw.removeCopyPaste()
            sbButtonFloatingSingIn.animateOnPress()
            sbButtonFloatingSingIn.setOnClickListener{
                /*viewModel.validateData(
                    editTextEmail.text.toString(),
                    editTextPw.text.toString()
                )*/
            }
            forgotPassButton.setOnClickListener {
                /*todo
                currentNavController().navigate(
                    SBSignInCustomerFragmentDirections.actionToForgotPss()
                )*/
            }

            val typeFace = ResourcesCompat.getFont(requireContext(), R.font.sb_font_regular)
            textInputLayoutEmail.typeface = typeFace
            textInputLayoutPw.typeface = typeFace

            editTextEmail.afterTextChanged {
                if (it.trim() != "") { textInputLayoutEmail.isErrorEnabled = false }
            }

            editTextPw.afterTextChanged {
                if (it.trim() != "") { textInputLayoutPw.isErrorEnabled = false }
            }
        }

        //bindViewModel()
        //viewModel.checkBiometricsIsActive(this)
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun bindViewModel() {
       /* viewModel.getShowProgress().observe(viewLifecycleOwner, Observer(this::showLoading))
        viewModel.getShowErrorMessage().observe(viewLifecycleOwner, Observer(this::showErrorAndSendTag))
        viewModel.getAction().observe(viewLifecycleOwner, Observer(this::handleAction))
        viewModel.errorMessages.observe(viewLifecycleOwner, Observer(this::shouldShowErrorMessages))*/
    }

    /*private fun handleAction(action: SBSignInAction) {
        when(action) {
            SBSignInAction.ReturnToHome -> listener?.showHome()
            SBSignInAction.ShowConfirmBiometrics -> showConfirmSaveCred()
        }
    }*/

    private fun showConfirmSaveCred() {
        /*showConfirmationMessage(
            getString(R.string.sb_biometrics_confirm_title),
            getString(R.string.sb_biometrics_confirm_description),
            getString(R.string.sb_biometrics_confirm_yes),
            getString(R.string.sb_biometrics_confirm_no),
            { viewModel.saveCredentials(true) },
            { viewModel.saveCredentials(false) }
        )*/
    }

    private fun shouldShowErrorMessages(messages: List<String>) {
        if (messages[0] != "") { binding.textInputLayoutEmail.error = messages[0] }
        if (messages[1] != "") { binding.textInputLayoutPw.error = messages[1] }
    }

    private fun showErrorAndSendTag(error: String) {
       // showError(error)
    }


    interface SBSignInCustomerListener{
        fun showHome()
    }
}