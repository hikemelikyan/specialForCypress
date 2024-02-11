package am.hikemelikyan.specialforcypress.shared.view

import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation


/**
 * Splash animation logic is here
 */
class SplashAnimator(
    private var target: SplashAnimatorTarget?,
    private val animationDuration: Long,
    actionToExecuteWhenCompleted: (() -> Unit)
) {
    private val startAnimationFactor = 1f
    private val endPulseAnimationFactor = 1.2f
    private val endFinalAnimationFactor = 50f

    private var isAnimationInProgress = false

    private var mActionToExecuteWhenCompleted: (() -> Unit)? = actionToExecuteWhenCompleted

    fun startAnimation(context: Context) {
        if (isAnimationInProgress) {
            return
        }

        isAnimationInProgress = true

        val animatedView = target?.getAnimatedView()!!

        val comboAnimation = AnimationSet(true)

        // Fade animation
        AlphaAnimation(0f, 1f).apply {
            duration = animationDuration
            repeatCount = 0
            interpolator = LinearInterpolator()
            fillAfter = true

            setAnimationListener(object : AnimationListenerBase() {
                override fun onAnimationStart(p0: Animation?) {
                    animatedView.visibility = View.VISIBLE
                }
            })

            comboAnimation.addAnimation(this)
        }

        // Pulse animation
        createPulseAnimation(endPulseAnimationFactor).apply {
            duration = animationDuration
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
            interpolator = LinearInterpolator()

            comboAnimation.addAnimation(this)
        }

        animatedView.startAnimation(comboAnimation)
    }

    fun completeAnimation() {
        if (!isAnimationInProgress) {
            return
        }

        val animatedView = target?.getAnimatedView()!!

        animatedView.clearAnimation()

        val comboAnimation = AnimationSet(true)

        // Fade animation
        AlphaAnimation(1f, 0f).apply {
            duration = animationDuration
            repeatCount = 0
            interpolator = LinearInterpolator()
            fillAfter = true

            setAnimationListener(object : AnimationListenerBase() {
                override fun onAnimationStart(p0: Animation?) {
                    animatedView.visibility = View.INVISIBLE
                }
            })

            comboAnimation.addAnimation(this)
        }

        // Final stretching animation
        createPulseAnimation(endFinalAnimationFactor).apply {
            duration = animationDuration
            repeatCount = 0
            interpolator = LinearInterpolator()
            fillAfter = true

            setAnimationListener(object : AnimationListenerBase() {
                override fun onAnimationEnd(p0: Animation?) {
                    isAnimationInProgress = false

                    animatedView.visibility = View.GONE

                    mActionToExecuteWhenCompleted?.invoke()
                }
            })

            comboAnimation.addAnimation(this)
        }

        animatedView.startAnimation(comboAnimation)
    }

    fun clear() {
        target?.getAnimatedView()?.clearAnimation()

        target = null
        mActionToExecuteWhenCompleted = null
    }

    private fun createPulseAnimation(endFactor: Float) =
        ScaleAnimation(
            startAnimationFactor,
            endFactor,
            startAnimationFactor,
            endFactor,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
}