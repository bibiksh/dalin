package com.seanlab.dalin.mlkit.adapter

import android.app.Activity

import android.content.Context
import android.content.Intent
//import android.support.v7.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seanlab.dalin.mlkit.activity.*
import com.seanlab.dalin.mlkit.model.PojoApi
import com.seanlab.dalin.mlkit.R
//sean
import com.seanlab.dalin.mlkit.md.kotlin.*

import kotlinx.android.synthetic.main.item_row_home.view.*

class HomeAdapter(private val apiList: List<PojoApi>) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private lateinit var context: Context

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val currItem = apiList[position]
        with(holder.itemView) {
            tViewApiName.text = currItem.title
            tViewApiDesc.text = currItem.desc
            iViewApi.setImageResource(currItem.imageId)
            cViewHome.setOnClickListener {

                when (currItem.id) {
                    //sean
                    0 -> context.startActivity(Intent(context, LiveObjectDetectionActivity::class.java))
                    1 -> context.startActivity(Intent(context, LiveObjectDetectionActivity::class.java))
                    2 -> context.startActivity(Intent(context, LiveBarcodeScanningActivity::class.java))
                    3 -> context.startActivity(Intent(context, LiveObjectDetectionActivity::class.java))
                    4 -> context.startActivity(Intent(context, LiveObjectDetectionActivity::class.java))


                    /*
                    0 -> context.startActivity(Intent(context, ImageLabelActivity::class.java))
                    1 -> context.startActivity(Intent(context, CardScannerActivity::class.java))
                    2 -> context.startActivity(Intent(context, BarCodeReaderActivity::class.java))
                    3 -> context.startActivity(Intent(context, LandmarkDetectorActivity::class.java))
                    4 -> context.startActivity(Intent(context, FaceDetectionActivity::class.java))
                    */
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        context = parent.context
        return HomeHolder(LayoutInflater.from(context).inflate(R.layout.item_row_home, parent, false))
    }

    override fun getItemCount() = apiList.size
}