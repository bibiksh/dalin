// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.seanlab.dalin.mlkit.md.java.cloudtextrecognition;

import android.graphics.Bitmap;
//sean
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.seanlab.dalin.mlkit.md.common.FrameMetadata;
import com.seanlab.dalin.mlkit.md.common.GraphicOverlayLabel;
import com.seanlab.dalin.mlkit.md.java.VisionProcessorBase;

import java.util.List;

/**
 * Processor for the cloud document text detector demo.
 */
public class CloudDocumentTextRecognitionProcessor
        extends VisionProcessorBase<FirebaseVisionDocumentText> {

    private static final String TAG = "CloudDocTextRecProc";

    private final FirebaseVisionDocumentTextRecognizer detector;

    public CloudDocumentTextRecognitionProcessor() {
        super();
        detector = FirebaseVision.getInstance().getCloudDocumentTextRecognizer();
    }

    @Override
    protected Task<FirebaseVisionDocumentText> detectInImage(FirebaseVisionImage image) {
        return detector.processImage(image);
    }

    @Override
    protected void onSuccess(
            @Nullable Bitmap originalCameraImage,
            @NonNull FirebaseVisionDocumentText text,
            @NonNull FrameMetadata frameMetadata,
            @NonNull GraphicOverlayLabel graphicOverlayLabel) {
        graphicOverlayLabel.clear();
        Log.d(TAG, "detected text is: " + text.getText());
        List<FirebaseVisionDocumentText.Block> blocks = text.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionDocumentText.Paragraph> paragraphs = blocks.get(i).getParagraphs();
            for (int j = 0; j < paragraphs.size(); j++) {
                List<FirebaseVisionDocumentText.Word> words = paragraphs.get(j).getWords();
                for (int l = 0; l < words.size(); l++) {
                    List<FirebaseVisionDocumentText.Symbol> symbols = words.get(l).getSymbols();
                    for (int m = 0; m < symbols.size(); m++) {
                        CloudDocumentTextGraphic cloudDocumentTextGraphic =
                                new CloudDocumentTextGraphic(graphicOverlayLabel,
                                symbols.get(m));
                        graphicOverlayLabel.add(cloudDocumentTextGraphic);
                    }
                }
            }
        }
        graphicOverlayLabel.postInvalidate();
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.w(TAG, "Cloud Document Text detection failed." + e);
    }
}
