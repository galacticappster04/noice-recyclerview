package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemStringBinding
import com.neonapps.lib.android.noice.sample.entities.StringEntity
import com.neonapps.lib.android.noice.sample.vh.StringViewHolder
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor

class StringItem(val item : StringEntity) : AppSimpleAdapterItem<SampleItemVisitor>() {

    override val type: Int = TYPE.String
    override var isSelected: Boolean = false

    override fun bind(holder: RecyclerView.ViewHolder, visitor : SampleItemVisitor?, position: Int) {

        if(holder !is StringViewHolder)
            return

        holder.binding.value = item.value
    }

    override fun update(payload: Any) {
        if(payload is String) {
            item.value = payload
        }
    }

    override fun create(): TypedHolder.Provider = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = StringViewHolder(ListitemStringBinding.bind(inflater.inflate(R.layout.listitem_string, group, attachToParent)))
    }
}