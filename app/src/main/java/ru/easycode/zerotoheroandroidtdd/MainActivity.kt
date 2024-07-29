package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var state: State = State.INITIAL
    private lateinit var titleTextView: TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        titleTextView = binding.titleTextView

        binding.removeButton.setOnClickListener {
            state = State.REMOVED
            state.removeViewFromLayout(binding.rootLayout, titleTextView)
        }

        savedInstanceState?.let { bundle ->
            state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY, State::class.java) as State
            } else {
                bundle.getSerializable(KEY) as State
            }
            state.removeViewFromLayout(binding.rootLayout, titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY = "key"
    }
}

interface State : Serializable {
    fun removeViewFromLayout(viewGroup: ViewGroup, view: View)

    object INITIAL : State {
        private fun readResolve(): Any = INITIAL
        override fun removeViewFromLayout(viewGroup: ViewGroup, view: View) = Unit
    }

    object REMOVED : State {
        private fun readResolve(): Any = REMOVED
        override fun removeViewFromLayout(viewGroup: ViewGroup, view: View) =
            viewGroup.removeView(view)
    }
}