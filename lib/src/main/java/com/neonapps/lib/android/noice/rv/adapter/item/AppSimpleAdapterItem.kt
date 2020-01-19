package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter

abstract class AppSimpleAdapterItem<V> : AdapterItem<V> {

    protected val _itemId : Long = AppSimpleAdapter.getItemId()

    override lateinit var adapter : AppSimpleAdapter<V>

    override fun getItemId(): Long = _itemId
}