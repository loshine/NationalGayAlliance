package xzy.loshine.nga.ui.forum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_forum.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject

@ActivityScoped
class ForumFragment @Inject constructor() : BaseFragment(R.layout.fragment_forum) {

    @Inject
    lateinit var viewModel: ForumViewModel
    @Inject
    lateinit var adapter: ForumPostAdapter

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh_layout.setOnRefreshListener { refresh() }
        adapter.setOnLoadMoreListener({ loadMore() }, recycler_view)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    private fun refresh() {
        addDisposable(viewModel.refresh().subscribe({ adapter.setNewData(it) }, { it.printStackTrace() }))
    }

    private fun loadMore() {
        addDisposable(viewModel.loadMore().subscribe({ adapter.addData(it) }, { it.printStackTrace() }))
    }

}
