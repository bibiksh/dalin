/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seanlab.dalin.mlkit.md.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//sean
import com.seanlab.dalin.mlkit.activity.ImageLabelActivity


import com.seanlab.dalin.mlkit.R
import kotlinx.android.synthetic.main.activity_home.*

/** Entry activity to select the detection mode.  */
class MainActivity : AppCompatActivity() {

    private enum class DetectionMode(val imgResId: Int,val titleResId: Int, val subtitleResId: Int) {
        ODT_LIVE_IMAGE(R.drawable.image_labelling,R.string.mode_odt_live_image_title, R.string.mode_odt_live_image_subtitle),
        ODT_LIVE_TEXT(R.drawable.text_recognition,R.string.mode_odt_live_text_title, R.string.mode_odt_live_text_subtitle),
        ODT_LIVE_BARCODE(R.drawable.barcode_scanning,R.string.mode_odt_live_barcode_title, R.string.mode_odt_live_barcode_subtitle),
        ODT_LIVE_LANDMARK(R.drawable.landmark_identification,R.string.mode_odt_live_landmark_title, R.string.mode_odt_live_landmark_subtitle),
        ODT_LIVE_FACE(R.drawable.face_detection,R.string.mode_odt_live_face_title, R.string.mode_odt_live_face_subtitle)
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)



        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setContentView(R.layout.activity_main_google)

        findViewById<RecyclerView>(R.id.mode_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ModeItemAdapter(DetectionMode.values())
        }

        //

    }

    override fun onResume() {
        super.onResume()
        if (!Utils.allPermissionsGranted(this)) {
            Utils.requestRuntimePermissions(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Utils.REQUEST_CODE_PHOTO_LIBRARY &&
                resultCode == Activity.RESULT_OK &&
                data != null) {
            val intent = Intent(this, StaticObjectDetectionActivity::class.java)
            intent.data = data.data
            startActivity(intent)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private inner class ModeItemAdapter internal constructor(private val detectionModes: Array<DetectionMode>) :
        RecyclerView.Adapter<ModeItemAdapter.ModeItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeItemViewHolder {
            return ModeItemViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.detection_mode_item, parent, false
                            )
            )
        }

        override fun onBindViewHolder(modeItemViewHolder: ModeItemViewHolder, position: Int) =
            modeItemViewHolder.bindDetectionMode(detectionModes[position])

        override fun getItemCount(): Int = detectionModes.size

        private inner class ModeItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
            //sean
            private val titleImage: ImageView = view.findViewById(R.id.iViewApi)

            private val titleView: TextView = view.findViewById(R.id.mode_title)
            private val subtitleView: TextView = view.findViewById(R.id.mode_subtitle)

            internal fun bindDetectionMode(detectionMode: DetectionMode) {
                //sean
                titleImage.setImageResource(detectionMode.imgResId)

                titleView.setText(detectionMode.titleResId)
                subtitleView.setText(detectionMode.subtitleResId)
                itemView.setOnClickListener {
                    val activity = this@MainActivity
                    when (detectionMode) {

                        DetectionMode.ODT_LIVE_IMAGE ->
                            activity.startActivity(Intent(activity, LiveObjectDetectionActivity::class.java))
                        DetectionMode.ODT_LIVE_TEXT ->Utils.openImagePicker(activity)
                        DetectionMode.ODT_LIVE_BARCODE ->
                            activity.startActivity(Intent(activity, LiveBarcodeScanningActivity::class.java))
                        DetectionMode.ODT_LIVE_LANDMARK ->
                            activity.startActivity(Intent(activity, LiveObjectDetectionActivity::class.java))
                        DetectionMode.ODT_LIVE_FACE ->
                            activity.startActivity(Intent(activity, LiveObjectDetectionActivity::class.java))
                     /*
                        DetectionMode.ODT_LIVE ->
                            activity.startActivity(Intent(activity, LiveObjectDetectionActivity::class.java))
                        DetectionMode.ODT_STATIC -> Utils.openImagePicker(activity)
                        DetectionMode.BARCODE_LIVE ->
                            activity.startActivity(Intent(activity, LiveBarcodeScanningActivity::class.java))
                     */
                    }
                }
            }
        }
    }
}
