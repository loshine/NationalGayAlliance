package xzy.loshine.nga.ui

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_main_content.*
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyActivity
import xzy.loshine.nga.ui.forum.ForumHomeFragment
import javax.inject.Inject


class MainActivity : EasyActivity(R.layout.activity_main) {

    @Inject
    lateinit var forumHomeFragmentProvider: Lazy<ForumHomeFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawer_layout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        val stateListAnimator = StateListAnimator()
        stateListAnimator.addState(IntArray(0), ObjectAnimator.ofFloat(app_bar_layout, "elevation", 0f))
        app_bar_layout.stateListAnimator = stateListAnimator
        supportFragmentManager.beginTransaction()
                .add(R.id.container, forumHomeFragmentProvider.get())
                .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
