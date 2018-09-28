package xzy.loshine.nga.ui.forum

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.loshine.nga.data.entity.ForumBoardCategory
import xzy.loshine.nga.ui.forumcategory.ForumBoardCategoryFragment

class ForumHomePagerAdapter(fm: FragmentManager, val data: List<ForumBoardCategory>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ForumBoardCategoryFragment()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }
}
