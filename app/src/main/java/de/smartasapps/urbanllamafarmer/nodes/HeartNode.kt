package de.smartasapps.urbanllamafarmer.nodes

import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3

class HeartNode : Node() {
    override fun onUpdate(frameTime: FrameTime) {
        super.onUpdate(frameTime)
        localRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), (frameTime.startSeconds*500) % 360)
    }
}
