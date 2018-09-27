package xzy.loshine.nga.di.android

import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.internal.Beta


/** Provides an {@link AndroidInjector} of {@link Fragment}s. */
@Beta
interface HasFragmentInjector {

    /** Returns an [AndroidInjector] of [Fragment]s.  */
    fun supportFragmentInjector(): AndroidInjector<Fragment>
}