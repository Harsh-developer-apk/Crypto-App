package com.example.cryptoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.cryptoapp.LoginActivity
import com.example.cryptoapp.MainActivity
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.TopLossGainPagerAdapter
import com.example.cryptoapp.adapter.TopMarketAdapter
import com.example.cryptoapp.api.ApiInterface
import com.example.cryptoapp.api.ApiUtilities
import com.example.cryptoapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        getTopCurrencyList()
        setTabLayout()
        binding.logoutButton.setOnClickListener {
            logout()
        }
        return binding.root
    }

 /*   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            requireActivity().finish()
        }
    }*/

    private fun logout(){
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val intent = Intent(requireContext(),LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()

    }
    private fun setTabLayout() {
        val adapter  = TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object:OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0){
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                }
                else{
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility = VISIBLE
                }
            }
        })
        TabLayoutMediator(binding.tabLayout,binding.contentViewPager){
            tab,position ->
            var title = if(position==0){
                "Top Gainers"
            }
            else{
                "Top Losers"
            }
            tab.text = title
        }.attach()

    }


    private fun getTopCurrencyList(){
        lifecycleScope.launch(Dispatchers.IO) {
                val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()
                withContext(Dispatchers.Main){
                    binding.topCurrencyRecyclerView.adapter = TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
                }

            Log.d("CRYPTO","getTopCurrencyList: ${res.body()!!.data.cryptoCurrencyList}")
        }
    }
}