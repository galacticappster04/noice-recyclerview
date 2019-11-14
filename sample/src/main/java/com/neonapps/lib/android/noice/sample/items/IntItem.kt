package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AdapterItem
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.ID
import com.neonapps.lib.android.noice.sample.databinding.ListitemIntBinding
import com.neonapps.lib.android.noice.sample.entities.IntEntity
import com.neonapps.lib.android.noice.sample.vh.IntViewHolder
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor

class IntItem(val item : IntEntity) : AppSimpleAdapterItem<SampleItemVisitor>() {

    override val type: Int = ID.INT
    override var isSelected: Boolean = false

    override fun click(visitor: SampleItemVisitor, position: Int, eventName: String) {

    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is IntViewHolder){
            holder.binding.value = item.value.toString()
        }
    }

    override fun createPrototype(): TypedHolder.Prototype = Prototype()

    inner class Prototype : TypedHolder.Prototype {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder {
            return IntViewHolder(ListitemIntBinding.bind(inflater.inflate(R.layout.listitem_int, group, attachToParent)))
        }
    }
}