package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding

class Adapter(private val inputs: ArrayList<String> = ArrayList()) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.elementTextView.text = text
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

    fun update(newList: List<String>) {
        val diffUtilCallback = DiffUtilCallback(inputs, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        inputs.clear()
        inputs.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

class DiffUtilCallback(
    private val old: List<String>,
    private val new: List<String>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size
    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].hashCode() == new[newItemPosition].hashCode()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}