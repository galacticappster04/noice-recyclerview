package com.neonapps.lib.android.noice.rv.adapter.events

import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.item.AdapterItem

interface ItemBindable<V> {
    fun bind(holder : RecyclerView.ViewHolder, position : Int){}

    fun onBound(visitor : V, holder : RecyclerView.ViewHolder, position : Int, eventName : String = "") {}

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {}
}