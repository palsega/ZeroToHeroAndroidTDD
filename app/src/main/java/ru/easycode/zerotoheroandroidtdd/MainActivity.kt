package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = MainViewModelFactory(
            LiveDataWrapper.Base(),
            Repository.Base()
        )
        viewModel =  viewModelFactory.create(MainViewModel::class.java)

        binding.actionButton.setOnClickListener {
            viewModel.load()
        }

        savedInstanceState?.let { uiState ->
            viewModel.restore(BundleWrapper.Base(uiState))
        } ?: viewModel.init()

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(binding.progressBar, binding.titleTextView, binding.actionButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
}