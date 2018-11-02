package xzy.loshine.nga.ui.reply

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_reply.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import javax.inject.Inject

class ReplyActivity : EasyActivity(R.layout.activity_reply) {

    @Inject
    lateinit var fragmentProvider: Lazy<ReplyFragment>

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