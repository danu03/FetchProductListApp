package com.danusuhendra.fetchproductlistapp.ui

import androidx.lifecycle.*
import com.danusuhendra.fetchproductlistapp.model.Product
import com.danusuhendra.fetchproductlistapp.repository.MainRepository
import com.danusuhendra.fetchproductlistapp.utlis.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Product>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Product>>>
        get() = _dataState

    fun getProduct(limit: Int) {
        viewModelScope.launch {
            repository.getProduct(limit)
                .onEach { dataState ->
                    _dataState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

}