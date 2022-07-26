package com.example.practicalproject.modelclass

data class Data(
    val itemsPerPage: Int,
    val list: List<CoinDetails>,
    val startIndex: Int,
    val totalItems: Int
)