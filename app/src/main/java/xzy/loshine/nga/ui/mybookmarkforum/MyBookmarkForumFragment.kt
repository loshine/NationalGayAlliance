package xzy.loshine.nga.ui.mybookmarkforum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_my_bookmark_forum.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import xzy.loshine.nga.ui.forum.ForumActivity
import xzy.loshine.nga.ui.forumgroup.ForumGroupAdapter
import javax.inject.Inject

@ActivityScoped
class MyBookmarkForumFragment @Inject constructor() : BaseFragment(R.layout.fragment_my_bookmark_forum) {

    @Inject
    lateinit var viewModel: MyBookmarkForumViewModel
    @Inject
    lateinit var adapter: ForumGroupAdapter

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .subscribeOn(schedulerProvider.computation())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(Intent(context, ForumActivity::class.java)
                    .also { it.putExtra("forum", adapter.data[position]) })
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 3)

        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    private fun load() {
        addDisposable(viewModel.load()
                .observeOn(schedulerProvider.ui())
                .subscribe({ adapter.setNewData(it) }, { it.printStackTrace() }))
    }
}