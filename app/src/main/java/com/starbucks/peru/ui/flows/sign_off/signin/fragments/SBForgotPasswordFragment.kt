package com.starbucks.peru.ui.flows.sign_off.signin.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.starbucks.peru.R
import com.starbucks.peru.core.fragments.disableScrollParent
import com.starbucks.peru.core.fragments.setSbTitle
import com.starbucks.peru.databinding.SbFragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBForgotPasswordFragment: Fragment() {
    private lateinit var binding : SbFragmentForgotPasswordBinding

    //lateinit var viewModel: SBForgotCustomerViewModel

    private fun currentNavController(): NavController = findNavController()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SbFragmentForgotPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSbTitle(R.string.sb_sign_in_forgot_pss_title)
        disableScrollParent()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
           /* editTextEmail.removeCopyPaste()
            sbButtonFloatingForgotPass.animateOnPress()
            sbButtonFloatingForgotPass.setOnClickListener {
                viewModel.validateEmail(editTextEmail.text.toString())
            }
            val typeFace = ResourcesCompat.getFont(requireContext(), R.font.sb_font_regular)
            textInputLayoutEmail.typeface = typeFace*/
        }
        bindViewModel()
    }

    private fun bindViewModel() {
      /*  viewModel.getShowProgress().observe(viewLifecycleOwner, Observer(this::showLoading))
        viewModel.getShowErrorMessage().observe(viewLifecycleOwner, Observer(this::showError))
        viewModel.getAction().observe(viewLifecycleOwner, Observer(this::handleAction))*/
    }

   /* private fun handleAction(action: SBForgotCustomerAction) {
        when (action) {
            is SBForgotCustomerAction.ShowMailError -> showEmailError(action.errorMessage)
            is SBForgotCustomerAction.ShowScreenDone -> showSuccessScreen()
        }
    }

    private fun showEmailError(error: String) {
        binding.textInputLayoutEmail.error = error
    }

    private fun showSuccessScreen() {
        currentNavController().navigate(
            SBForgotPasswordFragmentDirections.actionSbFragmentForgotPasswordToSbFragmentForgotPassConfirmation()
        )
    }*/
}