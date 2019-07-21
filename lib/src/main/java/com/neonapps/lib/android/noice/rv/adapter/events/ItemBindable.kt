package com.neonapps.lib.android.noice.rv.adapter.events

import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem

interface ItemBindable<V> {
    fun bind(adapter : AppSimpleAdapter<V>, holder : RecyclerView.ViewHolder, position : Int){}

    fun onBound(visitor : V, holder : RecyclerView.ViewHolder, item : AppSimpleAdapterItem<V>, position : Int)
}