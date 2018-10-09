package xzy.loshine.nga

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun a() {
        var json = "{\"data\":{\"__MESSAGE\":{\"0\":15,\"1\":\"访客不能直接访问\",\"2\":\"<br/><script>\\nfunction replace_txt(o){\\nwhile(o){\\n\tif(o.nodeType==3){\\n\t\tif(o.nodeValue.indexOf('访客不能直接访问')!=-1)\\n\t\t\to.nodeValue='加载中…'\\n\t\tif(o.nodeValue.indexOf('(ERROR:15')!=-1)\\n\t\t\to.nodeValue=' '\\n\t\t}\\n\to=o.previousSibling\\n\t}\\n}\\n</script><img src='about:blank' style='display:none' onerror='replace_txt(this)'/>\\n<script>\\nvar d = location.host;\\nvar x = new Date()\\nx.setTime(x.getTime()+1200000)\\ndocument.cookie = 'guestJs=1539067137;domain='+d+';path=/;expires='+x.toUTCString();\\nx.setTime(0)\\ndocument.cookie = 'lastpath=0;domain='+d+';path=/;expires='+x.toUTCString();\\nd = function(x,y,z,t){document.write('<'+x+' '+y+'=\\\"'+z+'\\\" style=\\\"color:gray\\\">'+t+'</'+x+'>')}\\ne = function(){\\nvar l = window.location,r = Math.floor(Math.random()*1000),s = (l.search.replace(/(?:\\x5C?|&)rand=\\x5Cd+/,'')+'&rand='+r).replace(/^&/,'?')\\nreturn l.pathname+s\\n}\\nd('a','href',e(),'如不能自动跳转 可点此链接')\\n\\nwindow.setTimeout(function(){\\nwindow.location.replace(e())\\n},1000)\\n</script><style>#vp_link {display:none}</style><br/><a href='/nuke.php?__lib=view_privilege&__act=view&id=-7' id='vp_link'>查看所需的权限/条件</a>\",\"3\":200}},\"encode\":\"gbk\",\"time\":1539067137}"
        json = json.replace(Regex(",\"2\":\"[\\s\\S]*,\"3\":"), ",\"3\":")
        println(json)
    }

    @Test
    fun format(){
        println("-7.0".toIntOrNull())
    }
}
