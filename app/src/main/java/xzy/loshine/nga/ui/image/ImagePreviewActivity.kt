package xzy.loshine.nga.ui.image

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_image_preview.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import javax.inject.Inject


class ImagePreviewActivity : EasyActivity(R.layout.activity_image_preview) {

    @Inject
    lateinit var fragmentProvider: Lazy<ImagePreviewFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val stateListAnimator = StateListAnimator()
        stateListAnimator.addState(IntArray(0), ObjectAnimator.ofFloat(app_bar_layout, "elevation", 0f))
        app_bar_layout.stateListAnimator = stateListAnimator

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragmentProvider.get())
                    .commit()
        }
    }
}