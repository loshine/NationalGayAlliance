package xzy.loshine.nga.ui.topic

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_topic.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import javax.inject.Inject

class TopicActivity : EasyActivity(R.layout.activity_topic) {

    @Inject
    lateinit var fragmentProvider: Lazy<TopicFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar.title = ""
        toolbar.setNavigationOnClickListener { onBackPressed() }

        setSupportActionBar(bottom_app_bar)
        bottom_app_bar.setNavigationOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.container) as TopicFragment?
            fragment?.scrollToTop()
        }

        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
            if (-offset == app_bar_layout.totalScrollRange) {
                fab.hide()
            } else if (offset == 0) {
                fab.show()
            }
        })

        title_text_view.text = intent.getStringExtra("subject")
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