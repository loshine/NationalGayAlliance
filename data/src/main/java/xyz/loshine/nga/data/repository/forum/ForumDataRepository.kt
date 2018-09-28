package xyz.loshine.nga.data.repository.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumBoard
import xyz.loshine.nga.data.entity.ForumBoardCategory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ForumDataRepository @Inject constructor() : ForumRepository {

    private val categoryList: MutableList<ForumBoardCategory> = mutableListOf()

    override fun getForumBoardCategories(): Flowable<List<ForumBoardCategory>> {
        if (categoryList.isEmpty()) {
            var boardList: List<ForumBoard> = listOf(
                    ForumBoard(-7, "大漩涡", categoryList.size),
                    ForumBoard(-343809, "汽车俱乐部", categoryList.size),
                    ForumBoard(-81981, "生命之杯", categoryList.size),
                    ForumBoard(-576177, "影音讨论区", categoryList.size),
                    ForumBoard(414, "游戏综合讨论", categoryList.size),
                    ForumBoard(436, "消费电子 IT新闻", categoryList.size),
                    ForumBoard(498, "二手交易", categoryList.size),
                    ForumBoard(-187579, "大漩涡历史博物馆", categoryList.size),
                    ForumBoard(485, "篮球", categoryList.size)
            )
            var category = ForumBoardCategory("大漩涡系列", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(7, "议事厅", categoryList.size),
                    ForumBoard(310, "精英议会", categoryList.size),
                    ForumBoard(323, "国服以外讨论", categoryList.size),
                    ForumBoard(10, "银色黎明", categoryList.size),
                    ForumBoard(230, "风纪委员会", categoryList.size)
            )
            category = ForumBoardCategory("综合讨论", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(390, "五晨寺", categoryList.size),
                    ForumBoard(320, "黑锋要塞", categoryList.size),
                    ForumBoard(181, "铁血沙场", categoryList.size),
                    ForumBoard(182, "魔法圣堂", categoryList.size),
                    ForumBoard(183, "信仰神殿", categoryList.size),
                    ForumBoard(185, "风暴祭坛", categoryList.size),
                    ForumBoard(186, "翡翠梦境", categoryList.size),
                    ForumBoard(187, "猎手大厅", categoryList.size),
                    ForumBoard(184, "圣光之力", categoryList.size),
                    ForumBoard(188, "恶魔深渊", categoryList.size),
                    ForumBoard(189, "暗影裂口", categoryList.size),
                    ForumBoard(477, "伊利达雷", categoryList.size)
            )
            category = ForumBoardCategory("职业讨论区", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(310, "精英议会", categoryList.size),
                    ForumBoard(190, "任务讨论", categoryList.size),
                    ForumBoard(213, "战争档案", categoryList.size),
                    ForumBoard(218, "副本专区", categoryList.size),
                    ForumBoard(258, "战场讨论", categoryList.size),
                    ForumBoard(272, "竞技场", categoryList.size),
                    ForumBoard(191, "地精商会", categoryList.size),
                    ForumBoard(200, "插件研究", categoryList.size),
                    ForumBoard(460, "BigFoot", categoryList.size),
                    ForumBoard(274, "插件发布", categoryList.size),
                    ForumBoard(333, "DKP系统", categoryList.size),
                    ForumBoard(327, "成就讨论", categoryList.size),
                    ForumBoard(388, "幻化讨论", categoryList.size),
                    ForumBoard(411, "宠物讨论", categoryList.size),
                    ForumBoard(255, "公会管理", categoryList.size),
                    ForumBoard(306, "人员招募", categoryList.size)
            )
            category = ForumBoardCategory("冒险心得", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(264, "卡拉赞剧院", categoryList.size),
                    ForumBoard(8, "大图书馆", categoryList.size),
                    ForumBoard(102, "作家协会", categoryList.size),
                    ForumBoard(124, "壁画洞窟", categoryList.size),
                    ForumBoard(254, "镶金玫瑰", categoryList.size),
                    ForumBoard(355, "龟岩兄弟会", categoryList.size),
                    ForumBoard(116, "奇迹之泉", categoryList.size)
            )
            category = ForumBoardCategory("麦迪文之塔", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(193, "帐号安全", categoryList.size),
                    ForumBoard(334, "PC软硬件", categoryList.size),
                    ForumBoard(201, "系统问题", categoryList.size),
                    ForumBoard(335, "网站开发", categoryList.size)
            )
            category = ForumBoardCategory("系统软硬件讨论", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(422, "炉石传说", categoryList.size),
                    ForumBoard(431, "风暴英雄", categoryList.size),
                    ForumBoard(459, "守望先锋", categoryList.size),
                    ForumBoard(318, "暗黑破坏神3", categoryList.size),
                    ForumBoard(490, "魔兽争霸", categoryList.size),
                    ForumBoard(406, "星际争霸2", categoryList.size)
            )
            category = ForumBoardCategory("暴雪游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(414, "游戏综合讨论", categoryList.size),
                    ForumBoard(428, "手机游戏", categoryList.size),
                    ForumBoard(-152678, "英雄联盟", categoryList.size),
                    ForumBoard(-452227, "口袋妖怪", categoryList.size),
                    ForumBoard(426, "智龙迷城", categoryList.size),
                    ForumBoard(-51095, "部落冲突", categoryList.size),
                    ForumBoard(492, "部落冲突:皇室战争", categoryList.size),
                    ForumBoard(-362960, "最终幻想14", categoryList.size),
                    ForumBoard(-6194253, "战争雷霆", categoryList.size),
                    ForumBoard(427, "怪物猎人", categoryList.size),
                    ForumBoard(-47218, "地下城与勇士", categoryList.size),
                    ForumBoard(425, "行星边际2", categoryList.size),
                    ForumBoard(-65653, "剑灵", categoryList.size),
                    ForumBoard(412, "巫师之怒", categoryList.size),
                    ForumBoard(-235147, "激战2", categoryList.size),
                    ForumBoard(442, "逆战", categoryList.size),
                    ForumBoard(-46468, "洛拉斯的战争世界", categoryList.size),
                    ForumBoard(432, "战机世界", categoryList.size),
                    ForumBoard(441, "战舰世界", categoryList.size),
                    ForumBoard(321, "DotA", categoryList.size),
                    ForumBoard(-2371813, "EVE", categoryList.size),
                    ForumBoard(-7861121, "剑叁 ", categoryList.size),
                    ForumBoard(448, "剑叁同人 ", categoryList.size),
                    ForumBoard(-793427, "斗战神", categoryList.size),
                    ForumBoard(332, "战锤40K", categoryList.size),
                    ForumBoard(416, "火炬之光2", categoryList.size),
                    ForumBoard(420, "MT Online", categoryList.size),
                    ForumBoard(424, "圣斗士星矢", categoryList.size),
                    ForumBoard(-1513130, "鲜血兄弟会", categoryList.size),
                    ForumBoard(433, "神雕侠侣", categoryList.size),
                    ForumBoard(434, "神鬼幻想", categoryList.size),
                    ForumBoard(435, "上古卷轴Online", categoryList.size),
                    ForumBoard(443, "FIFA Online 3", categoryList.size),
                    ForumBoard(444, "刀塔传奇", categoryList.size),
                    ForumBoard(445, "迷你西游", categoryList.size),
                    ForumBoard(447, "锁链战记", categoryList.size),
                    ForumBoard(-532408, "沃土", categoryList.size),
                    ForumBoard(353, "纽沃斯英雄传", categoryList.size),
                    ForumBoard(452, "天涯明月刀", categoryList.size),
                    ForumBoard(453, "魔力宝贝", categoryList.size),
                    ForumBoard(454, "神之浩劫", categoryList.size),
                    ForumBoard(455, "鬼武者 魂", categoryList.size),
                    ForumBoard(480, "百万亚瑟王", categoryList.size),
                    ForumBoard(481, "Minecraft", categoryList.size),
                    ForumBoard(482, "CS", categoryList.size),
                    ForumBoard(484, "热血江湖传", categoryList.size),
                    ForumBoard(486, "辐射", categoryList.size),
                    ForumBoard(487, "刀剑魔药2", categoryList.size),
                    ForumBoard(488, "村长打天下", categoryList.size),
                    ForumBoard(493, "刀塔战纪", categoryList.size),
                    ForumBoard(494, "魔龙之魂", categoryList.size),
                    ForumBoard(495, "光荣三国志系列", categoryList.size),
                    ForumBoard(496, "九十九姬", categoryList.size)
            )
            category = ForumBoardCategory("其他游戏", categoryList.size, boardList)
            categoryList.add(category)

            boardList = listOf(
                    ForumBoard(-447601, "二次元国家地理", categoryList.size),
                    ForumBoard(-84, "模玩之魂", categoryList.size),
                    ForumBoard(-8725919, "小窗视界", categoryList.size),
                    ForumBoard(-965240, "树洞", categoryList.size),
                    ForumBoard(-131429, "红茶馆——小说馆", categoryList.size),
                    ForumBoard(-608808, "血腥厨房", categoryList.size),
                    ForumBoard(-469608, "影~视~秀", categoryList.size),
                    ForumBoard(-55912, "音乐讨论", categoryList.size),
                    ForumBoard(-522474, "综合体育讨论区", categoryList.size),
                    ForumBoard(-1068355, "晴风村", categoryList.size),
                    ForumBoard(-168888, "育儿版", categoryList.size),
                    ForumBoard(-54214, "时尚板", categoryList.size),
                    ForumBoard(-353371, "宠物养成", categoryList.size),
                    ForumBoard(-538800, "乙女向二次元", categoryList.size),
                    ForumBoard(-7678526, "麻将科学院", categoryList.size),
                    ForumBoard(-202020, "程序员职业交流", categoryList.size),
                    ForumBoard(-444012, "我们的骑迹", categoryList.size),
                    ForumBoard(-349066, "开心茶园", categoryList.size),
                    ForumBoard(-314508, "世界尽头的百货公司", categoryList.size),
                    ForumBoard(-2671, "耳机区", categoryList.size),
                    ForumBoard(-970841, "东方教主陈乔恩", categoryList.size),
                    ForumBoard(-3355501, "基腐版", categoryList.size),
                    ForumBoard(-403298, "怨灵图纸收藏室", categoryList.size),
                    ForumBoard(-3432136, "飘落的诗章", categoryList.size),
                    ForumBoard(-187628, "家居 装修", categoryList.size),
                    ForumBoard(-8627585, "牛头人酋长乐队", categoryList.size),
                    ForumBoard(-17100, "全民健身中心", categoryList.size)
            )
            category = ForumBoardCategory("个人版面", categoryList.size, boardList)
            categoryList.add(category)
        }
        return Flowable.just(categoryList)
    }

}