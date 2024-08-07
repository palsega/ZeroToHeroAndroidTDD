package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "binding init" }
    private lateinit var inputs: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputs = savedInstanceState?.run {
            getStringArrayList(KEY).also { list ->
                list?.forEach { input ->
                    binding.contentLayout.addView(TextView(this@MainActivity)
                        .also { it.text = input })
                }
            }
        } ?: ArrayList()

        binding.actionButton.setOnClickListener {
            val input = binding.inputEditText.text
            inputs.add(input.toString())
            val newTextView = TextView(this)
            newTextView.text = input
            binding.contentLayout.addView(newTextView)
            input?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY, inputs)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY = "key"
    }
}