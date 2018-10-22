package xzy.loshine.nga.ui.topic

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_topic.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject

@ActivityScoped
class TopicFragment @Inject constructor() : BaseFragment(R.layout.fragment_topic) {

    @Inject
    lateinit var viewModel: TopicViewModel
    @Inject
    lateinit var adapter: TopicRowsAdapter

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .observeOn(schedulerProvider.ui())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
        addDisposable(viewModel.getHasMore()
                .observeOn(schedulerProvider.ui())
                .subscribe { adapter.setEnableLoadMore(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        adapter.setOnLoadMoreListener({ loadMore() }, recycler_view)

        bindViewModel()

        view.post {
            viewModel.load()
                    .observeOn(schedulerProvider.ui())
                    .subscribe({ adapter.setNewData(it) }, { it.printStackTrace() })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    private fun loadMore() {
        addDisposable(viewModel.loadMore()
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    adapter.addData(it)
                    adapter.loadMoreComplete()
                }) { it.printStackTrace() })
    }
}