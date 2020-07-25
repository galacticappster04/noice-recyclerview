package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemFloatBinding
import com.neonapps.lib.android.noice.sample.entities.FloatEntity
import com.neonapps.lib.android.noice.sample.vh.FloatViewHolder
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor

class FloatItem(val item : FloatEntity) : AppSimpleAdapterItem<SampleItemVisitor>() {

    override val type: Int = TYPE.FLOAT
    override var isSelected: Boolean = false

    override fun bind(holder: RecyclerView.ViewHolder, visitor : SampleItemVisitor?, position: Int) {

        if(holder !is FloatViewHolder)
            return

        holder.binding.value = item.value.toString()
    }

    override fun create(): TypedHolder.Provider = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = FloatViewHolder(ListitemFloatBinding.bind(inflater.inflate(R.layout.listitem_float, group, attachToParent)))
    }
}