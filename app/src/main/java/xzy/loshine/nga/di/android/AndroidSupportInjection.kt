package xzy.loshine.nga.di.android

import android.util.Log
import android.util.Log.DEBUG
import androidx.fragment.app.Fragment
import dagger.android.HasFragmentInjector
import dagger.internal.Preconditions.checkNotNull

object AndroidSupportInjection {

    private const val TAG = "dagger.android.support"

    fun inject(fragment: Fragment) {
        checkNotNull(fragment, "fragment")
        val hasSupportFragmentInjector = findHasFragmentInjector(fragment)
        if (Log.isLoggable(TAG, DEBUG)) {
            Log.d(TAG, String.format(
                    "An injector for %s was found in %s",
                    fragment::class.java.canonicalName,
                    hasSupportFragmentInjector.javaClass.canonicalName))
        }
    }

    private fun findHasFragmentInjector(fragment: Fragment): HasFragmentInjector {
        var parentFragment = fragment.parentFragment
        while (parentFragment != null) {
            if (parentFragment is HasFragmentInjector) {
                return parentFragment
            }
            parentFragment = parentFragment.parentFragment
        }
        val activity = fragment.activity
        if (activity is HasFragmentInjector) {
            return activity
        }
        if (activity?.application is HasFragmentInjector) {
            return activity.application as HasFragmentInjector
        }
        throw IllegalArgumentException(
                String.format("No injector was found for %s", fragment::class.java.canonicalName))
    }
}