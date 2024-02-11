package am.hikemelikyan.specialforcypress.mvvm

import am.hikemelikyan.specialforcypress.shared.view.IBaseView
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), IBaseView {

    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding

    protected abstract val inflate: (LayoutInflater) -> VB

    protected abstract fun initView(binding: VB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(_binding.root)
        initView(_binding)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(resId: Int) {
        showToast(getString(resId))
    }

}