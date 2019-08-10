package sample.nackun.com.studyfirst.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sample.nackun.com.studyfirst.ui.ticker.TickerViewModel

val viewModelModule = module {
    viewModel { TickerViewModel(get()) }
}