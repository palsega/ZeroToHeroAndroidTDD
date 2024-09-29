package ru.easycode.zerotoheroandroidtdd.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding
import ru.easycode.zerotoheroandroidtdd.ui.ItemUi
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteItemUi

class Adapter(
    private val deleteItemUi: DeleteItemUi,
    private val inputs: ArrayList<ItemUi> = ArrayList()
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUi: ItemUi) {
            itemUi.show(binding.elementTextView)
            itemView.setOnClickListener {
                itemUi.delete(deleteItemUi)
            }
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

    fun update(newList: List<ItemUi>) {
        val diffUtilCallback = DiffUtilCallback(inputs, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        inputs.clear()
        inputs.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

class DiffUtilCallback(
    private val old: List<ItemUi>,
    private val new: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].areItemsTheSame(new[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

}