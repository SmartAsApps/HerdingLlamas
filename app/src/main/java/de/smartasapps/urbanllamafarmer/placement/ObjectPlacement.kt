package de.smartasapps.urbanllamafarmer.placement

import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import de.smartasapps.urbanllamafarmer.nodes.NodeCreator

class ObjectPlacement(private val nodeCreator: NodeCreator) {
    private var placementType: ObjectPlacementType = FencePlacement

    fun placeObject(hitResult: HitResult): Node {
        val anchor = hitResult.createAnchor()
        val anchorNode = AnchorNode(anchor)
        placementType.createNode(nodeCreator).setParent(anchorNode)
        return anchorNode
    }

    fun activateFence() {
        placementType = FencePlacement
    }

    fun activateLlamas() {
        placementType = LlamaPlacement
    }
}

private sealed class ObjectPlacementType {
    abstract fun createNode(nodeCreator: NodeCreator): Node
}

private object LlamaPlacement : ObjectPlacementType() {
    override fun createNode(nodeCreator: NodeCreator): Node = nodeCreator.createLlama()
}

private object FencePlacement : ObjectPlacementType() {
    override fun createNode(nodeCreator: NodeCreator): Node = nodeCreator.createFence()
}
