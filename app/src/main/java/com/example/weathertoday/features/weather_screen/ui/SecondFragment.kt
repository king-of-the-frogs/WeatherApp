package com.example.weathertoday.features.weather_screen.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weathertoday.R
import com.example.weathertoday.base.viewBinding
import com.example.weathertoday.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.ZoneId
import java.text.SimpleDateFormat
import java.time.Instant
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
    @SuppressLint("SetTextI18n", "NewApi", "ResourceType")
    private fun render(viewState: ViewState) {
        val formatterForDate = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
        val formatterForTime = DateTimeFormatter.ofPattern("hh:mm a")

        // Объединение timeZone и sun(rise/set) для корректного вывода информации в зависимости от timeZone искомго города
        val timeZone = ZoneOffset.ofTotalSeconds(viewState.timeZone)
        val sunriseInstant = Instant.ofEpochSecond(viewState.sunrise)
        val sunsetInstant = Instant.ofEpochSecond(viewState.sunset)
        val sunriseTime = ZonedDateTime.ofInstant(sunriseInstant, timeZone)
        val sunsetTime = ZonedDateTime.ofInstant(sunsetInstant, timeZone)

        // Привязка данных к textView для вывода на экран
        binding.loader.isVisible = !viewState.isLoading
        binding.address.text = viewState.city
        binding.temp.text = "${viewState.temp} °C"
        binding.tempMax.text = "${viewState.tempMax} °C"
        binding.tempMin.text = "${viewState.tempMin} °C"
        binding.feelsLike.text = "${viewState.tempMin} °C"
        binding.wind.text = viewState.speed.toString()
        binding.pressure.text = viewState.pressure.toString()
        binding.humidity.text = viewState.humidity.toString()
        binding.sunrise.text = sunriseTime.format(formatterForTime)
        binding.sunset.text = sunsetTime.format(formatterForTime)
        binding.timeZone.text = ZonedDateTime.now(ZoneOffset.ofTotalSeconds(viewState.timeZone))
            .format(formatterForDate)

        // Ночная тема
        if (binding.address.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            binding.address.setTextColor(getResources().getColor(R.color.blue2));
            binding.temp.setTextColor(getResources().getColor(R.color.black));
            binding.tempMax.setTextColor(getResources().getColor(R.color.blue3));
            binding.tempMin.setTextColor(getResources().getColor(R.color.blue3));
            binding.feelsLike.setTextColor(getResources().getColor(R.color.black));
            binding.wind.setTextColor(getResources().getColor(R.color.black));
            binding.pressure.setTextColor(getResources().getColor(R.color.black));
            binding.humidity.setTextColor(getResources().getColor(R.color.black));
            binding.sunrise.setTextColor(getResources().getColor(R.color.black));
            binding.sunset.setTextColor(getResources().getColor(R.color.black));
            binding.timeZone.setTextColor(getResources().getColor(R.color.blue2));
        }
    }


}



