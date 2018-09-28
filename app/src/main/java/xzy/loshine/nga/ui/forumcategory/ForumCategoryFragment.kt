package xzy.loshine.nga.ui.forumcategory

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_forum_category.*
import xyz.loshine.nga.data.entity.ForumBoardCategory
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyFragment

class ForumCategoryFragment : EasyFragment(R.layout.fragment_forum_category) {

    companion object {
        private const val ARGUMENT_CATEGORY = "category"

        fun newInstance(category: ForumBoardCategory): ForumCategoryFragment {
            return ForumCategoryFragment()
                    .also { f -> f.arguments = Bundle().also { it.putParcelable(ARGUMENT_CATEGORY, category) } }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ForumCategoryBoardAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 3)

        val category = arguments?.getParcelable<ForumBoardCategory>(ARGUMENT_CATEGORY)
        category?.let { adapter.addData(it.forumBoardList) }
    }
}