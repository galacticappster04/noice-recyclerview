package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.ReusableAdapter
import com.neonapps.lib.android.noice.rv.adapter.events.*

interface AdapterItem<T> : ItemBindable<T>, MultiSelectable<T>, Updateable, Provider {
    val type : Int

    var adapter : ReusableAdapter<T>

    fun getItemId() : Long
}