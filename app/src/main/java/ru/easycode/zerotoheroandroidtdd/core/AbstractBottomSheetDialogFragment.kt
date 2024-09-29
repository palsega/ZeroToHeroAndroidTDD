package ru.easycode.zerotoheroandroidtdd.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class AbstractBottomSheetDialogFragment<B : ViewBinding, V : VMWithComeBack> :
    BottomSheetDialogFragment() {
    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding) { "${javaClass.simpleName} binding init" }
    private var onBackPressedCallback: OnBackPressedCallback? = null
    protected lateinit var viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = bind(inflater, container)
        return binding.root
    }

    protected abstract fun bind(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandBottomSheetDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback as OnBackPressedCallback)
        return dialog
    }

    private fun expandBottomSheetDialog() {
        val bottomSheetDialogFragmentBehavior = (dialog as BottomSheetDialog).behavior
        bottomSheetDialogFragmentBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback = null
        _binding = null
    }
}

abstract class VMWithComeBack : ViewModel() {
    abstract fun comeback()
}