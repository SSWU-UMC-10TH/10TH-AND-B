package com.example.week04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week04.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())


        val dummyList = listOf(
            Product("Air Jordan XXXVI", "US$185", R.drawable.airjordan),
            Product("Nike Air Force 1 '07", "US$115", R.drawable.airforce)
        )


        binding.homeRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        lifecycleScope.launch {
            val isFirst = dataStoreManager.isFirstRun().first()
            if (isFirst) {
                dataStoreManager.saveProducts(dummyList)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getProducts().collect { list ->
                binding.homeRecyclerview.adapter = ProductAdapter(list) { updatedList ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        dataStoreManager.saveProducts(updatedList)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}