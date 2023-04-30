package com.example.weathertoday.features.weather_screen.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weathertoday.R
import com.example.weathertoday.base.viewBinding
import com.example.weathertoday.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    // Переменная, содержащая привязку ViewBinding к макету фрагмента
    private val binding by viewBinding { FragmentMainBinding.bind(it) }

    // Получение ViewModel, созданной с помощью Koin
    private val viewModel: MainViewModel by sharedViewModel()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            // Обработка события загрузки погоды по названию города
            viewModel.processDataEvent(UiEvent.LoadWeatherFromCity(binding.etStart.text.toString()))
            openWeather()
        }


        // Ночная тема
        if (binding.btnStart.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            binding.fragment1.setBackgroundResource(R.drawable.gradient_bg2)
            binding.label.setColorFilter(Color.WHITE)
            binding.btnStart.setBackgroundColor(Color.BLACK)
        }
    }

    // Функция для перехода на другой фрагмент
    private fun openWeather() {
        findNavController().navigate(R.id.action_mainFragment_to_secondFragment2)
    }
}