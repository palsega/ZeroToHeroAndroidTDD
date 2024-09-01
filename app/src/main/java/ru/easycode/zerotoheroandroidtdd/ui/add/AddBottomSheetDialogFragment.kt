package ru.easycode.zerotoheroandroidtdd.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(AddViewModel::class.java)
        expandBottomSheetDialog()
        addOnBackPressedCallback()

        binding.addInputEditText.addTextChangedListener {
            binding.saveButton.isEnabled = (binding.addInputEditText.text?.length ?: 0) > 2
        }

        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            dismissAndBack()
        }
    }

    private fun expandBottomSheetDialog() {
        val modalBottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        modalBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun addOnBackPressedCallback() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dismissAndBack()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    private fun dismissAndBack() {
        dismiss()
        viewModel.comeback()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
        _binding = null
    }
}

