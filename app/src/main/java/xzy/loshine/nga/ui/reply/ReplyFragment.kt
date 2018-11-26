package xzy.loshine.nga.ui.reply

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_reply.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import xzy.loshine.nga.ui.textcolorpalette.TextColorPaletteDialogFragment
import javax.inject.Inject

@ActivityScoped
class ReplyFragment @Inject constructor() : BaseFragment(R.layout.fragment_reply), TextColorPaletteDialogFragment.Callback {

    @Inject
    lateinit var textColorDialogProvider: Lazy<TextColorPaletteDialogFragment>

    override fun bindViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        action_format_color.setOnClickListener {
            val dialogFragment = textColorDialogProvider.get()
            if (dialogFragment.targetFragment == null) {
                dialogFragment.setTargetFragment(this, 0)
            }
            dialogFragment.show(fragmentManager, null)
        }
    }

    override fun onColorSelected(colorPair: Pair<String, Int>) {
        edit_text.append("[color=${colorPair.first}][/color]")
        edit_text.setSelection(edit_text.text.length - 8)
        KeyboardUtils.showSoftInput(edit_text)
    }
}