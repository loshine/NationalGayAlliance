var tables = document.getElementsByTagName("table");
for (var i = 0; i < tables.length; i++) {
  var table = tables[i];
  var brNodes = table.parentNode.getElementsByTagName("br")
  for (var j = brNodes.length - 1; j >= 0; j--) {
    var br = brNodes[j];
    br.parentNode.removeChild(br);
  }
}