package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "binding init" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = MainViewModelFactory(
            LiveDataWrapper.Base(),
            Repository.Base()
        )
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.actionButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(binding.progressBar, binding.titleTextView, binding.actionButton)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}