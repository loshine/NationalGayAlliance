package xzy.loshine.nga.utils

import android.text.TextUtils
import org.apache.commons.text.StringEscapeUtils
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ContentParser @Inject constructor() {

    fun parse(content: String): String {

        val replaceImgFunc: (MatchResult) -> CharSequence = {
            val imgUrl = if (it.groupValues[1].startsWith("./mon_")) {
                "https://img.nga.178.com/attachments${it.groupValues[1].substring(1)}"
            } else {
                it.groupValues[1]
            }
            "<a href='$imgUrl'><img src='$imgUrl' ></a>"
        }
        val replaceUrlFunc: (MatchResult) -> CharSequence = {
            if (it.groupValues[1].startsWith("/")) {
                "<a href='https://bbs.nga.cn${it.groupValues[1]}'>[站内链接]</a>"
            } else {
                "<a href='${it.groupValues[1]}'>${it.groupValues[1]}</a>"
            }
        }
        val replaceUrl2Func: (MatchResult) -> CharSequence = {
            if (it.groupValues[1].startsWith("/")) {
                "<a href='https://bbs.nga.cn${it.groupValues[1]}'>${it.groupValues[2]}</a>"
            } else {
                "<a href='${it.groupValues[1]}'>${it.groupValues[2]}</a>"
            }
        }

        return collapse(StringEscapeUtils.unescapeHtml4(content)) // 处理 &#xxx; 此类编码字符
                .replace("\\[img]([\\s\\S]*?)\\[/img]".toRegex(), replaceImgFunc) // 处理 [img]
                .replace("\\[url]([\\s\\S]*?)\\[/url]".toRegex(), replaceUrlFunc)   // 处理 [url]asd[/url]
                .replace("\\[url=([\\s\\S]*?)]([\\s\\S]*?)\\[/url]".toRegex(), replaceUrl2Func) // 处理[url=xxx]asd[/url]
                .replace("===([\\s\\S]*?)===".toRegex(), "<h4>$1</h4>") // 处理 ===标题===
                .replace("\\[color=([a-z]+?)]([\\s\\S]*?)\\[/color]".toRegex(), "<font color='$1'>$2</font>") // 处理[color=xx]asd[/color]
                .replace("\\[align=([a-z]+?)]([\\s\\S]*?)\\[/align]".toRegex(), "<div style='text-align:\$1'>$2</div>") // 处理[align=xx]asd[/align]
                .replace("\\[size=(\\d+)%]".toRegex(), "<span style='font-size:$1%;line-height:$1%'>")  // 处理 [size=?%]
                .replace("[/size]", "</span>") // [/size]
                .replace("\\[font=([^\\[|\\]]+)]".toRegex(), "<span style='font-family:$1'>") // 处理 [font=?]
                .replace("[/font]", "</span>") // [/font]
                // uid = $4
                .replace("\\[b]Reply to \\[pid=(\\d+)?,(\\d+)?,(\\d+)?]Reply\\[/pid] Post by \\[uid=(\\d+)?]([\\s\\S]*?)\\[/uid] \\(([\\s\\S]*?)\\)\\[/b]".toRegex(),
                        "<blockquote><a href='https://bbs.nga.cn/read.php?searchpost=1&pid=$1'>Reply</a> by $5 ($6)</blockquote>")
                .replace("\\[([/]?(b|u|i|del|list|tr|td))]".toRegex(), "<$1>")    // 处理 b, u, i, del, list, tr, td
                .replace("[table]", "<div><table><tbody>")
                .replace("[/table]", "</tbody></table></div>")
                .replace("\\[td([\\d]{1,3})+]".toRegex(), "<td style='width:$1%;'>")    // 处理 [td20]
                .replace("\\[td (rowspan|colspan)=([\\d]+?)]".toRegex(), "<td $1='$2'")
                .replace("<([/]?(table|tbody|tr|td))><br/>".toRegex(), "<$1>") // 处理表格外面的额外空行
                .replace("[-]{6,}".toRegex(), "<h5></h5>")
                .replace("\\[\\*](.+?)<br/>".toRegex(), "<li>$1</li>")  // 处理 [*]
                .replace("[quote]", "<blockquote>") // 处理 [quote]
                .replace("[/quote]", "</blockquote>")   // 处理 [/quote]
    }

    fun getImageListByHtml(html: String): List<String> {
        val pattern = Pattern.compile("<img src='([\\s\\S]*?)' >")
        val matcher = pattern.matcher(html)
        val list = mutableListOf<String>()
        while (matcher.find()) {
            list.add(matcher.group(1))
        }
        return list
    }

    /**
     * 处理点击展开此类标签
     */
    private fun collapse(content: String): String {
        var c = content
        val pattern = Pattern.compile("\\[collapse(.*?)](.*?)\\[/collapse]")
        var matcher = pattern.matcher(c)
        var index = 0
        while (matcher.find()) {
            var title = matcher.group(1)
            c = matcher.group(2)
            c = String.format("<blockquote id=collapse%s style='display:none' >%s</blockquote>", index, c)
            if (TextUtils.isEmpty(title)) {
                c = String.format("<button id=collapseBtn%s onclick='toggleCollapse(%s)'>点击展开内容</button>%s", index, index, c)
            } else {
                title = title.substring(1)
                c = String.format("<button id=collapseBtn%s onclick='toggleCollapse(%s,'%s')'>点击展开内容 : %s</button>%s", index, index, title, title, c)
            }
            c = matcher.replaceFirst(c)
            matcher = pattern.matcher(c)
            index++
        }
        return c
    }
}