package com.starbucks.peru.ui.flows.sign_off.signin.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.starbucks.peru.R
import com.starbucks.peru.core.fragments.configureToolbar
import com.starbucks.peru.core.fragments.disableScrollParent
import com.starbucks.peru.core.fragments.isVisibleNavigationIcon
import com.starbucks.peru.core.fragments.setSbTitle
import com.starbucks.peru.core.utils.animateOnPress
import com.starbucks.peru.databinding.SbFragmentForgotPassConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBForgotPasswordConfirmFragment: Fragment() {
    private lateinit var binding : SbFragmentForgotPassConfirmationBinding
    private var listener: SBSignInPasswordConfirmationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SBSignInPasswordConfirmationListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SbFragmentForgotPassConfirmationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSbTitle(R.string.sb_sign_in_forgot_pss_title)
        disableScrollParent()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonDone.animateOnPress()
        binding.buttonDone.setOnClickListener {
            listener?.onDonePressed()
        }
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
        isVisibleNavigationIcon(false)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface SBSignInPasswordConfirmationListener {
        fun onDonePressed()
    }
}