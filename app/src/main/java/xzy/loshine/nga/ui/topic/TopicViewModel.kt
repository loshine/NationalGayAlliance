package xzy.loshine.nga.ui.topic

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.TopicDetailsData
import xyz.loshine.nga.data.entity.TopicUser
import xyz.loshine.nga.data.repository.topic.TopicRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import xzy.loshine.nga.utils.ContentParser
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class TopicViewModel
@Inject constructor(@Named("tid") private val tid: Int,
                    private val contentParser: ContentParser,
                    private val topicRepository: TopicRepository) : BaseViewModel() {

    private var index = 1

    private val groupList: MutableList<Pair<Int, String>> = mutableListOf()
    private val userList: MutableList<TopicUser> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    fun load(): Flowable<List<TopicRowUiModel>> {
        index = 1
        return topicRepository.getTopicList(tid, index)
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe {
                    groupList.clear()
                    userList.clear()
                }
                .doOnNext { data ->
                    val groupMap = data.userList["__GROUPS"] as Map<String, Map<String, Any>>?
                    groupMap?.mapTo(groupList) { Pair(it.value["2"].toString().toDouble().toInt(), it.value["0"].toString()) }
                }
                .doOnNext { data ->
                    data.userList.filter { it.key.toIntOrNull() != null }
                            .mapTo(userList) { TopicUser(it.value) }
                }
                .map { data -> data.rows.map { convertUiModel(it.value) } }
                .doOnComplete { index++ }
    }

    @Suppress("UNCHECKED_CAST")
    fun loadMore(): Flowable<List<TopicRowUiModel>> {
        return topicRepository.getTopicList(tid, index)
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe {
                    groupList.clear()
                    userList.clear()
                }
                .doOnNext { data ->
                    val groupMap = data.userList["__GROUPS"] as Map<String, Map<String, Any>>?
                    groupMap?.mapTo(groupList) { Pair(it.value["2"].toString().toDouble().toInt(), it.value["0"].toString()) }
                }
                .doOnNext { data ->
                    data.userList.filter { it.key.toIntOrNull() != null }
                            .mapTo(userList) { TopicUser(it.value) }
                }
                .map { data -> data.rows.map { convertUiModel(it.value) } }
                .doOnComplete { index++ }
    }

    private fun convertUiModel(topicRow: TopicDetailsData.TopicRow): TopicRowUiModel {
        val user = userList.firstOrNull { topicRow.authorId == it.uid }
        val group = groupList.firstOrNull { user?.memberId == it.first }
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
                contentParser.parse(topicRow.content)
        )
    }
}