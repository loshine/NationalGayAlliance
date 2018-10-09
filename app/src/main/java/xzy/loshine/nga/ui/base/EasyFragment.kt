package xzy.loshine.nga.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xzy.loshine.nga.di.android.DaggerFragment

abstract class EasyFragment(private val layoutResId: Int? = null) : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (layoutResId == null) return null
        return inflater.inflate(layoutResId, container, false)
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}