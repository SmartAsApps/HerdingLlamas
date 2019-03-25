package de.smartasapps.urbanllamafarmer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.ux.ArFragment
import de.smartasapps.urbanllamafarmer.logic.LlamaLogic
import de.smartasapps.urbanllamafarmer.nodes.NodeCreator
import de.smartasapps.urbanllamafarmer.placement.ObjectPlacement
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment

    private val objectPlacement by inject<ObjectPlacement>()
    private val nodeCreator by inject<NodeCreator>()
    private val llamaLogic by inject<LlamaLogic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        objectSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) objectPlacement.activateLlamas() else objectPlacement.activateFence()
        }

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        nodeCreator.init(this, arFragment.transformationSystem)
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val scene = arFragment.arSceneView?.scene ?: return@setOnTapArPlaneListener
            val node = objectPlacement.placeObject(hitResult)
            scene.addChild(node)
        }
        llamaLogic.init(arFragment.arSceneView.scene)
    }
}
