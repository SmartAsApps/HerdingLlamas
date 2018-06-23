package de.smartasapps.urbanllamafarmer.nodes

import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3

fun Node.resolveOverlap() {
    val overlappingNode = scene?.overlapTest(this) ?: return
    if (overlappingNode is HeartNode) return
    val direction = Vector3.subtract(worldPosition, overlappingNode.worldPosition)
    direction.y = 0f
    val rejection = 0.1f
    worldPosition = Vector3.add(worldPosition, direction.normalized().scaled(rejection))
}
