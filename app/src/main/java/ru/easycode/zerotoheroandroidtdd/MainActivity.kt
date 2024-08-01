package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var uiState: UiState by Delegates.observable(UiState.Base("")) { _, _, newValue ->
        newValue.apply(
            binding.countTextView, binding.decrementButton, binding.incrementButton
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val count = Count.Base(2, 4, 0)

        uiState = savedInstanceState?.run {
            if (Build.VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                getParcelable(KEY, uiState::class.java)
            } else {
                getParcelable(KEY)
            }
        } ?: count.initial(binding.countTextView.text.toString())

        binding.decrementButton.setOnClickListener {
            uiState = count.decrement(binding.countTextView.text.toString())
        }

        binding.incrementButton.setOnClickListener {
            uiState = count.increment(binding.countTextView.text.toString())
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY, uiState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY = "key"
    }
}