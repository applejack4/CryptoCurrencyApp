package com.plcoding.cryptocurrencyappyt.Domain.Repository

import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.Domain.Model.Coin

interface CoinRepository {

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinById(id : String) : CoinDetailDto

}