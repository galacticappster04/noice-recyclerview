package com.neonapps.lib.android.noice.rv.adapter.events

import androidx.recyclerview.widget.RecyclerView

interface ItemBindable<V> {
    fun bind(holder : RecyclerView.ViewHolder, visitor : V?, position : Int){}

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {}
}