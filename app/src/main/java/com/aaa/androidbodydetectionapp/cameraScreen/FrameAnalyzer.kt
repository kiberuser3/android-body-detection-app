package com.aaa.androidbodydetectionapp.cameraScreen

import android.util.Size
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import java.lang.Integer.max
import java.lang.Integer.min

@ExperimentalGetImage
class FrameAnalyzer(skeletView: SkeletView): ImageAnalysis.Analyzer {
    val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    val detector = PoseDetection.getClient(options)
    val poseView = skeletView

    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null) {
            val imageForDetector = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
            detector.process(imageForDetector)
                .addOnSuccessListener { detectedPose ->
                    val minSide = min(image.height, image.width)
                    val maxSide = max(image.height, image.width)
                    val targetSize = Size(minSide, maxSide)
                    poseView.setParametres(detectedPose, targetSize)
                    image.close()
                }
                .addOnFailureListener {
                    println("Не удалось обработать сообщение")
                    image.close()
                }
        }
    }
}