package com.plcoding.cryptocurrencyappyt.Data.Repository

import com.plcoding.cryptocurrencyappyt.Data.Remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.Domain.Repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject
        constructor(private val coinPaprikaApi: CoinPaprikaApi) :
        CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetailDto {
         return coinPaprikaApi.getCoinById(id)
    }

}