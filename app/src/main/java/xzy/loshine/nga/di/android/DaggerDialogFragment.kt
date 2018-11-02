package xzy.loshine.nga.di.android

import android.content.Context
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.internal.Beta
import javax.inject.Inject

/**
 * An {@link AppCompatDialogFragment} that injects its members in {@link #onAttach(Context)} and can
 * be used to inject child {@link Fragment}s attached to it. Note that when this fragment gets
 * reattached, its members will be injected again.
 */
@Beta
abstract class DaggerDialogFragment : AppCompatDialogFragment(), HasFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }
}
