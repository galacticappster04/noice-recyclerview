package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.events.ItemBindable
import com.neonapps.lib.android.noice.rv.adapter.events.ItemClickable
import com.neonapps.lib.android.noice.rv.adapter.events.MultiSelectable
import com.neonapps.lib.android.noice.rv.adapter.events.PrototypeProvider

interface AppSimpleAdapterItem<T> : ItemClickable<T>, ItemBindable<T>, MultiSelectable<T>, PrototypeProvider {
    val type : Int

    fun getId(position : Int) : Long
}