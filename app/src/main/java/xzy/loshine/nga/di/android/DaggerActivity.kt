package xzy.loshine.nga.di.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.internal.Beta
import javax.inject.Inject
import androidx.fragment.app.Fragment as SupportFragment

/**
 * An [AppCompatActivity] that injects its members in [.onCreate] and can be
 * used to inject `Fragment`s attached to it.
 */
@Beta
abstract class DaggerActivity : AppCompatActivity(), HasFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<SupportFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<SupportFragment> {
        return supportFragmentInjector
    }
}