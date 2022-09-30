package com.danusuhendra.fetchproductlistapp.repository

import com.danusuhendra.fetchproductlistapp.model.Product
import com.danusuhendra.fetchproductlistapp.network.ApiInterface
import com.danusuhendra.fetchproductlistapp.network.NetworkMapper
import com.danusuhendra.fetchproductlistapp.utlis.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val retrofit: ApiInterface,
    private val networkMapper: NetworkMapper
){

    suspend fun getProduct(limit: Int): Flow<DataState<List<Product>>> = flow {
        emit(DataState.Loading)
        try {
            val networkProduct = retrofit.getProductList(limit)
            val products = networkMapper.mapFromEntityList(networkProduct)
            emit(DataState.Success(products))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}