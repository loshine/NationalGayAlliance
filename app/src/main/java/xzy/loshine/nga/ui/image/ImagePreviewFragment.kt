package xzy.loshine.nga.ui.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_topic.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class ImagePreviewFragment @Inject constructor() : BaseFragment(R.layout.fragment_image_preview) {

    @Inject
    lateinit var adapter: ImagePreviewAdapter
    @JvmField
    @field:Named("index")
    @Inject
    var currentIndex: Int = 0

    override fun bindViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleHideBar()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        recycler_view.scrollToPosition(currentIndex)
        PagerSnapHelper().attachToRecyclerView(recycler_view)
    }

    private fun toggleHideBar() {
        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        val uiOptions = activity!!.window.decorView.systemUiVisibility
        var newUiOptions = uiOptions
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        val isImmersiveModeEnabled = uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY == uiOptions
        if (isImmersiveModeEnabled) {
            Logger.i("Turning immersive mode mode off. ")
        } else {
            Logger.i("Turning immersive mode mode on.")
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        // Status bar hiding: Backwards compatible to Jellybean
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        activity!!.window.decorView.systemUiVisibility = newUiOptions
        //END_INCLUDE (set_ui_flags)
    }
}