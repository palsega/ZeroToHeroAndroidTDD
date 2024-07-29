package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parcelize
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var state: State

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        state = State.INITIAL
        binding.removeButton.setOnClickListener {
            state = State.REMOVEDANDDISABLED
            state.adjust(binding.rootLayout, binding.titleTextView, binding.removeButton)
        }

        savedInstanceState?.let { bundle ->
            state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(KEY, State::class.java)
            } else {
                bundle.getParcelable(KEY)
            } as State
            state.adjust(binding.rootLayout, binding.titleTextView, binding.removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY, state)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY = "key"
    }
}

interface State : Parcelable {

    fun adjust(viewGroup: ViewGroup, textView: TextView, button: Button)

    @Parcelize
    object INITIAL : State {
        override fun adjust(viewGroup: ViewGroup, textView: TextView, button: Button) = Unit
    }

    @Parcelize
    object REMOVEDANDDISABLED : State, RemoveView, DisableView {

        override fun removeView(viewGroup: ViewGroup, view: View) {
            viewGroup.removeView(view)
        }

        override fun disableView(view: View) {
            view.isEnabled = false
        }

        override fun adjust(viewGroup: ViewGroup, textView: TextView, button: Button) {
            removeView(viewGroup, textView)
            disableView(button)
        }
    }
}

interface RemoveView {
    fun removeView(viewGroup: ViewGroup, view: View)
}

interface DisableView {
    fun disableView(view: View)
}