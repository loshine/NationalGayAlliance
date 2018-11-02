package xzy.loshine.nga.ui.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_topic.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class ImagePreviewFragment @Inject constructor() : BaseFragment(R.layout.fragment_image_preview) {

    @Inject
    lateinit var adapter: ImagePreviewAdapter
    @JvmField
    @field:Named("index")
    @Inject
    var currentIndex: Int = 0

    override fun bindViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        recycler_view.scrollToPosition(currentIndex)
        PagerSnapHelper().attachToRecyclerView(recycler_view)
    }
}