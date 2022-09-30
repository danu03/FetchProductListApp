package com.danusuhendra.fetchproductlistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.danusuhendra.fetchproductlistapp.adapter.ProductAdapter
import com.danusuhendra.fetchproductlistapp.databinding.ActivityMainBinding
import com.danusuhendra.fetchproductlistapp.model.Product
import com.danusuhendra.fetchproductlistapp.utlis.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    private val viewModel: MainViewModel by viewModels()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)
        setUpRecyclerView()

        viewModel.getProduct()

        viewModel.dataState.observe(this) { dataState ->
            when(dataState) {
                is DataState.Success<List<Product>> -> {
                    displayLoading(false)
                    appendProduct(dataState.data)
                }
                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayLoading(true)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = ProductAdapter()
        binding.apply {
            rvProduct.layoutManager = layoutManager
            rvProduct.setHasFixedSize(true)
            rvProduct.adapter = adapter
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun appendProduct(product: List<Product>) {
        adapter.setList(product)
    }
}