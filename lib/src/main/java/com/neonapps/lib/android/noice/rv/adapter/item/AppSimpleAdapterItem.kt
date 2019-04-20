package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.events.ItemBindable
import com.neonapps.lib.android.noice.rv.adapter.events.ItemClickable
import com.neonapps.lib.android.noice.rv.adapter.events.PrototypeProvider

interface AppSimpleAdapterItem<T> : ItemClickable<T>, ItemBindable<T>, PrototypeProvider {
    val type : Int
}