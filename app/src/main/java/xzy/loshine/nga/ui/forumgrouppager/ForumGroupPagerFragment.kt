package xzy.loshine.nga.ui.forumgrouppager

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_forum_group_pager.*
import xyz.loshine.nga.data.entity.ForumGroup
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import xzy.loshine.nga.ui.forumgroup.ForumGroupFragment
import xzy.loshine.nga.ui.mybookmarkgroup.MyBookmarkGroupFragment
import javax.inject.Inject

@ActivityScoped
class ForumGroupPagerFragment @Inject constructor() : BaseFragment(R.layout.fragment_forum_group_pager) {

    @Inject
    lateinit var viewModel: ForumGroupPagerViewModel
    @Inject
    lateinit var myBookmarkGroupFragment: MyBookmarkGroupFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateListAnimator = StateListAnimator()
        stateListAnimator.addState(IntArray(8), ObjectAnimator.ofFloat(tab_layout, "elevation", 8f))
        tab_layout.stateListAnimator = stateListAnimator

        addDisposable(viewModel.loadForumBoardCategories()
                .observeOn(schedulerProvider.ui())
                .subscribe({ initCategories(it) }, {}))

        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .observeOn(schedulerProvider.ui())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
    }

    private fun initCategories(categories: List<ForumGroup>) {
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(myBookmarkGroupFragment)
        val titleList = mutableListOf<String>()
        titleList.add(getString(R.string.tab_my_bookmark))
        categories.forEach {
            fragmentList.add(ForumGroupFragment.newInstance(it))
            titleList.add(it.name)
        }
        val adapter = ForumGroupPagerAdapter(childFragmentManager, fragmentList, titleList)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = categories.size
        tab_layout.setupWithViewPager(view_pager)
    }
}