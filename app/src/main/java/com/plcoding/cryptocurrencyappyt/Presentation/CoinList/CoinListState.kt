package com.plcoding.cryptocurrencyappyt.Presentation.CoinList

import com.plcoding.cryptocurrencyappyt.Domain.Model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error : String = ""
)
