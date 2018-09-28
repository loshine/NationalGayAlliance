package xyz.loshine.nga.data.entity

data class ForumBoardCategory(
        val name: String,
        val index: Int,
        val forumBoardList: List<ForumBoard>
)