package ru.easycode.zerotoheroandroidtdd.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractBottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddBottomSheetDialogFragment :
    AbstractBottomSheetDialogFragment<FragmentAddBinding, AddViewModel>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentAddBinding {
        return FragmentAddBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(AddViewModel::class.java)

        binding.addInputEditText.addTextChangedListener {
            binding.saveButton.isEnabled = (binding.addInputEditText.text?.length ?: 0) > 3
        }

        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            dismiss()
        }
    }
}