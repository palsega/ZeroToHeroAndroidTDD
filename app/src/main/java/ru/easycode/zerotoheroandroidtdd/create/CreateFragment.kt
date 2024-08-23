package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

class CreateFragment : AbstractFragment<FragmentCreateBinding>() {

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateBinding {
        return FragmentCreateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (activity as ProvideViewModel).viewModel(CreateViewModel::class.java)

        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled = (it?.length ?: 0) > 2
        }

        binding.createButton.setOnClickListener {
            viewModel.add(binding.inputEditText.text.toString())
        }

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}