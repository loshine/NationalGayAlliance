package xzy.loshine.nga.ui.topic

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.TopicDetailsData
import xyz.loshine.nga.data.entity.TopicUser
import xyz.loshine.nga.data.repository.topic.TopicRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class TopicViewModel
@Inject constructor(@Named("tid") private val tid: Int,
                    private val topicRepository: TopicRepository) : BaseViewModel() {

    private var index = 1

    private var groupList: List<Pair<Int, String>>? = null
    private var userList: List<TopicUser>? = null

    @Suppress("UNCHECKED_CAST")
    fun load(): Flowable<List<TopicRowUiModel>> {
        return topicRepository.getTopicList(tid, index)
                .subscribeOn(schedulerProvider.io())
                .doOnNext { data ->
                    // 分离出 group 数据
                    val groupMap = data.userList["__GROUPS"] as Map<String, Map<String, Any>>?
                    groupList = groupMap?.map { Pair(it.value["2"].toString().toDouble().toInt(), it.value["0"].toString()) }
                }
                .doOnNext { data ->
                    // 分离用户数据
                    userList = data.userList.filter { it.key.toIntOrNull() != null }
                            .map { TopicUser(it.value) }
                }
                .map { data -> data.rows.map { convertUiModel(it.value) } }
    }

    private fun convertUiModel(topicRow: TopicDetailsData.TopicRow): TopicRowUiModel {
        val user = userList?.firstOrNull { topicRow.authorid == it.uid }
        val group = groupList?.firstOrNull { user?.memberId == it.first }
        // 替换可直接转换为 html 代码的格式
        val content = topicRow.content.replace("[b]", "<b>")
                .replace("[/b]", "</b>")
                .replace("[u]", "<u>")
                .replace("[/u]", "</u>")
                .replace("[i]", "<i>")
                .replace("[/i]", "</i>")
                .replace("[del]", "<del>")
                .replace("[/del]", "</del>")
        return TopicRowUiModel(
                topicRow.pid,
                topicRow.fid,
                topicRow.tid,
                topicRow.subject,
                user?.avatar ?: "",
                user?.username ?: "",
                group?.second ?: "",
                topicRow.postDate,
                topicRow.index,
                topicRow.fromClient ?: "",
                content
        )
    }

}