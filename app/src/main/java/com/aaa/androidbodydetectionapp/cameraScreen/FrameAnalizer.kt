package com.aaa.androidbodydetectionapp.cameraScreen

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

class FrameAnalyzer: ImageAnalysis.Analyzer {
    val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    val detector = PoseDetection.getClient(options)

    override fun analyze(image: ImageProxy) {
        println("я роботаю")
        image.close()
    }

}