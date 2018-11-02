package xzy.loshine.nga.ui.reply

import android.os.Bundle
import android.view.View
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_reply.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import xzy.loshine.nga.ui.textcolorpalette.TextColorPaletteDialogFragment
import javax.inject.Inject

@ActivityScoped
class ReplyFragment @Inject constructor() : BaseFragment(R.layout.fragment_reply) {

    @Inject
    lateinit var textColorDialogProvider: Lazy<TextColorPaletteDialogFragment>

    override fun bindViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        action_format_color.setOnClickListener {
            textColorDialogProvider.get().show(childFragmentManager, null)
        }
    }
}