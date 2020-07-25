package com.neonapps.lib.android.noice.rv.adapter.events

import androidx.recyclerview.widget.RecyclerView

interface ItemBindable {
    fun bind(holder : RecyclerView.ViewHolder, position : Int){}

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {}
}