package com.example.venditz.how_to_use_koin_kr.view

import android.os.Bundle
import com.example.venditz.how_to_use_koin_kr.BaseActivity
import com.example.venditz.how_to_use_koin_kr.R
import com.example.venditz.how_to_use_koin_kr.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    
    //inject view model
    private val mViewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addDisposable(mViewModel.observableData.subscribe{
            kotlin_textview.text = it
        })

        kotlin_button.setOnClickListener { mViewModel.changeData() }
    }
}
