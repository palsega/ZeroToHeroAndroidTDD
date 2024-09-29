package ru.easycode.zerotoheroandroidtdd.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.ui.add.AddBottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteBottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteItemUi
import ru.easycode.zerotoheroandroidtdd.ui.list.Adapter

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java)

        val adapter = Adapter(object : DeleteItemUi {
            override fun delete(itemId: Long) {
                DeleteBottomSheetDialogFragment.newInstance(itemId)
                    .show(supportFragmentManager, "DeleteBottomSheetFragment")
            }
        })
        binding.recyclerView.adapter = adapter


        binding.addButton.setOnClickListener {
            AddBottomSheetDialogFragment().show(
                supportFragmentManager, "AddBottomSheetDialogFragment"
            )
        }

        viewModel.liveData().observe(this) { itemsUi ->
            adapter.update(itemsUi)
        }

        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        (application as ProvideViewModel).viewModel(viewModelClass)
}