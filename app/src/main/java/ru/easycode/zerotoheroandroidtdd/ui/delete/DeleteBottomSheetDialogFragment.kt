package ru.easycode.zerotoheroandroidtdd.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractBottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDeleteBinding

class DeleteBottomSheetDialogFragment :
    AbstractBottomSheetDialogFragment<FragmentDeleteBinding, DeleteViewModel>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentDeleteBinding {
        return FragmentDeleteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java)

        val itemId: Long = requireArguments().getLong(KEY).also {
            viewModel.init(it)
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTitleTextView.text = it
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }
    }

    companion object {
        fun newInstance(itemId: Long): DeleteBottomSheetDialogFragment {
            val instance = DeleteBottomSheetDialogFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return instance
        }

        private const val KEY = "ItemIdToDelete"
    }
}
