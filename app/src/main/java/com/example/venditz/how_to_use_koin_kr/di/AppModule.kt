package com.example.venditz.how_to_use_koin_kr.di

import com.example.venditz.how_to_use_koin_kr.model.HelloDataModel
import com.example.venditz.how_to_use_koin_kr.model.HelloDataModelImpl
import com.example.venditz.how_to_use_koin_kr.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

//관계 정의

val MyModule : Module = module {
    factory <HelloDataModel> {
        HelloDataModelImpl()
    }
    //뷰모델에 위의 data source를 inject
    viewModel {
        MainViewModel(get()) as MainViewModel
    }
}

val MyAppModule = listOf(MyModule)