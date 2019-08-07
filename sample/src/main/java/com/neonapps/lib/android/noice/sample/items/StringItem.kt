package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.ID
import com.neonapps.lib.android.noice.sample.databinding.ListitemStringBinding
import com.neonapps.lib.android.noice.sample.entities.StringEntity
import com.neonapps.lib.android.noice.sample.vh.StringViewHolder
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor

class StringItem(val item : StringEntity) : AppSimpleAdapterItem<SampleItemVisitor> {

    override val type: Int = ID.String
    override var isSelected: Boolean = false

    override fun bind(adapter: AppSimpleAdapter<SampleItemVisitor>, holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is StringViewHolder){
            holder.binding.value = item.value
        }
    }

    override fun click(visitor: SampleItemVisitor, position: Int, eventName: String) {
    }

    override fun getId(position: Int): Long = position.toLong()

    override fun createPrototype(): TypedHolder.Prototype = Prototype()

    inner class Prototype : TypedHolder.Prototype {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
                = StringViewHolder(ListitemStringBinding.bind(inflater.inflate(R.layout.listitem_string, group, attachToParent)))
    }
}