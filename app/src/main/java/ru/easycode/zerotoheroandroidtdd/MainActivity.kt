package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val count = Count.Base(2)

        binding.incrementButton.setOnClickListener {
            val currentValue = binding.countTextView.text.toString()
            val newValue = count.increment(currentValue)
            binding.countTextView.text = newValue
        }

        savedInstanceState?.run {
            binding.countTextView.text = getString(KEY)!!
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, binding.countTextView.text.toString())
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY = "key"
    }
}

interface Count {
    fun increment(number: String): String
    class Base(private val step: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException(
                "step should be positive, but was $step"
            )
        }

        override fun increment(number: String) =
            (number.toInt() + step).toString()
    }
}
