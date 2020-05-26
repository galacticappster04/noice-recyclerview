package com.neonapps.lib.android.noice.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.item.AdapterItem
import com.neonapps.lib.android.noice.sample.entities.DoubleEntity
import com.neonapps.lib.android.noice.sample.entities.StringEntity
import com.neonapps.lib.android.noice.sample.items.DoubleItem
import com.neonapps.lib.android.noice.sample.items.StringItem
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor
import kotlinx.android.synthetic.main.activity_main.*

// TODO Create sample for this project
class MainActivity : AppCompatActivity(), SampleItemVisitor {

    private val adapter : AppSimpleAdapter<SampleItemVisitor> = AppSimpleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items : MutableList<AdapterItem<SampleItemVisitor>> = mutableListOf()
        items.add(DoubleItem(DoubleEntity((1.0))))
        items.add(DoubleItem(DoubleEntity((2.0))))
        items.add(DoubleItem(DoubleEntity((3.0))))
        items.add(DoubleItem(DoubleEntity((4.0))))
        items.add(DoubleItem(DoubleEntity((5.0))))
        items.add(DoubleItem(DoubleEntity((6.0))))
        items.add(DoubleItem(DoubleEntity((7.0))))
        items.add(DoubleItem(DoubleEntity((8.0))))
        items.add(DoubleItem(DoubleEntity((9.0))))
        items.add(DoubleItem(DoubleEntity((10.0))))
        items.add(DoubleItem(DoubleEntity((11.0))))
        items.add(DoubleItem(DoubleEntity((13.0))))
        items.add(DoubleItem(DoubleEntity((14.0))))
        items.add(DoubleItem(DoubleEntity((15.0))))
        items.add(DoubleItem(DoubleEntity((16.0))))
        items.add(DoubleItem(DoubleEntity((17.0))))
        items.add(DoubleItem(DoubleEntity((18.0))))
        items.add(DoubleItem(DoubleEntity((19.0))))
        items.add(DoubleItem(DoubleEntity((20.0))))
        items.add(DoubleItem(DoubleEntity((21.0))))
        items.add(DoubleItem(DoubleEntity((22.0))))
        items.add(DoubleItem(DoubleEntity((23.0))))
        items.add(DoubleItem(DoubleEntity((24.0))))
        items.add(DoubleItem(DoubleEntity((25.0))))
        items.add(DoubleItem(DoubleEntity((26.0))))
        items.add(DoubleItem(DoubleEntity((27.0))))
        items.add(DoubleItem(DoubleEntity((28.0))))
        items.add(DoubleItem(DoubleEntity((29.0))))
        items.add(DoubleItem(DoubleEntity((30.0))))
        items.add(DoubleItem(DoubleEntity((31.0))))
        items.add(DoubleItem(DoubleEntity((32.0))))
        items.add(DoubleItem(DoubleEntity((33.0))))
        items.add(DoubleItem(DoubleEntity((34.0))))
        items.add(DoubleItem(DoubleEntity((35.0))))
        items.add(DoubleItem(DoubleEntity((36.0))))
        items.add(DoubleItem(DoubleEntity((37.0))))
        items.add(DoubleItem(DoubleEntity((38.0))))
        items.add(DoubleItem(DoubleEntity((39.0))))
        items.add(DoubleItem(DoubleEntity((40.0))))
        items.add(DoubleItem(DoubleEntity((41.0))))
        items.add(DoubleItem(DoubleEntity((42.0))))
        items.add(DoubleItem(DoubleEntity((43.0))))
        items.add(DoubleItem(DoubleEntity((44.0))))

        recyclerview_main.adapter = adapter
        recyclerview_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        relativelayout_delete.setOnClickListener {
            val selectedItems = adapter.selectedItems
            adapter.removeItem(*selectedItems.map { it.key }.toIntArray())
            adapter.multiSelectionEnabled = false
        }

        adapter.visitor = this
        adapter.notifyCurrentSelectedOnChange = true
        adapter.addContent(*items.toTypedArray())
        adapter.addContent(StringItem(StringEntity("This is a sample entity 1")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 2")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 3")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 4")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 5")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 6")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 7")))
        adapter.addContent(StringItem(StringEntity("This is a sample entity 8")))

        val item = adapter.getItem(adapter.size - 1)
        item?.update(payload = "This new a message, and replaced")
    }

    override fun onBind(position: Int, eventName: String) {

    }

    override fun onStart() {
        super.onStart()
        adapter.onStart()
    }

    override fun onStop() {
        super.onStop()
        adapter.onStop()
    }
}
