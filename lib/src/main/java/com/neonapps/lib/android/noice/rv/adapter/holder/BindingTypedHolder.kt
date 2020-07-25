package com.neonapps.lib.android.noice.rv.adapter.holder

import androidx.databinding.ViewDataBinding

open class BindingTypedHolder<out B : ViewDataBinding>(val binding : B) : TypedHolder(binding.root)

