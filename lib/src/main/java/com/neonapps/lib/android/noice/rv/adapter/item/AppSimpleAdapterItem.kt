package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.events.ItemBindable
import com.neonapps.lib.android.noice.rv.adapter.events.ItemClickable

interface AppSimpleAdapterItem<T> : ItemClickable<T>, ItemBindable<T> {
    val type : Int
}