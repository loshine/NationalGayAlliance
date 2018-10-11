package xzy.loshine.nga.ui.forumgroup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_forum_group.*
import xyz.loshine.nga.data.entity.ForumGroup
import xzy.loshine.nga.R
import xzy.loshine.nga.ui.base.EasyFragment
import xzy.loshine.nga.ui.forum.ForumActivity

class ForumGroupFragment : EasyFragment(R.layout.fragment_forum_group) {

    companion object {
        private const val ARGUMENT_GROUP = "group"

        fun newInstance(group: ForumGroup): ForumGroupFragment {
            return ForumGroupFragment()
                    .also { f -> f.arguments = Bundle().also { it.putParcelable(ARGUMENT_GROUP, group) } }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ForumGroupAdapter()
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(Intent(context, ForumActivity::class.java)
                    .also { it.putExtra("forum", adapter.data[position]) })
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 3)

        val category = arguments?.getParcelable<ForumGroup>(ARGUMENT_GROUP)
        category?.let { adapter.addData(it.forumList) }
    }
}