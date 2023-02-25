package com.starbucks.peru.ui.flows.sign_off.wallet.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.starbucks.peru.R
import com.starbucks.peru.core.fragments.setSbTitle
import com.starbucks.peru.core.utils.animateOnPress
import com.starbucks.peru.databinding.SbFragmentWalletCustomerBinding
import com.starbucks.peru.ui.flows.sign_off.home.listeners.SBWalletListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBWalletCustomerFragment : Fragment() {
    private lateinit var binding : SbFragmentWalletCustomerBinding

    private var listener: SBWalletListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SBWalletListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SbFragmentWalletCustomerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setSbTitle(R.string.sb_customer_wallet_title)

        binding.buttonSignInHome.animateOnPress()
        binding.buttonSignInHome.setOnClickListener {
            listener?.showSignIn()
        }

        binding.buttonJoinHome.animateOnPress()
        binding.buttonJoinHome.setOnClickListener {
            listener?.showRegister()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}