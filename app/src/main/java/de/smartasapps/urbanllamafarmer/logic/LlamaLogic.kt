package de.smartasapps.urbanllamafarmer.logic

import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import de.smartasapps.urbanllamafarmer.nodes.LlamaNode
import java.util.*

class LlamaLogic : Scene.OnUpdateListener {
    private val walkingDirections = mutableMapOf<LlamaNode, Vector3>()
    private val random = Random()
    private lateinit var scene: Scene
    private val walkUntilMap = mutableMapOf<LlamaNode, Float>()


    fun init(scene: Scene) {
        this.scene = scene
        scene.addOnUpdateListener(this)
    }

    override fun onUpdate(frameTime: FrameTime) {
        moveLlamas(frameTime)
    }

    private fun moveLlamas(frameTime: FrameTime) {
        scene.callOnHierarchy {
            val llamaNode = it as? LlamaNode ?: return@callOnHierarchy
            walkingDirections.putIfAbsent(llamaNode, randomWalkingDirection())
            if (!walkUntilMap.containsKey(llamaNode)) {
                maybeStartWalking(frameTime.startSeconds, llamaNode)
            }
            val endTime = walkUntilMap[llamaNode] ?: return@callOnHierarchy
            if (endTime < frameTime.startSeconds) {
                walkUntilMap.remove(llamaNode)
                return@callOnHierarchy
            }
            val direction = walkingDirections[llamaNode] ?: return@callOnHierarchy
            llamaNode.localPosition = Vector3.add(llamaNode.localPosition, direction.scaled(llamaNode.walkingSpeed * frameTime.deltaSeconds))
            llamaNode.localRotation = Quaternion.rotationBetweenVectors(Vector3(0f,0f,1f), direction)
        }
    }

    private fun randomWalkingDirection(): Vector3 {
        val vector3 = Vector3()
        vector3.x = randomSignedFloat()
        vector3.y = 0f
        vector3.z = randomSignedFloat()
        return vector3.normalized()
    }

    private fun maybeStartWalking(currentTime: Float, node: LlamaNode) {
        if (random.nextDouble() > 0.98) return
        walkUntilMap[node] = currentTime + random.nextFloat() * 30
    }

    private fun randomSignedFloat() = random.nextFloat() * if (random.nextBoolean()) 1 else -1
}