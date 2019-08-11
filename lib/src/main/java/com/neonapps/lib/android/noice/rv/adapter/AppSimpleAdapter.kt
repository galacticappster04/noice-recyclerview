package com.neonapps.lib.android.noice.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AdapterItem

class AppSimpleAdapter<V> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val prototypes : HashMap<Int, TypedHolder.Prototype> = hashMapOf()

    private var _content : MutableList<AdapterItem<V>> = mutableListOf()

    var multiSelectionEnabled : Boolean = false
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var notifyCurrentSelectedOnChange : Boolean = false
    var visitor : V? = null

    val content : List<AdapterItem<V>>
        get() = _content

    val selectedItems : Map<Int, AdapterItem<V>>
        get() {
            val items : MutableMap<Int, AdapterItem<V>> = mutableMapOf()

            content.withIndex().forEach{
                if(it.value.isSelected)
                    items.put(it.index, it.value)
            }

            return items
        }

    var currentSelected : Int = -1
        set(value){

            previousSelected = if(field == -1) value else field
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

    fun dispatchTouchEvent(item : AdapterItem<V>, position : Int, eventName : String) {
        if(visitor != null) {
            item.click(visitor!!, position, eventName)
        }
    }

    fun dispatchBindEvent(item : AdapterItem<V>, viewHolder : RecyclerView.ViewHolder, position : Int, eventName: String) {
        if(visitor != null)
            item.onBound(visitor!!, viewHolder, position, eventName)
    }

    fun setContent(items : MutableList<AdapterItem<V>>) {

        for(item in items){
            if(!prototypes.containsKey(item.type))
                prototypes[item.type] = item.createPrototype()
        }

        this._content = items
        notifyDataSetChanged()
    }

    fun addItem(position: Int = this@AppSimpleAdapter.content.size - 1, item: AdapterItem<V>) {
        if(!prototypes.containsKey(item.type))
            prototypes[item.type] = item.createPrototype()

        _content.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(vararg position : Int) {
        val selectedPositions = position.filter { it > 0 || it < content.size }
        val selectedItems = _content.withIndex().filter { it.index in selectedPositions }.map { it.value }

        selectedItems.forEach {
            _content.remove(it)
        }

        selectedPositions.forEach {
            notifyItemRemoved(it)
        }
    }

    fun removeViewPrototype(type : Int) {
        prototypes.remove(type)
    }

    fun removeAllPrototype(type : Int) {
        prototypes.clear()
    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int = content[position].type

    override fun getItemId(position: Int): Long = content[position].getItemId()

    companion object{

        private var idGenerator : Long = 0

        fun getItemId() : Long = idGenerator++
    }
}