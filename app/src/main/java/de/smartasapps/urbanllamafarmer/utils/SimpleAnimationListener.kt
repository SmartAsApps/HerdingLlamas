package de.smartasapps.urbanllamafarmer.utils

import android.animation.Animator

open class SimpleAnimatorListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator) {}

    override fun onAnimationEnd(animation: Animator) {}

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationStart(animation: Animator) {}

}
