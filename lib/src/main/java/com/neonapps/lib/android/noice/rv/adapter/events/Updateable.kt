package com.neonapps.lib.android.noice.rv.adapter.events

interface Updateable {
    fun update(payload : Any) : Boolean = true
}