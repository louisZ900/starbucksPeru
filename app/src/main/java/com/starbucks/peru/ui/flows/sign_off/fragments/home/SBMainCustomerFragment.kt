package com.starbucks.peru.ui.flows.sign_off.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.starbucks.peru.R
import com.starbucks.peru.core.fragment.showHomeTitle
import com.starbucks.peru.core.utils.Resource
import com.starbucks.peru.databinding.FragmentHomeBinding
import com.starbucks.peru.domain.response.SBPromotionsCarouselModel
import com.starbucks.peru.domain.response.SBPromotionsModel
import com.starbucks.peru.ui.flows.sign_off.fragments.home.adapter.PromotionsAdapter
import com.starbucks.peru.ui.flows.sign_off.fragments.home.adapter.PromotionsCarouselAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SBMainCustomerFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }
        binding.textHome.setOnClickListener {
            homeViewModel.getPromotions()
        }*/
        showHomeTitle(R.string.sb_welcome_customer)
        bindViewModel()
        homeViewModel.getPromotions()
        homeViewModel.getPromotionsCorusel()
    }

    private fun bindViewModel(){
        homeViewModel.promotionsLiveData.observe(requireActivity(), onPromotions)
        homeViewModel.promotionsCarouselLiveData.observe(requireActivity(), onPromotionsCarousel)
    }


    private val onPromotions = Observer <Resource<List<SBPromotionsModel>>> {
        when (it) {
            is Resource.OnSuccess -> {
                binding.recyclerPromotions.adapter = PromotionsAdapter(it.data ?: arrayListOf()) {

                }
                binding.recyclerPromotions.layoutManager = LinearLayoutManager(requireActivity())
                binding.recyclerPromotions.setHasFixedSize(true)
            }
            is Resource.OnError -> {

            }
            is Resource.OnLoader -> {

            }
        }
    }

    private val onPromotionsCarousel = Observer <Resource<List<SBPromotionsCarouselModel>>> {
        when (it) {
            is Resource.OnSuccess -> {
                binding.recyclerRewards.adapter = PromotionsCarouselAdapter(it.data ?: arrayListOf()) {

                }
                binding.recyclerRewards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerRewards.setHasFixedSize(true)
            }
            is Resource.OnError -> {

            }
            is Resource.OnLoader -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}