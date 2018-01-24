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