package com.example.venditz.how_to_use_koin_kr.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.venditz.how_to_use_koin_kr.model.HelloDataModel
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val modelImpl: HelloDataModel) : ViewModel() {
    var observableData: PublishSubject<String> = PublishSubject.create()

    fun changeData() {
        observableData.onNext(
            """
            HELLO ${modelImpl.printHello().name} DATA!
            It's ${modelImpl.printHello().time} time
            """.trimIndent()
        )
    }
}