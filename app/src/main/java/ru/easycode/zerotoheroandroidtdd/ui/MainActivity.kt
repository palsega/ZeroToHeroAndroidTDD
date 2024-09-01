package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.list.Adapter
import ru.easycode.zerotoheroandroidtdd.ui.add.AddBottomSheetDialogFragment

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = viewModel(MainViewModel::class.java)

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetDialogFragment().show(
                supportFragmentManager,
                "AddBottomSheetDialogFragment"
            )
        }

        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        (application as ProvideViewModel).viewModel(viewModelClass)
}