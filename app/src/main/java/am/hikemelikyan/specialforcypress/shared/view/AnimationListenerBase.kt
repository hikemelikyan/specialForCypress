package am.hikemelikyan.specialforcypress.shared.view

import android.view.animation.Animation

/**
 * Simple [Animation.AnimationListener] that allows to implement only necessary methods
 */
open class AnimationListenerBase: Animation.AnimationListener {
    override fun onAnimationRepeat(p0: Animation?) {}

    override fun onAnimationEnd(p0: Animation?) { }

    override fun onAnimationStart(p0: Animation?) { }
}