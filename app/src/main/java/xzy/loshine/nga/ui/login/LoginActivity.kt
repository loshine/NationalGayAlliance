package xzy.loshine.nga.ui.login

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_login.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import xzy.loshine.nga.ui.base.EasyFragment
import javax.inject.Inject

class LoginActivity : EasyActivity(R.layout.activity_login) {

    @Inject
    lateinit var fragmentProvider: Lazy<LoginFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        setSupportActionBar(toolbar)

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragmentProvider.get())
                    .commit()
        }
    }

    override fun onBackPressed() {
        if ((supportFragmentManager.findFragmentById(R.id.container) as EasyFragment?)?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }
}