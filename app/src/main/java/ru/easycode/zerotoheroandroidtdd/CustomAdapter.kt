package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding

class CustomAdapter(private val inputs: ArrayList<CharSequence> = ArrayList()) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(input: CharSequence) {
            binding.elementTextView.text = input
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = inputs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(inputs[position])
    }
}