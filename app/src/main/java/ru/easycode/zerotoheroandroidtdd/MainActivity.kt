package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.NonCancellable.join
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "init binding" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            binding.actionButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
//            val handler = Handler(Looper.getMainLooper())
//            handler.postDelayed(
//                {
//                    binding.titleTextView.visibility = View.VISIBLE
//                    binding.actionButton.isEnabled = true
//                    binding.progressBar.visibility = View.INVISIBLE
//                },
//                3500
//            )
            lifecycleScope.launch {
                delay(3500)
                binding.titleTextView.visibility = View.VISIBLE
                binding.actionButton.isEnabled = true
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}