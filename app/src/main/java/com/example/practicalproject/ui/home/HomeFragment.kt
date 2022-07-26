package com.example.practicalproject.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicalproject.Network.NetworkConnection
import com.example.practicalproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var viewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setValues()
        fetchData(requireContext())
        setListners()

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    private fun setValues(){
        isLoading(true)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getCoins()

    }

    private  fun fetchData(context:Context){
        isLoading(true)
        isInternet(true)
        viewModel.data.observe(viewLifecycleOwner) {
            val list= it
            if (NetworkConnection().isNetworkAvailable(requireContext())){


                if (it!=null){
                    Log.d("erroroccured",list.toString())
                    binding.coinsRecycler.apply {
                        layoutManager = GridLayoutManager(context,3)
                        adapter= HomeAdapter(context,it)
                    }
                    isLoading(false)

                }
            }else{
                isInternet(false)
            }
        }
    }
    private fun setListners(){
        binding.nointernet.tryAgainButton.setOnClickListener {
            fetchData(requireContext())
        }

    }
    private fun isInternet(internetAvaliable:Boolean){
        if (internetAvaliable){
            binding.nointernet.nointernet.isVisible=false
            binding.coinsRecycler.isVisible=true
        }else{
            binding.nointernet.nointernet.isVisible=true
            binding.coinsRecycler.isVisible=false
        }

    }

    private fun isLoading(loading:Boolean){
        if (loading){
            binding.progress.isVisible=true
            binding.coinsRecycler.isVisible=false

        }else{
            binding.progress.isVisible=false
            binding.coinsRecycler.isVisible=true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}