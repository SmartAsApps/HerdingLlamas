package de.smartasapps.urbanllamafarmer.nodes

import android.content.Context
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.TransformationSystem
import de.smartasapps.urbanllamafarmer.R
import org.koin.dsl.module.module

class NodeCreator {
    private lateinit var llamaRenderable: ModelRenderable
    private lateinit var fenceRenderable: ModelRenderable
    private lateinit var heartRenderable: ModelRenderable
    private lateinit var transformationSystem: TransformationSystem

    fun init(context: Context, transformationSystem: TransformationSystem) {
        this.transformationSystem = transformationSystem
        ModelRenderable.builder().setSource(context, R.raw.llama).build().thenAccept { llamaRenderable = it }
        ModelRenderable.builder().setSource(context, R.raw.fence).build().thenAccept { fenceRenderable = it }
        ModelRenderable.builder().setSource(context, R.raw.heart).build().thenAccept { heartRenderable = it }
    }

    fun createLlama(): Node {
        val llamaNode = LlamaNode({ createHeart() }, transformationSystem)
        llamaNode.renderable = llamaRenderable
        return llamaNode
    }

    fun createFence(): Node {
        val fenceNode = FenceNode(transformationSystem)
        fenceNode.renderable = fenceRenderable
        return fenceNode
    }

    private fun createHeart(): HeartNode {
        val heartNode = HeartNode()
        heartNode.renderable = heartRenderable
        return heartNode
    }
}

val nodeCreateModule = module {
    single { NodeCreator() }
}
