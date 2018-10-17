package xzy.loshine.nga.utils

import org.apache.commons.text.StringEscapeUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentParser @Inject constructor() {

    fun parse(content: String): String {

        val replaceImgFunc: (MatchResult) -> CharSequence = {
            if (it.groupValues[1].startsWith("./mon_")) {
                "<img src=\"https://img.nga.178.com/attachments${it.groupValues[1].substring(1)}\" />"
            } else {
                "<img src=\"${it.groupValues[1]}\" />"
            }
        }
        val replaceUrlFunc: (MatchResult) -> CharSequence = {
            if (it.groupValues[1].startsWith("/")) {
                "<a href=\"https://bbs.nga.cn${it.groupValues[1]}\">[站内链接]</a>"
            } else {
                "<a href=\"${it.groupValues[1]}\">${it.groupValues[1]}</a>"
            }
        }
        val replaceUrl2Func: (MatchResult) -> CharSequence = {
            if (it.groupValues[1].startsWith("/")) {
                "<a href=\"https://bbs.nga.cn${it.groupValues[1]}\">${it.groupValues[2]}</a>"
            } else {
                "<a href=\"${it.groupValues[1]}\">${it.groupValues[2]}</a>"
            }
        }

        return StringEscapeUtils.unescapeHtml4(content) // 处理 &#xxx; 此类编码字符
                .replace("\\[img]([\\s\\S]*?)\\[/img]".toRegex(), replaceImgFunc) // 处理 [img]
                .replace("\\[url]([\\s\\S]*?)\\[/url]".toRegex(), replaceUrlFunc)   // 处理 [url]asd[/url]
                .replace("\\[url=([\\s\\S]*?)]([\\s\\S]*?)\\[/url]".toRegex(), replaceUrl2Func) // 处理[url=xxx]asd[/url]
                .replace("===([\\s\\S]*?)===".toRegex(), "<h4>$1</h4>") // 处理 ===标题===
                .replace("\\[color=([a-z]+?)]([\\s\\S]*?)\\[/color]".toRegex(), "<font color=\"$1\">$2</font>") // 处理[color=xx]asd[/color]
                .replace("\\[align=([a-z]+?)]([\\s\\S]*?)\\[/align]".toRegex(), "<div style=\"text-align:\$1\">$2</div>") // 处理[align=xx]asd[/align]
                .replace("\\[([/]?(b|u|i|del|list|tr|td))]".toRegex(), "<$1>")    // 处理 b, u, i, del, list, tr, td
                .replace("[table]", "<div><table cellspacing='0px' style='width:99.9%;'><tbody>")
                .replace("[/table]", "</tbody></table></div>")
                .replace("\\[td([\\d]{1,3})+]".toRegex(), "<td style=\"width:$1%;\">")    // 处理 [td20]
                .replace("\\[td rowspan=([\\d]+?)]".toRegex(),"<td rowspan=\"$1\"")
                .replace("\\[\\*](.+?)<br/>".toRegex(), "<li>$1</li>")  // 处理 [*]
                .replace("[quote]", "<blockquote>") // 处理 [quote]
                .replace("[/quote]", "</blockquote>")   // 处理 [/quote]
    }
}