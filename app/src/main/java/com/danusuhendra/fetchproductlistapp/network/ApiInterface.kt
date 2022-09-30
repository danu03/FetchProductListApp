package com.danusuhendra.fetchproductlistapp.network

import com.danusuhendra.fetchproductlistapp.model.ProductResponse
import com.danusuhendra.fetchproductlistapp.model.ProductResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("products")
    suspend fun getProductList(
        @Query("limit") limit: Int = 4
    ): List<ProductResponseItem>
}