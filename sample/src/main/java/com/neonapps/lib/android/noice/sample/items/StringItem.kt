package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.BindingTypedHolder
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemStringBinding
import com.neonapps.lib.android.noice.sample.entities.StringEntity

class StringItem(val item : StringEntity, val listener : Listener? = null) : AppSimpleAdapterItem() {

    override val type: Int = TYPE.String
    override var isSelected: Boolean = false

    interface Listener {
        fun onClick(item : StringEntity, position : Int)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder !is StringViewHolder)
            return

        holder.binding.value = item.value
        holder.binding.relativelayoutItem.setOnClickListener {
            listener?.onClick(item, position)
        }
    }

    override fun update(payload: Any) {
        if(payload is String) {
            item.value = payload
        }
    }

    override fun createProvider(): TypedHolder.Provider = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = StringViewHolder(ListitemStringBinding.bind(inflater.inflate(R.layout.listitem_string, group, attachToParent)))
    }

    class StringViewHolder(binding : ListitemStringBinding) : BindingTypedHolder<ListitemStringBinding>(binding)
}