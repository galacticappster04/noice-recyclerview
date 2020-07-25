package com.neonapps.lib.android.noice.sample.items

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.BindingTypedHolder
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemDoubleBinding
import com.neonapps.lib.android.noice.sample.entities.DoubleEntity

class DoubleItem(val item : DoubleEntity, val listener : Listener? = null) : AppSimpleAdapterItem() {

    override val type: Int = TYPE.DOUBLE
    override var isSelected: Boolean = false

    interface Listener {
        fun onClick(item : DoubleEntity, position : Int)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder !is DoubleViewHolder)
            return

        holder.binding.value = item.value.toString()
        holder.binding.executePendingBindings()

        holder.binding.checkboxSelected.isChecked = isSelected

        if(adapter.multiSelectionEnabled ?: false) {
            isSelected = false
            holder.binding.checkboxSelected.isChecked = false
        }

        holder.binding.relativelayoutItem.setOnLongClickListener {
            adapter.multiSelectionEnabled = adapter.multiSelectionEnabled ?: false
            isSelected = adapter.multiSelectionEnabled ?: true
            holder.binding.checkboxSelected.isChecked = isSelected

            listener?.onClick(item, position)
            false
        }

        holder.binding.relativelayoutItem.setOnClickListener{

            if(adapter.multiSelectionEnabled ?: true) {
                isSelected = !isSelected
                holder.binding.checkboxSelected.isChecked = isSelected
            }

            adapter.currentSelected = position
            listener?.onClick(item, position)
        }

        holder.binding.relativelayoutItem.setBackgroundColor(
            if(adapter.currentSelected == position)
                Color.parseColor("#333eee")
            else
                Color.parseColor("#A78686"))

        holder.binding.checkboxSelected.visibility = if(adapter.multiSelectionEnabled ?: false)
            View.VISIBLE
        else
            View.GONE
    }

    override fun createProvider() : TypedHolder.Provider  = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = DoubleViewHolder(ListitemDoubleBinding.bind(inflater.inflate(R.layout.listitem_double, group, attachToParent)))
    }

    class DoubleViewHolder(binding : ListitemDoubleBinding) : BindingTypedHolder<ListitemDoubleBinding>(binding)
}