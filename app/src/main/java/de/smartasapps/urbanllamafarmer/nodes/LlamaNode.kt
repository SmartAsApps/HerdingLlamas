package de.smartasapps.urbanllamafarmer.nodes

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.animation.OvershootInterpolator
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.HitTestResult
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.math.Vector3Evaluator
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem
import de.smartasapps.urbanllamafarmer.utils.SimpleAnimatorListener
import java.util.*

class LlamaNode constructor(private val heartProvider: () -> HeartNode, transformationSystem: TransformationSystem) : TransformableNode(transformationSystem) {

    init {
        scaleController.isEnabled = false
    }

    override fun onUpdate(frameTime: FrameTime?) {
        super.onUpdate(frameTime)
        resolveOverlap()
    }

    val walkingSpeed: Float = Random().nextFloat()

    override fun onTap(hitTestResult: HitTestResult?, motionEvent: MotionEvent?) {
        startPetAnimation()
    }

    private fun startPetAnimation() {
        val heartNode = heartProvider()
        addChild(heartNode)
        heartNode.localPosition = Vector3(0f, 1.5f, 0f)
        heartNode.localScale = Vector3(2f, 2f, 2f)
        val animator = createAnimator()
        animator.target = heartNode
        animator.addListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator) {
                removeChild(heartNode)
            }
        })
        animator.start()
    }

    private fun createAnimator(): ObjectAnimator {
        val animator = ObjectAnimator()
        animator.setObjectValues(Vector3(0f, 1.5f, 0f), Vector3(0f, 2.5f, 0f))
        animator.propertyName = "localPosition"
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.interpolator = OvershootInterpolator()
        animator.duration = 750
        animator.repeatCount = 1
        animator.setEvaluator(Vector3Evaluator())
        return animator
    }
}
