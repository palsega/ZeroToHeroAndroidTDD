package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "binding init" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.run {
            getStringArrayList(KEY).also { list ->
                list?.forEach { input ->
                    binding.contentLayout.addTextViewWithText(this@MainActivity, input)
                }
            }
        } ?: ArrayList()

        binding.actionButton.setOnClickListener {
            val input = binding.inputEditText.text.toString()
            binding.contentLayout.addTextViewWithText(this, input)
            binding.inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val inputs = ArrayList(
            binding.contentLayout.children
                .filterIsInstance<TextView>()
                .map { it.text.toString() }
                .toList()
        )
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

private fun ViewGroup.addTextViewWithText(context: Context, text: String) {
    addView(TextView(context).also {
        it.text = text
    })
}