package com.example.venditz.how_to_use_koin_kr.model

import com.example.venditz.how_to_use_koin_kr.data.HelloKotlinData

class HelloDataModelImpl : HelloDataModel {
    override fun printHello() : HelloKotlinData {
        return HelloKotlinData("KOTLIN", System.currentTimeMillis())
    }
}