package xzy.loshine.nga.ui.forum

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_forum.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import xzy.loshine.nga.ui.topic.TopicActivity
import javax.inject.Inject

@ActivityScoped
class ForumFragment @Inject constructor() : BaseFragment(R.layout.fragment_forum) {

    @Inject
    lateinit var viewModel: ForumViewModel
    @Inject
    lateinit var adapter: ForumPostAdapter

    private var favouriteMenuItem: MenuItem? = null

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .observeOn(schedulerProvider.ui())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
        addDisposable(viewModel.getRefreshing()
                .observeOn(schedulerProvider.ui())
                .subscribe { refresh_layout.isRefreshing = it })
        addDisposable(viewModel.getHasMore()
                .observeOn(schedulerProvider.ui())
                .subscribe { adapter.setEnableLoadMore(it) })
        addDisposable(viewModel.getFavouriteDrawable()
                .observeOn(schedulerProvider.ui())
                .subscribe { favouriteMenuItem?.setIcon(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh_layout.setOnRefreshListener { refresh() }
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(Intent(context, TopicActivity::class.java)
                    .also { it.putExtra("tid", adapter.data[position].tid) })
        }
        adapter.setOnLoadMoreListener({ loadMore() }, recycler_view)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        bindViewModel()

        view.post { refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_forum, menu)
        favouriteMenuItem = menu.getItem(0)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_favourite) {
            toggleFavourite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleFavourite() {
        addDisposable(viewModel.toggleFavourite()
                .observeOn(schedulerProvider.ui())
                .subscribe({}, { it.printStackTrace() }))
    }

    private fun refresh() {
        addDisposable(viewModel.refresh()
                .observeOn(schedulerProvider.ui())
                .subscribe({ adapter.setNewData(it) }, { it.printStackTrace() }))
        addDisposable(viewModel.isFavourite()
                .observeOn(schedulerProvider.ui())
                .subscribe({}, { it.printStackTrace() }))
    }

    private fun loadMore() {
        addDisposable(viewModel.loadMore()
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    adapter.addData(it)
                    adapter.loadMoreComplete()
                }, { it.printStackTrace() }))
    }

}
