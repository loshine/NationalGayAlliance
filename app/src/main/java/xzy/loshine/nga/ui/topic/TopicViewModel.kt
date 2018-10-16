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

    private val replaceImgFunc: (MatchResult) -> CharSequence = {
        if (it.groupValues[1].startsWith("http")) {
            "<img src=\"${it.groupValues[1]}\" />"
        } else {
            "<img src=\"https://img.nga.178.com/attachments${it.groupValues[1]}\" />"
        }
    }

    private fun convertUiModel(topicRow: TopicDetailsData.TopicRow): TopicRowUiModel {
        val user = userList?.firstOrNull { topicRow.authorid == it.uid }
        val group = groupList?.firstOrNull { user?.memberId == it.first }
        // 替换可直接转换为 html 代码的格式
        val content = topicRow.content.replace("\\[img]\\.([\\s\\S]*?)\\[/img]".toRegex(), replaceImgFunc)
                .replace("\\[color=([a-z]+?)]([\\s\\S]*?)\\[/color]".toRegex(), "<font color=\"$1\">$2</font>")
                .replace("\\[([/]?(b|u|i|del|list))]".toRegex(), "<$1>")
                .replace("[quote]", "<blockquote style=\"background:#f9efd6;margin:1em 0px 1em 0px;padding:1em;border:1px solid gray;\">")
                .replace("[/quote]", "</blockquote>")
                .replace("\\[\\*](.+?)<br/>".toRegex(), "<li>$1</li>")
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