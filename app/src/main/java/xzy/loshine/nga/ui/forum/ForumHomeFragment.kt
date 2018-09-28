package xzy.loshine.nga.ui.forum

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_forum_home.*
import xyz.loshine.nga.data.entity.ForumBoardCategory
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject

class ForumHomeFragment @Inject constructor() : BaseFragment(R.layout.fragment_forum_home) {

    @Inject
    lateinit var viewModel: ForumHomeViewModel

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

    private fun initCategories(categories: List<ForumBoardCategory>) {
        val adapter = ForumHomePagerAdapter(childFragmentManager, categories)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = categories.size
        tab_layout.setupWithViewPager(view_pager)
    }
}