package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.ID
import com.neonapps.lib.android.noice.sample.databinding.ListitemDoubleBinding
import com.neonapps.lib.android.noice.sample.entities.DoubleEntity
import com.neonapps.lib.android.noice.sample.vh.DoubleViewHolder
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor

class DoubleItem(val item : DoubleEntity) : AppSimpleAdapterItem<SampleItemVisitor> {

    override val type: Int = ID.DOUBLE
    override var isSelected: Boolean = false

    override fun bind(adapter: AppSimpleAdapter<SampleItemVisitor>, holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is DoubleViewHolder){
            holder.binding.value = item.value.toString()
            holder.binding.executePendingBindings()

            if(!adapter.multiSelectionEnabled) {
                isSelected = false
                holder.binding.checkboxSelected.isChecked = false
            }

            holder.binding.relativelayoutItem.setOnLongClickListener {
                adapter.multiSelectionEnabled = !adapter.multiSelectionEnabled
                isSelected = adapter.multiSelectionEnabled
                holder.binding.checkboxSelected.isChecked = isSelected

                adapter.dispatchTouchEvent(this, position, "")
                false
            }

            holder.binding.relativelayoutItem.setOnClickListener{

                if(adapter.multiSelectionEnabled) {
                    isSelected = !isSelected
                    holder.binding.checkboxSelected.isChecked = isSelected
                }

                adapter.dispatchTouchEvent(this, position, "")
            }

            holder.binding.checkboxSelected.visibility = if(adapter.multiSelectionEnabled)
                View.VISIBLE
            else
                View.GONE
        }
    }

    override fun click(visitor: SampleItemVisitor, position: Int, eventName: String) {

    }

    override fun onBound(visitor: SampleItemVisitor, holder: RecyclerView.ViewHolder, item: AppSimpleAdapterItem<SampleItemVisitor>, position: Int, eventName: String) {

    }

    override fun getId(position: Int): Long = position.toLong()

    override fun createPrototype() : TypedHolder.Prototype  = Prototype()

    inner class Prototype : TypedHolder.Prototype {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = DoubleViewHolder(ListitemDoubleBinding.bind(inflater.inflate(R.layout.listitem_double, group, attachToParent)))
    }
}