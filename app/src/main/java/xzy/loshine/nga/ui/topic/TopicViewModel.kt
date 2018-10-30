package xzy.loshine.nga.ui.topic

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
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

    private val firstAndPreMenuItemVisible = BehaviorSubject.createDefault(false)
    private val lastAndNextMenuItemVisible = BehaviorSubject.createDefault(false)
    private val thisTimeLastPageSubject = BehaviorSubject.createDefault(1)

    fun loadFirst(): Flowable<List<TopicRowUiModel>> {
        index = 1
        return loadByPage()
    }

    fun loadPrevious(): Flowable<List<TopicRowUiModel>> {
        index -= 2
        return loadByPage()
    }

    fun loadNext(): Flowable<List<TopicRowUiModel>> {
        return loadByPage()
    }

    fun loadLast(): Flowable<List<TopicRowUiModel>> {
        index = thisTimeLastPageSubject.value ?: 1
        return loadByPage()
    }

    fun getFirstAndPreMenuItemVisible(): Flowable<Boolean> {
        return firstAndPreMenuItemVisible.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getLastAndNextMenuItemVisible(): Flowable<Boolean> {
        return lastAndNextMenuItemVisible.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getThisTimeLastPage(): Flowable<Int> {
        return thisTimeLastPageSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadByPage(): Flowable<List<TopicRowUiModel>> {
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
                .doOnNext {
                    val thisTimeLastPage = it.topic.thisVisitRows / it.pageSize + 1
                    thisTimeLastPageSubject.onNext(thisTimeLastPage)
                    firstAndPreMenuItemVisible.onNext(index > 1)
                    lastAndNextMenuItemVisible.onNext(thisTimeLastPage != index)
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