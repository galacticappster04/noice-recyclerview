package com.neonapps.lib.android.noice.rv.adapter.events

import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder

interface Provider {
    fun create() : TypedHolder.Provider
}