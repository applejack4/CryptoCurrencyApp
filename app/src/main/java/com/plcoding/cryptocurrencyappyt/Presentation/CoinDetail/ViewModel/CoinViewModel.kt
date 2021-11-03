package com.plcoding.cryptocurrencyappyt.Presentation.CoinDetail.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.Commom.Constants
import com.plcoding.cryptocurrencyappyt.Commom.Resource
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.Domain.UseCases.GetCoin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.Domain.UseCases.GetCoins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.Presentation.CoinDetail.CoinState
import com.plcoding.cryptocurrencyappyt.Presentation.CoinList.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){


    init {
    savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
        getCoin(coinId)
    }
    }

    private val _state = mutableStateOf(CoinState())
    val state : State<CoinState> = _state

    private fun getCoin(coinId : String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Loading ->{
                    _state.value = CoinState(isLoading = true)
                }

                is Resource.Error ->{
                    _state.value = CoinState(error = result.message ?: "An Unexpected Error has Occurred")
                }

                is Resource.Success ->{
                    _state.value = CoinState(coin = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}