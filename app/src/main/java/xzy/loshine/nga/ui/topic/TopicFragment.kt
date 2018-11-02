package xzy.loshine.nga.ui.topic

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import io.reactivex.Flowable
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

    private var firstMenuItem: MenuItem? = null
    private var preMenuItem: MenuItem? = null
    private var nextMenuItem: MenuItem? = null
    private var lastMenuItem: MenuItem? = null

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .observeOn(schedulerProvider.ui())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
        addDisposable(viewModel.getFirstAndPreMenuItemVisible()
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    firstMenuItem?.isVisible = it
                    preMenuItem?.isVisible = it
                })
        addDisposable(viewModel.getLastAndNextMenuItemVisible()
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    nextMenuItem?.isVisible = it
                    lastMenuItem?.isVisible = it
                })
        addDisposable(viewModel.getThisTimeLastPage()
                .observeOn(schedulerProvider.ui())
                .subscribe { lastMenuItem?.title = getString(R.string.action_page_last_expr, it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.setItemViewCacheSize(20)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        bindViewModel()

        loadFirst()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_app_bar_topic, menu)
        firstMenuItem = menu.findItem(R.id.action_first)
        preMenuItem = menu.findItem(R.id.action_previous)
        nextMenuItem = menu.findItem(R.id.action_next)
        lastMenuItem = menu.findItem(R.id.action_last)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_first -> loadFirst()
            R.id.action_previous -> loadPrevious()
            R.id.action_next -> loadNext()
            R.id.action_last -> loadLast()
        }
        return true
    }

    fun scrollToTop() {
        recycler_view.scrollToPosition(0)
    }

    private fun loadFirst() {
        addFlowable(viewModel.loadFirst())
    }

    private fun loadPrevious() {
        addFlowable(viewModel.loadPrevious())
    }

    private fun loadNext() {
        addFlowable(viewModel.loadNext())
    }

    private fun loadLast() {
        addFlowable(viewModel.loadLast())
    }

    private fun addFlowable(flowable: Flowable<List<TopicRowUiModel>>) {
        addDisposable(flowable
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    adapter.setNewData(it)
                    recycler_view.scrollToPosition(0)
                }, { Logger.e(it, "error") }))
    }
}