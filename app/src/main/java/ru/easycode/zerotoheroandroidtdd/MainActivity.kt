package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "binding init" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputEditText.addTextChangedListener { text ->
            binding.actionButton.isEnabled = (text?.length ?: 0) >= 3
        }

        binding.actionButton.setOnClickListener {
            binding.inputEditText.text?.let { text ->
                binding.actionButton.isEnabled = false
                binding.titleTextView.text = text
                binding.inputEditText.setText("")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}