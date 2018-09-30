package xzy.loshine.nga.ui.forum

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_forum.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity

class ForumActivity : EasyActivity(R.layout.activity_forum) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}