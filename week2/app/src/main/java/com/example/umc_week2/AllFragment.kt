package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.umc_week2.databinding.FragmentAllBinding
import com.example.umc_week2.ui.buy.BuyViewModel
import com.example.umc_week2.ui.buy.ProductListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BuyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeBuyProducts.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        binding.composeBuyProducts.setContent {
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.errorMessage) {
                state.errorMessage?.let { message ->
                    Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            ProductListScreen(
                products = state.productList,
                onWishClick = { clickedProduct ->
                    val position = state.productList.indexOfFirst { product ->
                        product.imageResId == clickedProduct.imageResId &&
                                product.name == clickedProduct.name &&
                                product.price == clickedProduct.price
                    }

                    if (position != -1) {
                        viewModel.toggleLike(position)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}