package xzy.loshine.nga.ui.topic

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xyz.loshine.nga.data.entity.TopicDetailsData
import xzy.loshine.nga.R
import javax.inject.Inject

class TopicRowsAdapter
@Inject constructor() : BaseQuickAdapter<TopicDetailsData.TopicRow, BaseViewHolder>(R.layout.recycler_item_topic_rows) {

    override fun convert(helper: BaseViewHolder, item: TopicDetailsData.TopicRow) {
        helper.setText(R.id.author, "${item.authorid}")
    }

}