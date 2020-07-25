package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.BindingTypedHolder
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemFloatBinding
import com.neonapps.lib.android.noice.sample.entities.FloatEntity

class FloatItem(val item : FloatEntity, val listener : Listener? = null) : AppSimpleAdapterItem() {

    override val type: Int = TYPE.FLOAT
    override var isSelected: Boolean = false

    interface Listener {
        fun onClick(item : FloatEntity, position : Int)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder !is FloatViewHolder)
            return

        holder.binding.value = item.value.toString()
        holder.binding.relativelayoutItem.setOnClickListener {
            listener?.onClick(item, position)
        }
    }

    override fun createProvider(): TypedHolder.Provider = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = FloatViewHolder(ListitemFloatBinding.bind(inflater.inflate(R.layout.listitem_float, group, attachToParent)))
    }

    class FloatViewHolder(binding : ListitemFloatBinding) : BindingTypedHolder<ListitemFloatBinding>(binding)
}