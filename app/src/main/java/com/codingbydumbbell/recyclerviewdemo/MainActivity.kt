package com.codingbydumbbell.recyclerviewdemo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item1.view.*
import kotlinx.android.synthetic.main.item2.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(false)
        recycler.adapter = MyAdapter()
    }

    class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val dataSet = listOf(
            MyData(1, text = "ABC"),
            MyData(2, img = R.drawable.pic_1),
            MyData(1, text = "CODE BY DUMBBELL"),
            MyData(1, text = "Rick"),
            MyData(2, img = R.drawable.pic_2)
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(
                    when (viewType) {
                        1 -> R.layout.item1
                        2 -> R.layout.item2
                        else -> -1
                    }, parent, false
                )
            return if (viewType == 1) ViewHolderItem1(view) else ViewHolderItem2(view)
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (dataSet[position].viewType == 1) (holder as ViewHolderItem1).bindView(dataSet[position])
            else (holder as ViewHolderItem2).bindView(dataSet[position])

        }

        override fun getItemViewType(position: Int): Int {
            return dataSet[position].viewType
        }
    }

    class ViewHolderItem1(view: View) : RecyclerView.ViewHolder(view) {
        var text = view.textView!!

        fun bindView(myData: MyData) {
            text.text = myData.text
        }
    }

    class ViewHolderItem2(view: View) : RecyclerView.ViewHolder(view) {
        var img = view.imageView!!
        var context = view.context!!

        fun bindView(myData: MyData) {
            img.setImageDrawable(context.resources.getDrawable(myData.img, context.theme))
        }
    }
}

data class MyData(val viewType: Int, val text: String = "", val img: Int = -1)
