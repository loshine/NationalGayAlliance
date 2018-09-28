package xzy.loshine.nga.ui.base

import android.os.Bundle
import xzy.loshine.nga.di.android.DaggerActivity

abstract class EasyActivity(private val layoutResId: Int? = null) : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutResId?.let { setContentView(it) }
    }
}
