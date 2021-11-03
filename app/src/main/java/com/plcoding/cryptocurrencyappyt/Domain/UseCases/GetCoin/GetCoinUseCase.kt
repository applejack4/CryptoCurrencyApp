package com.plcoding.cryptocurrencyappyt.Domain.UseCases.GetCoin

import com.plcoding.cryptocurrencyappyt.Commom.Resource
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.Domain.Model.CoinDetail
import com.plcoding.cryptocurrencyappyt.Domain.Repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId : String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error"))
        }catch (e : IOException){
            emit(Resource.Error(e.localizedMessage ?: "Couldn't Reach Server, Try your internet connection."))
        }
    }
}