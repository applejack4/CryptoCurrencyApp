package com.plcoding.cryptocurrencyappyt.Domain.UseCases.GetCoins

import com.plcoding.cryptocurrencyappyt.Commom.Resource
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.Domain.Model.Coin
import com.plcoding.cryptocurrencyappyt.Domain.Repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map {
                it.toCoin()
            }
            emit(Resource.Success(coins))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error"))
        }catch (e : IOException){
            emit(Resource.Error(e.localizedMessage ?: "Couldn't Reach Server, Try your internet connection."))
        }
    }
}