package com.plcoding.cryptocurrencyappyt.Domain.Model

import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.Tag
import com.plcoding.cryptocurrencyappyt.Data.Remote.dto.TeamMembers

data class CoinDetail(
    val coinId : String,
    val name : String,
    val description : String,
    val symbol : String,
    val rank : Int,
    val isActive : Boolean,
    val tags : List<String>,
    val team : List<TeamMembers>
)
