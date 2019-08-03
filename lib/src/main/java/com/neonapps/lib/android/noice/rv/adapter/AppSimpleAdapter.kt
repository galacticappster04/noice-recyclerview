package com.neonapps.lib.android.noice.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem

class AppSimpleAdapter<V> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val prototypes : HashMap<Int, TypedHolder.Prototype> = hashMapOf()

    private var _content : MutableList<AppSimpleAdapterItem<V>> = mutableListOf()

    var notifyCurrentSelectedOnChange : Boolean = false
    var visitor : V? = null

    val content : List<AppSimpleAdapterItem<V>>
        get() = _content

    var currentSelected : Int = -1
        set(value){
            previousSelected = field
            field = value

            if(notifyCurrentSelectedOnChange)
                notifyItemChanged(currentSelected)

            notifyItemChanged(previousSelected)
        }

    private var previousSelected : Int = -1
    private var linearLayoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(!prototypes.containsKey(viewType)) {
            throw Error("There is no available viewholder prototype for type= $viewType")
        }

        if(linearLayoutInflater == null)
            linearLayoutInflater = LayoutInflater.from(parent.context)

        return prototypes[viewType]?.create(linearLayoutInflater!!, parent, false)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content[position].bind(this, holder, position)
    }

    fun dispatchTouchEvent(item : AppSimpleAdapterItem<V>, position : Int, eventName : String) {
        if(visitor != null)
            item.click(visitor!!, position, eventName)
    }

    fun dispatchBindEvent(item : AppSimpleAdapterItem<V>, viewHolder : RecyclerView.ViewHolder, position : Int, eventName: String) {
        if(visitor != null)
            item.onBound(visitor!!, viewHolder, item, position, eventName)
    }

    fun setContent(items : MutableList<AppSimpleAdapterItem<V>>) {

        for(item in items){
            if(!prototypes.containsKey(item.type))
                prototypes[item.type] = item.createPrototype()
        }

        this._content = items
        notifyDataSetChanged()
    }

    fun addItem(position: Int = this@AppSimpleAdapter.content.size - 1, item: AppSimpleAdapterItem<V>) {
        if(!prototypes.containsKey(item.type))
            prototypes[item.type] = item.createPrototype()

        _content.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(position : Int) {
        if(position < 0 || position >= content.size)
            return

        _content.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeViewPrototype(type : Int) {
        prototypes.remove(type)
    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int = content[position].type

    override fun getItemId(position: Int): Long = content[position].getId(position)
}