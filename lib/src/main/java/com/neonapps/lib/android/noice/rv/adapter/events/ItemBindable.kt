package com.neonapps.lib.android.noice.rv.adapter.events

import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter

interface ItemBindable<V> {
    fun bind(adapter : AppSimpleAdapter<V>, holder : RecyclerView.ViewHolder, position : Int)
}