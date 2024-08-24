package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}