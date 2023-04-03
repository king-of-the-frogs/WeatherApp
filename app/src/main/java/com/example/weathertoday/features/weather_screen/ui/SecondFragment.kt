package com.example.weathertoday.features.weather_screen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weathertoday.R
import com.example.weathertoday.base.viewBinding
import com.example.weathertoday.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class SecondFragment : Fragment(R.layout.fragment_second) {

    // Получение объекта привязки с помощью делегата viewBinding, что упрощает доступ к элементам макета
    private val binding by viewBinding { FragmentSecondBinding.bind(it) }

    // Получение ViewModel, созданной с помощью Koin
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Наблюдение за изменением состояния viewState во ViewModel
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

    }

    // Обработка изменения состояния viewState
    @SuppressLint("SetTextI18n", "NewApi")
    private fun render(viewState: ViewState) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
        binding.loader.isVisible = !viewState.isLoading
        binding.address.text = viewState.city
        binding.temp.text = "${viewState.temp} °C"
        binding.tempMax.text = "${viewState.tempMax} °C"
        binding.tempMin.text = "${viewState.tempMin} °C"
        binding.feelsLike.text = "${viewState.tempMin} °C"
        binding.wind.text = viewState.speed.toString()
        binding.pressure.text = viewState.pressure.toString()
        binding.humidity.text = viewState.humidity.toString()
        binding.sunrise.text =
            SimpleDateFormat("hh:mm a", Locale.UK).format(Date(viewState.sunrise * 1000))
        binding.sunset.text =
            SimpleDateFormat("hh:mm a", Locale.UK).format(Date(viewState.sunset * 1000))
        binding.timeZone.text = ZonedDateTime.now(ZoneOffset.ofTotalSeconds(viewState.timeZone))
            .format(formatter)

    }

}



