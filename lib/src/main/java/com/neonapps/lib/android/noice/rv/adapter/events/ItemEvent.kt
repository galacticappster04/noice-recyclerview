package com.neonapps.lib.android.noice.rv.adapter.events

interface ItemEvent<V> {
    fun onEvent(visitor : V, position : Int, eventName: String){}
}