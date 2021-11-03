package com.plcoding.cryptocurrencyappyt.Presentation.CoinDetail

import com.plcoding.cryptocurrencyappyt.Domain.Model.Coin
import com.plcoding.cryptocurrencyappyt.Domain.Model.CoinDetail

data class CoinState(
    val isLoading : Boolean = false,
    val coin : CoinDetail ?= null,
    val error : String = ""
)
