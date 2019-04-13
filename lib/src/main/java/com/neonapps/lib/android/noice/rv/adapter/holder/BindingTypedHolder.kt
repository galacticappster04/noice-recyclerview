package com.neonapps.lib.android.noice.rv.adapter.holder

import androidx.databinding.ViewDataBinding

open class BindingTypedHolder<out B : ViewDataBinding>(val binding : B, type : Int) : TypedHolder(binding.root, type)

