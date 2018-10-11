package xzy.loshine.nga.ui.topic

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
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
        setSupportActionBar(toolbar)

        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragmentProvider.get())
                    .commit()
        }
    }
}