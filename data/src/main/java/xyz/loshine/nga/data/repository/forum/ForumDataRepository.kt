package xyz.loshine.nga.data.repository.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.Forum
import xyz.loshine.nga.data.entity.ForumGroup
import xyz.loshine.nga.data.entity.PostListData
import xyz.loshine.nga.data.net.api.NgaApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ForumDataRepository @Inject constructor(private val ngaApi: NgaApi) : ForumRepository {

    private val categoryList: MutableList<ForumGroup> = mutableListOf()

    override fun getForumBoardCategories(): Flowable<List<ForumGroup>> {
        if (categoryList.isEmpty()) {
            var boardList: MutableList<Forum> = mutableListOf(
                    Forum(-7, "大漩涡", categoryList.size),
                    Forum(-187579, "历史博物馆", categoryList.size),
                    Forum(-447601, "二次元", categoryList.size),
                    Forum(-353371, "宠物养成", categoryList.size),
                    Forum(-343809, "汽车俱乐部", categoryList.size),
                    Forum(-81981, "生命之杯", categoryList.size),
                    Forum(485, "篮球", categoryList.size),
                    Forum(-576177, "影音讨论区", categoryList.size),
                    Forum(414, "游戏综合讨论", categoryList.size),
                    Forum(436, "消费电子 IT新闻", categoryList.size),
                    Forum(334, "硬件配置", categoryList.size),
                    Forum(498, "二手交易", categoryList.size)
            )
            var category = ForumGroup("网事杂谈", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(7, "议事厅", categoryList.size),
                    Forum(181, "铁血沙场", categoryList.size),
                    Forum(184, "圣光广场", categoryList.size),
                    Forum(320, "黑锋要塞", categoryList.size),
                    Forum(187, "猎手大厅", categoryList.size),
                    Forum(185, "风暴祭坛", categoryList.size),
                    Forum(189, "暗影裂口", categoryList.size),
                    Forum(186, "翡翠梦境", categoryList.size),
                    Forum(477, "伊利达雷", categoryList.size),
                    Forum(390, "五晨寺", categoryList.size),
                    Forum(182, "魔法圣堂", categoryList.size),
                    Forum(188, "恶魔深渊", categoryList.size),
                    Forum(183, "信仰神殿", categoryList.size),

                    Forum(310, "精英议会", categoryList.size),
                    Forum(190, "任务讨论", categoryList.size),
                    Forum(213, "战争档案", categoryList.size),
                    Forum(218, "副本专区", categoryList.size),
                    Forum(258, "战场讨论", categoryList.size),
                    Forum(272, "竞技场", categoryList.size),
                    Forum(191, "地精商会", categoryList.size),
                    Forum(200, "插件研究", categoryList.size),
                    Forum(460, "BigFoot", categoryList.size),
                    Forum(274, "插件发布", categoryList.size),
                    Forum(333, "DKP系统", categoryList.size),
                    Forum(327, "成就讨论", categoryList.size),
                    Forum(388, "幻化讨论", categoryList.size),
                    Forum(411, "宠物讨论", categoryList.size),
                    Forum(255, "公会管理", categoryList.size),
                    Forum(306, "人员招募", categoryList.size),

                    Forum(264, "卡拉赞剧院", categoryList.size),
                    Forum(8, "大图书馆", categoryList.size),
                    Forum(102, "作家协会", categoryList.size),
                    Forum(124, "壁画洞窟", categoryList.size),
                    Forum(254, "镶金玫瑰", categoryList.size),
                    Forum(355, "龟岩兄弟会", categoryList.size),
                    Forum(116, "奇迹之泉", categoryList.size),

                    Forum(323, "国服以外", categoryList.size),
                    Forum(10, "银色黎明", categoryList.size),
                    Forum(230, "风纪委员会", categoryList.size)
            )
            category = ForumGroup("魔兽世界", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(459, "守望先锋", categoryList.size),
                    Forum(422, "炉石传说", categoryList.size),
                    Forum(318, "暗黑破坏神3", categoryList.size),
                    Forum(431, "风暴英雄", categoryList.size),
                    Forum(406, "星际争霸2", categoryList.size),
                    Forum(490, "魔兽争霸", categoryList.size)
            )
            category = ForumGroup("暴雪游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(-152678, "英雄联盟", categoryList.size),
                    Forum(479, "英雄联盟赛事", categoryList.size),
                    Forum(418, "英雄联盟视频", categoryList.size)
            )
            category = ForumGroup("拳头游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(482, "CS:GO", categoryList.size),
                    Forum(321, "DOTA2", categoryList.size),
                    Forum(622, "刀塔卡牌 Artifact", categoryList.size)
            )
            category = ForumGroup("Valve Games", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(614, "PlayStation", categoryList.size),
                    Forum(615, "XBox", categoryList.size),
                    Forum(616, "Nintendo", categoryList.size)
            )
            category = ForumGroup("主机游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(414, "游戏综合讨论", categoryList.size),
                    Forum(428, "手机游戏", categoryList.size),
                    Forum(-452227, "口袋妖怪", categoryList.size),
                    Forum(426, "智龙迷城", categoryList.size),
                    Forum(-51095, "部落冲突", categoryList.size),
                    Forum(492, "部落冲突:皇室战争", categoryList.size),
                    Forum(-362960, "最终幻想14", categoryList.size),
                    Forum(-6194253, "战争雷霆", categoryList.size),
                    Forum(489, "怪物猎人", categoryList.size),
                    Forum(-47218, "地下城与勇士", categoryList.size),
                    Forum(425, "行星边际2", categoryList.size),
                    Forum(-65653, "剑灵", categoryList.size),
                    Forum(412, "巫师之怒", categoryList.size),
                    Forum(-235147, "激战2", categoryList.size),
                    Forum(442, "逆战", categoryList.size),
                    Forum(-46468, "洛拉斯的战争世界", categoryList.size),
                    Forum(432, "战机世界", categoryList.size),
                    Forum(441, "战舰世界", categoryList.size),
                    Forum(-2371813, "EVE", categoryList.size),
                    Forum(-7861121, "剑叁 ", categoryList.size),
                    Forum(448, "剑叁同人 ", categoryList.size),
                    Forum(-793427, "斗战神", categoryList.size),
                    Forum(332, "战锤40K", categoryList.size),
                    Forum(416, "火炬之光2", categoryList.size),
                    Forum(420, "MT Online", categoryList.size),
                    Forum(424, "圣斗士星矢", categoryList.size),
                    Forum(-1513130, "鲜血兄弟会", categoryList.size),
                    Forum(433, "神雕侠侣", categoryList.size),
                    Forum(434, "神鬼幻想", categoryList.size),
                    Forum(435, "上古卷轴Online", categoryList.size),
                    Forum(443, "FIFA Online 3", categoryList.size),
                    Forum(444, "刀塔传奇", categoryList.size),
                    Forum(445, "迷你西游", categoryList.size),
                    Forum(447, "锁链战记", categoryList.size),
                    Forum(-532408, "沃土", categoryList.size),
                    Forum(353, "纽沃斯英雄传", categoryList.size),
                    Forum(452, "天涯明月刀", categoryList.size),
                    Forum(453, "魔力宝贝", categoryList.size),
                    Forum(454, "神之浩劫", categoryList.size),
                    Forum(455, "鬼武者 魂", categoryList.size),
                    Forum(480, "百万亚瑟王", categoryList.size),
                    Forum(481, "Minecraft", categoryList.size),
                    Forum(484, "热血江湖传", categoryList.size),
                    Forum(486, "辐射", categoryList.size),
                    Forum(487, "刀剑魔药2", categoryList.size),
                    Forum(488, "村长打天下", categoryList.size),
                    Forum(493, "刀塔战纪", categoryList.size),
                    Forum(494, "魔龙之魂", categoryList.size),
                    Forum(495, "光荣三国志系列", categoryList.size),
                    Forum(496, "九十九姬", categoryList.size)
            )
            category = ForumGroup("其他游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(193, "帐号安全", categoryList.size),
                    Forum(334, "PC软硬件", categoryList.size),
                    Forum(201, "系统问题", categoryList.size),
                    Forum(335, "网站开发", categoryList.size)
            )
            category = ForumGroup("系统软硬件讨论", categoryList.size, boardList)
            categoryList.add(category)

            boardList = mutableListOf(
                    Forum(-447601, "二次元国家地理", categoryList.size),
                    Forum(-84, "模玩之魂", categoryList.size),
                    Forum(-8725919, "小窗视界", categoryList.size),
                    Forum(-965240, "树洞", categoryList.size),
                    Forum(-131429, "红茶馆——小说馆", categoryList.size),
                    Forum(-608808, "血腥厨房", categoryList.size),
                    Forum(-469608, "影~视~秀", categoryList.size),
                    Forum(-55912, "音乐讨论", categoryList.size),
                    Forum(-522474, "综合体育讨论区", categoryList.size),
                    Forum(-1068355, "晴风村", categoryList.size),
                    Forum(-168888, "育儿版", categoryList.size),
                    Forum(-54214, "时尚板", categoryList.size),
                    Forum(-353371, "宠物养成", categoryList.size),
                    Forum(-538800, "乙女向二次元", categoryList.size),
                    Forum(-7678526, "麻将科学院", categoryList.size),
                    Forum(-202020, "程序员职业交流", categoryList.size),
                    Forum(-444012, "我们的骑迹", categoryList.size),
                    Forum(-349066, "开心茶园", categoryList.size),
                    Forum(-314508, "世界尽头的百货公司", categoryList.size),
                    Forum(-2671, "耳机区", categoryList.size),
                    Forum(-970841, "东方教主陈乔恩", categoryList.size),
                    Forum(-3355501, "彩虹天堂", categoryList.size),
                    Forum(-403298, "怨灵图纸收藏室", categoryList.size),
                    Forum(-3432136, "飘落的诗章", categoryList.size),
                    Forum(-187628, "家居 装修", categoryList.size),
                    Forum(-8627585, "牛头人酋长乐队", categoryList.size),
                    Forum(-17100, "全民健身中心", categoryList.size)
            )
            category = ForumGroup("个人版面", categoryList.size, boardList)
            categoryList.add(category)
        }
        return Flowable.just(categoryList)
    }

    override fun getForumPostList(fid: Int, index: Int): Flowable<PostListData> {
        return ngaApi.getThreadList(fid, index)
                .map { it.data }
    }
}