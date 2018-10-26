function toggleCollapse(id, text) {
    var collapse = document.getElementById('collapse'+id);
    var button = document.getElementById('collapseBtn'+id);
    if (collapse.style.display == 'none') {
        collapse.style.display = '';
        if (text == null) {
            button.innerHTML = "点击收起内容"
        } else {
            button.innerHTML = "点击收起内容 : " + text;
        }
    } else {
        collapse.style.display = 'none';
        if (text == null) {
            button.innerHTML = "点击展开内容"
        } else {
            button.innerHTML = "点击展开内容 : " + text;
        }
    }
}