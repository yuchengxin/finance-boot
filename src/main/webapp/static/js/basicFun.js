/**
 * Created by che on 2018/1/24.
 */


function getMoth(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHours = oDate.getHours(),
        oMinutes = oDate.getMinutes(),
        oSeconds = oDate.getSeconds();
//				oTime =oYear+"-"+oMonth+"-"+oDay+" "+oHours+":"+oMinutes+":"+oSeconds;//最后拼接时间
    oTime =oYear+"-"+oMonth+"-"+oDay;//最后拼接时间
    return oTime;
};


function clearGrid(gridId){
    var item = $( '#'+gridId).datagrid('getRows');
    if (item) {
        for (var i = item.length - 1; i >= 0; i--) {
            var index = $( '#'+gridId).datagrid('getRowIndex', item[i]);
            $( '#'+gridId).datagrid('deleteRow', index);
        }
    }
}

function dateToLong(v){
    var d = new Date();
    var dArr = v.split('-');
    d.setYear(dArr[0]);
    d.setMonth(dArr[1] - 1);
    d.setDate(dArr[2]);
    return d.getTime();
}


function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}