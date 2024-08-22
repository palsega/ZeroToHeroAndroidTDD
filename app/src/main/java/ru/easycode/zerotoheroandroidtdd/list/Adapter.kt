package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding

class Adapter(private val inputs: ArrayList<CharSequence> = ArrayList()) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(source: CharSequence) {
            binding.elementTextView.text = source
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

    fun update(newList: ArrayList<CharSequence>) {
        val diffUtilCallback = DiffUtilCallback(inputs, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        inputs.clear()
        inputs.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

class DiffUtilCallback(
    private val old: List<CharSequence>,
    private val new: ArrayList<CharSequence>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}