package am.hikemelikyan.specialforcypress.ui.welcomeActivity

import am.hikemelikyan.specialforcypress.mvvm.BaseActivity
import am.hikemelikyan.specialforcypress.databinding.ActivityWelcomeBinding
import am.hikemelikyan.specialforcypress.shared.view.SplashAnimator
import am.hikemelikyan.specialforcypress.ui.mainActivity.MainActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import am.hikemelikyan.specialforcypress.shared.view.SplashAnimatorTarget
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(), SplashAnimatorTarget {
    private lateinit var splashAnimator: SplashAnimator

    override val inflate: (LayoutInflater) -> ActivityWelcomeBinding
        get() = ActivityWelcomeBinding::inflate

    override fun initView(binding: ActivityWelcomeBinding) {
        splashAnimator = SplashAnimator(this, 500L,::launchMainActivity)
        splashAnimator.startAnimation(this)
        GlobalScope.launch {
            delay(3000L)
            splashAnimator.completeAnimation()
        }
    }

    override fun getAnimatedView(): View = mBinding.splashIcon

    override fun getRootView(): View = mBinding.root

    private fun launchMainActivity(){
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        })
        finish()
    }
}