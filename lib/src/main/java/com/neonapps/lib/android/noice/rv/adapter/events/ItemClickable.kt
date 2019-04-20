package com.neonapps.lib.android.noice.rv.adapter.events

interface ItemClickable<V> {
    fun click(visitor : V, position : Int, eventName: String){}
}