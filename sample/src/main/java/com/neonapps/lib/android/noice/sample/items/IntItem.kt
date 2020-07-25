package com.neonapps.lib.android.noice.sample.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.BindingTypedHolder
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.R
import com.neonapps.lib.android.noice.sample.constants.TYPE
import com.neonapps.lib.android.noice.sample.databinding.ListitemIntBinding
import com.neonapps.lib.android.noice.sample.entities.IntEntity

class IntItem(val item : IntEntity, val listener : Listener? = null) : AppSimpleAdapterItem(), LifecycleObserver {

    override val type: Int = TYPE.INT
    override var isSelected: Boolean = false

    interface Listener {
        fun onClick(item : IntEntity, position : Int)
    }

    override fun onAdded() {
        adapter.owner?.lifecycle?.addObserver(this)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder !is IntViewHolder)
            return

        holder.binding.value = item.value.toString()
        holder.binding.relativelayoutItem.setOnClickListener {
            listener?.onClick(item, position)
        }
    }

    override fun createProvider(): TypedHolder.Provider = object : TypedHolder.Provider {
        override fun create(inflater: LayoutInflater, group: ViewGroup, attachToParent: Boolean): TypedHolder
            = IntViewHolder(ListitemIntBinding.bind(inflater.inflate(R.layout.listitem_int, group, attachToParent)))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onCreate() {

    }

    class IntViewHolder(binding : ListitemIntBinding) : BindingTypedHolder<ListitemIntBinding>(binding)
}