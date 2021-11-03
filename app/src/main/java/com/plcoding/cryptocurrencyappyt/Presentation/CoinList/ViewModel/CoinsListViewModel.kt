package com.plcoding.cryptocurrencyappyt.Presentation.CoinList.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.Commom.Resource
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.Domain.UseCases.GetCoins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.Presentation.CoinList.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel(){


    init {
    getCoins()
    }

    private val _state = mutableStateOf(CoinListState())
    val state : State<CoinListState> = _state

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Loading ->{
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Error ->{
                    _state.value = CoinListState(error = result.message ?: "An Unexpected Error has Occurred")
                }

                is Resource.Success ->{
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}