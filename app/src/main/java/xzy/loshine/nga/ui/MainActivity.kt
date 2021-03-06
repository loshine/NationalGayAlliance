package xzy.loshine.nga.ui

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_main_content.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import xzy.loshine.nga.ui.forumgrouppager.ForumGroupPagerFragment
import xzy.loshine.nga.ui.login.LoginActivity
import javax.inject.Inject


class MainActivity : EasyActivity(R.layout.activity_main) {

    @Inject
    lateinit var forumGroupPagerFragmentProvider: Lazy<ForumGroupPagerFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener {
            // set item as selected to persist highlight
            it.isChecked = true
            // close drawer when item is tapped
            drawer_layout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        navigation_view.getHeaderView(0).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val stateListAnimator = StateListAnimator()
        stateListAnimator.addState(IntArray(0), ObjectAnimator.ofFloat(app_bar_layout, "elevation", 0f))
        app_bar_layout.stateListAnimator = stateListAnimator
        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, forumGroupPagerFragmentProvider.get())
                    .commit()
        }
    }

    private var lastTime: Long = 0

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime < 2 * 1000) {
                super.onBackPressed()
            } else {
                Toast.makeText(this, "再按一次返回键退出应用", Toast.LENGTH_SHORT).show()
                lastTime = currentTime
            }
        }
    }
}
