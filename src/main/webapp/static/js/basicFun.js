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

function getMonth(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHours = oDate.getHours(),
        oMinutes = oDate.getMinutes(),
        oSeconds = oDate.getSeconds();
//				oTime =oYear+"-"+oMonth+"-"+oDay+" "+oHours+":"+oMinutes+":"+oSeconds;//最后拼接时间
    oTime =oYear+"-"+oMonth;//最后拼接时间
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

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return true;
    }
}


function databoxMonth(databoxId){
    $('#'+databoxId).datebox({
        //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
        onShowPanel: function () {
            //触发click事件弹出月份层
            span.trigger('click');
            if (!tds)
            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                setTimeout(function() {
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function(e) {
                        //禁止冒泡执行easyui给月份绑定的事件
                        e.stopPropagation();
                        //得到年份
                        var year = /\d{4}/.exec(span.html())[0] ,
                        //月份
                        //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1;
                            month = parseInt($(this).attr('abbr'), 10);

                        //隐藏日期对象
                        $('#attYearMonth').datebox('hidePanel')
                            //设置日期的值
                            .datebox('setValue', year + '-' + month);
                    });
                }, 0);
        },
        //配置parser，返回选择的日期
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth());
        formatter: function (d) {
            var currentMonth = (d.getMonth()+1);
            var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
            return d.getFullYear() + '-' + currentMonthStr;
        }
    });

    //日期选择对象
    var p = $('#attYearMonth').datebox('panel'),
    //日期选择对象中月份
        tds = false,
    //显示月份层的触发控件
        span = p.find('span.calendar-text');
    var curr_time = new Date();

    //设置前当月
    $("#attYearMonth").datebox("setValue", myformatter(curr_time));
}

//格式化日期
function myformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}


function init_yearMonth(id){
    var db = $('#'+id);
    db.datebox({
        editable:false,
        prompt:'选择年月',
        validType:[],
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                    db.datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                });
            }, 0);
            yearIpt.unbind();//解绑年份输入框中任何事件
            $(yearIpt).attr('readonly',true);//年份只读
            $(yearIpt).css('border-color','white');//边框去掉
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) { return d.getFullYear() + '-' + (d.getMonth() + 1);/*getMonth返回的是0开始的，忘记了。。已修正*/ }
    });
    var p = db.datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        aToday = p.find('a.datebox-current'),
        yearIpt = p.find('input.calendar-menu-year'),//年份输入框
    //显示月份层的触发控件
        span = aToday.length ? p.find('div.calendar-title span') ://1.3.x版本
            p.find('span.calendar-text'); //1.4.x版本
    if (aToday.length) {//1.3.x版本，取消Today按钮的click事件，重新绑定新事件设置日期框为今天，防止弹出日期选择面板
        aToday.unbind('click').click(function () {
            var now=new Date();
            db.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
        });
    }
}