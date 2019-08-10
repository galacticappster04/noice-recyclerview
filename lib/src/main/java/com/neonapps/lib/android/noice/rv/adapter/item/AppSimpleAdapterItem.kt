package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter

abstract class AppSimpleAdapterItem<V> : AdapterItem<V> {
    override fun getItemId(): Long = AppSimpleAdapter.getItemId()
}