// 引用不同图片的路径
var green_right = ctx + '/static/images/report/green-right.png',
    green_left = ctx + '/static/images/report/green-left.png',
    green_right2 = ctx + '/static/images/report/green-right2.png',
    green_left2 = ctx + '/static/images/report/green-left2.png',
    red_right = ctx + '/static/images/report/red-right.png',
    red_left = ctx + '/static/images/report/red-left.png',
    gray_right = ctx + '/static/images/report/red-right.png',
    gray_left = ctx + '/static/images/report/red-left.png',
    // gray_right = ctx+'/static/images/report/gray-right.png',
    // gray_left = ctx+'/static/images/report/gray-left.png',
    gray_right2 = ctx + '/static/images/report/red-right2.png',
    gray_left2 = ctx + '/static/images/report/red-left2.png',
    blue_right = ctx + '/static/images/report/yellow-right.png',
    blue_left = ctx + '/static/images/report/yellow-left.png',
    // blue_right = ctx+'/static/images/report/blue-right.png',
    // blue_left = ctx+'/static/images/report/blue-left.png',
    blue_right2 = ctx + '/static/images/report/yellow-right2.png',
    blue_left2 = ctx + '/static/images/report/yellow-left2.png',
    black_left2 = ctx + '/static/images/report/black-left2.png',
    black_right2 = ctx + '/static/images/report/black-right2.png',
    dot = ctx + '/static/images/report/dot.png';
// 跳转加载时渲染的线别
var line = '';
$(function () {


});
// 存储当前线路的健康度数据
var HealthUP, healthDOWN;
var clickSegmentName = '';
// 左边导航
var p = 43, page = 0;
// 表格数据的请求
var statis = {};
// 导出按钮导出周报
var objAll = {};
// 默认加载时渲染的线别
// var line = '7号线';

// var line = window.localStorage.getItem("line")
function eachartsPie(data, id) {
    var _data = All(data);
    var pieChart = echarts.init(document.getElementById('echarts-pie-' + id));
    var pieOption = {
        tooltip: {},
        legend: {
            left: 'center',
            data: ['AA', 'A', 'B', 'C', 'D']
        },
        series: [{
            type: 'pie',
            radius: '65%',
            center: ['50%', '60%'],
            data: [{
                value: _data.AA,
                name: 'AA'
            },
                {
                    value: _data.A,
                    name: 'A'
                },
                {
                    value: _data.B,
                    name: 'B'
                },
                {
                    value: _data.C,
                    name: 'C'
                },
                {
                    value: _data.D,
                    name: 'D'
                }
            ],

        }]
    };
    pieChart.setOption(pieOption, true);
}

function eachartsBar(xzdata, xjdata, id) {
    var barChart = echarts.init(document.getElementById('echarts-bar-' + id));
    var barOption = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['新增量', '消除量']
        },
        xAxis: [
            {
                type: 'category',
                data: ['AA', 'A', 'B', 'C', 'D'],
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '数量',
            },
        ],
        series: [
            {
                name: '新增量',
                type: 'bar',
                data: xzdata
            },
            {
                name: '消除量',
                type: 'bar',
                data: xjdata
            }

        ]
    };
    barChart.setOption(barOption, true);
    window.onresize = function () {
        barChart.resize();
        //myChart1.resize();    //若有多个图表变动，可多写
//https://blog.csdn.net/qq_38382380/article/details/80460729,参考
    }
}

// 表格隔行换色
function interlacing(className) {
    $.each($(className), function (i, n) {
        if (i % 2 != 0) {
            $(className).eq(i).css({"background-color": "#e7eef4"})
        }
    })
}


$('.export').click(function () {
    var weekAdd = [], weekDelete = [], weekAll = [];
    var weekdata = weekDataAll;
    $.each(weekdata, function (i, n) {
        weekdata[i]['line'] = '网络剩余'
    })
    var object = {
        statis: weekdata,
        weekAdd: statis.data.week.Storage,
        weekDelete: statis.data.week.Maintain,
        weekAll: statis.data.week.Storage
    };
    objAll = object
    optiontest()
    //项目文档的下载与删除
})

var downLoad = true;

function optiontest() {
    if (!downLoad) {
        alert("努力导出中...请稍等");
        return "";
    }
    downLoad = false;
    //获取上周时间
    var beforWeek = getTime(7) + " - " + getTime(1);
    objAll.showTime = beforWeek;
    var url = "./module/report/exportWord";
    var data = JSON.stringify({params: objAll});

    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject('Micrisoft.XMLHTTP');
    }


    xhr.open('post', url, true);    // 也可以使用POST方式，根据接口
    xhr.setRequestHeader('Content-Type', 'application/json')
    xhr.responseType = "blob";  // 返回类型blob
    // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
    xhr.onload = function () {
        // 请求完成
        if (this.status === 200) {
            // 返回200
            var blob = this.response;
            var reader = new FileReader();
            reader.readAsDataURL(blob);  // 转换为base64，可以直接放入a标签href
            reader.onload = function (e) {
                // 转换完成，创建一个a标签用于下载
                var a = document.createElement('a');
                a.download = "监护监测部周报（" + beforWeek + "）.docx";
                a.href = e.target.result;
                $("body").append(a);  // 修复firefox中无法触发click
                a.click();
                $(a).remove();
            }
        }
        downLoad = true;
    };
    // 发送ajax请求
    xhr.send(data);
}


/*
$('.bao-z').children('.bao-bgweek').addClass('bao-bg4')
$('.bao-z').click(function () {
    $(this).children('.bao-bgweek').addClass('bao-bg4')
    $('.bao-bgmonth').removeClass('bao-bg5')
    $('.bao-bgyear').removeClass('bao-bg6')
    // 获取eacharts需要的结存数据
    eachartsPie(statis.data.week.statis.All);
    echartParam(statis.data.week.statis.Add, statis.data.week.statis.Delete);
});
$('.bao-y').click(function () {
    $(this).children('.bao-bgmonth').addClass('bao-bg5')
    $('.bao-bgyear').removeClass('bao-bg6');
    $('.bao-bgweek').removeClass('bao-bg4');
    // 获取eacharts需要的结存数据
    eachartsPie(statis.data.month.statis.All);
    echartParam(statis.data.month.statis.Add, statis.data.month.statis.Delete);
})
$('.bao-n').click(function () {
    $(this).children('.bao-bgyear').addClass('bao-bg6')
    $('.bao-bgmonth').removeClass('bao-bg5');
    $('.bao-bgweek').removeClass('bao-bg4');
    // 获取eacharts需要的结存数据
    eachartsPie(statis.data.year.statis.All);
    echartParam(statis.data.year.statis.Add, statis.data.year.statis.Delete);
})*/

// 周报月报年报tab切换
$('.count-tit div').click(function () {
    var siblings = $(this).siblings();
    siblings.removeClass("bao-t");
    // $(this).siblings().css({
    //     "background-color": "rgb(217, 221, 235)",
    //     "color": "#000"
    // });
    $(this).addClass("bao-t");

    var oClass = $(this).find("p:first").attr("class");
    var tabType = $(this).attr("data-type");
    var find1 = $(this).find("p:first");
    find1.removeClass('bao-bg' + tabType + '-t');
    $.each(siblings, function (i, r) {
        var type = $(r).attr("data-type");
        var find = $(r).find("p:first");
        find.removeClass('bao-bg' + type + '-t');

    });
    $(this).find("p:first").addClass(oClass + '-t');
    $('.' + tabType).siblings().hide();
    $('.' + tabType).show();
    eachartsPie(statis.data[tabType].statis.All, tabType);
    echartParam(statis.data[tabType].statis.Add, statis.data[tabType].statis.Delete, tabType);
});

$('.menu-tit2').click(function () {
    $(this).css({"background": '#d2e4f4'}).siblings().css({"background": '#fff'})
})
$('.menu-btm2').click(function () {
    $(this).css({"background": '#d2e4f4'}).siblings().css({"background": '#fff'})
})

function slider() {
    $('.jiao2').click(function () {
        page = $('.page_line').scrollTop();
        $('.page_line').animate({scrollTop: page + p}, 300);
    });
    $('.jiao1').click(function () {
        page = $('.page_line').scrollTop();
        $('.page_line').animate({scrollTop: page - p}, 300);
    })
}


$.ajax({
    type: 'post',
    url: ctx + '/module/report/getFaultAnalyze',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify(new Object),
    success: function (data) {
        statis = data;
        // 月
        staticMethod(statis.data.month.statis, '.yueTl');
        zcjTab(statis.data.month.newFault, '.yueXZ');
        zcjTab(statis.data.month.Maintain, '.monthDel');
        zcjTab(statis.data.month.Storage, '.monthJc');
        // 年
        staticMethod(statis.data.year.statis, '.yearTl');
        zcjTab(statis.data.year.newFault, '.yearXZ');
        zcjTab(statis.data.year.Maintain, '.yearDel');
        zcjTab(statis.data.year.Storage, '.yearJc');

// 周
        staticMethod(statis.data.week.statis, '.blfenix');
        zcjTab(statis.data.week.newFault, '.addBH');
        zcjTab(statis.data.week.Maintain, '.weekXC');
        zcjTab(statis.data.week.Storage, '.weekAll');
        // 获取eacharts需要的结存数据
        eachartsPie(statis.data.week.statis.All, 'week');
        echartParam(statis.data.week.statis.Add, statis.data.week.statis.Delete, 'week');
        //先加载年报
        var $count = $('.count-tit .bao-btn');
        $.each($count, function (i, r) {
            if ($(r).attr("data-type") === 'year') {
                $(r).click();

            }
        })
    }
});

function All(aa) {
    var all = {
        AA: 0,
        A: 0,
        B: 0,
        C: 0,
        D: 0,
        sum: 0
    };
    $.each(aa, function (i, n) {
        if (n.FAULT_LEVEL == 'AA') {
            all.AA = n.BH_COUNT
            all.sum += n.BH_COUNT
        } else if (n.FAULT_LEVEL == 'A') {
            all.A = n.BH_COUNT
            all.sum += n.BH_COUNT
        } else if (n.FAULT_LEVEL == 'B' || n.FAULT_LEVEL == 'BB') {
            all.B += n.BH_COUNT
            all.sum += n.BH_COUNT
        } else if (n.FAULT_LEVEL == 'C') {
            all.C = n.BH_COUNT
            all.sum += n.BH_COUNT
        }
    })
    return all
}

function tjs(add, del, all) {
    var AA = {
        name: 'AA合计',
        add: 0,
        Delete: 0,
        All: 0
    };
    var A = {
        name: 'A合计',
        add: 0,
        Delete: 0,
        All: 0
    };
    var B = {
        name: 'B合计',
        add: 0,
        Delete: 0,
        All: 0
    };
    var C = {
        name: 'C合计',
        add: 0,
        Delete: 0,
        All: 0
    };
    var D = {
        name: 'D合计',
        add: 0,
        Delete: 0,
        All: 0
    }
    var sum = {
        name: '以上合计',
        add: 0,
        Delete: 0,
        All: 0
    }
    $.each(add, function (i, n) {
        if (i == 'AA') {
            AA.add = n
        } else if (i == 'A') {
            A.add = n
        } else if (i == 'B') {
            B.add = n
        } else if (i == 'C') {
            C.add = n
        } else if (i == 'D') {
            D.add = n
        } else if (i == 'sum') {
            sum.add = n
        }
    })
    $.each(del, function (i, n) {
        if (i == 'AA') {
            AA.Delete = n
        } else if (i == 'A') {
            A.Delete = n
        } else if (i == 'B') {
            B.Delete = n
        } else if (i == 'C') {
            C.Delete = n
        } else if (i == 'D') {
            D.Delete = n
        } else if (i == 'sum') {
            sum.Delete = n
        }
    })
    $.each(all, function (i, n) {
        if (i == 'AA') {
            AA.All = n
        } else if (i == 'A') {
            A.All = n
        } else if (i == 'B') {
            B.All = n
        } else if (i == 'C') {
            C.All = n
        } else if (i == 'D') {
            D.All = n
        } else if (i == 'sum') {
            sum.All = n
        }
    })
    var bb = [
        AA, A, B, C, D, sum
    ]
    return bb
}

// 存储周报表格数据到一个变量
var weekDataAll;

// 条例分析
function staticMethod(data, lei) {
    var resAll = All(data.All);
    var resAdd = All(data.Add);
    var resDel = All(data.Delete);
    var res = tjs(resAdd, resDel, resAll);
    weekDataAll = res;
    var th = '';
    $.each(res, function (i, n) {
        th += "<tr><td>" + n.name + "</td><td>" + n.add + "</td><td>" + n.Delete + "</td> <td>" + n.All + "</td></tr>"
    })
    $(lei).append(th)
    interlacing('.blfenix tr')
    interlacing('.yueTl tr')
    interlacing('.yearTl tr')

}

function zcjTab(data, lei) {
    var tr = '';
    $.each(data, function (i, n) {
        tr += "<tr><td>" + n.LINE_ID + "</td><td>" + n.START_STATIONNAME + "-" +
            n.END_STATIONNAME + "/" + n.UPDOWN + "</td><td>" + n.DUCT_CODE + "-" +
            n.DUCT_CODE2 + "</td><td>" + n.FAULT_TYPE + "</td><td>" + n.FAULT_LEVEL + "</td></tr>"
    })
    $(lei).append(tr);
}

// 导出数据封装一个json数据
function weekList(data, arr) {
    $.each(data, function (i, n) {
        var obj = {
            LINE_ID: '',
            START_STATIONNAME: '',
            END_STATIONNAME: '',
            UPDOWN: '',
            DUCT_CODE: '',
            DUCT_CODE2: '',
            FAULT_TYPE: '',
            FAULT_LEVEL: ''
        }
        obj.LINE_ID = n.LINE_ID;
        obj.START_STATIONNAME = n.START_STATIONNAME;
        obj.END_STATIONNAME = n.END_STATIONNAME;
        obj.UPDOWN = n.UPDOWN;
        obj.DUCT_CODE = n.DUCT_CODE;
        obj.DUCT_CODE2 = n.DUCT_CODE2;
        obj.FAULT_TYPE = n.FAULT_TYPE;
        obj.FAULT_LEVEL = n.FAULT_LEVEL;
        arr.push(obj)
    })
}

// $.each($('tbody tr'), function (i, n) {
//     if (i % 2 != 0) {
//         $('.blfenix tr').eq(i).css({"background-color": "#e7eef4"})
//     }
// });


function getTime(n) {
    var now = new Date();
    var year = now.getFullYear();
//因为月份是从0开始的,所以获取这个月的月份数要加1才行
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var day = now.getDay();
    // console.log(date);
//判断是否为周日,如果不是的话,就让今天的day-1(例如星期二就是2-1)
    if (day !== 0) {
        n = n + (day - 1);
    } else {
        n = n + day;
    }
    if (day) {
//这个判断是为了解决跨年的问题
        if (month > 1) {
            month = month;
        }
//这个判断是为了解决跨年的问题,月份是从0开始的
        else {
            year = year - 1;
            month = 12;
        }
    }
    now.setDate(now.getDate() - n);
    year = now.getFullYear();
    month = now.getMonth() + 1;
    date = now.getDate();
    console.log(n);
    s = year + "." + (month < 10 ? ('0' + month) : month) + "." + (date < 10 ? ('0' + date) : date);
    return s;
}

var common = {};

function lineSort(a, b) {
    var asub = a.LINE_NAME;
    var bsub = b.LINE_NAME;
    return parseInt(asub.substring(0, asub.length - 2)) - parseInt(bsub.substring(0, bsub.length - 2));
}


$.ajax({
    type: "post",
    url: ctx + "/module/data/base/getStationInfo",
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({}),
    success: function (data) {
        if (data.code == 200) {
            data.data.stations.sort(lineSort);
            common = data.data.stations;
            xianAll(common);
            line = ($("#lineCode").val() != '') ? $("#lineCode").val() + '号线' : '1号线';
            state(line, common);
            slider();
            // 处理默认线别左侧显示的样式
            var num = parseInt(line.substring(0, line.length - 2)) - 1;
            $('.line').eq(num).css({"box-shadow": "0px 0px 4px #333"});
            $('.page_line').animate({scrollTop: num * p + "px"}, 300);
            page = num;

        }
    }
})

//    渲染线别
function xianAll(aa) {
    var htmlLine = '';
    $.each(aa, function (i, n) {
        htmlLine += " <div class=' line' data-line='" + n.LINE_NAME + "'>" +
            "<p class='line-font'>" + n.LINE_NAME + "</p>" +
            "<p class='line-col' style='background-color:" + n.LINE_COLOR + "'></p>" +
            "</div>"
    });
    $('.page_chLine').html(htmlLine);
}

// 点击左侧线路动态改变线路图
$(document).on('click', '.line', function () {
    line = $(this).attr('data-line');
    $(this).css({"box-shadow": "0px 0px 4px #333"}).siblings().css({"box-shadow": "none"})
    state(line, common);
})

// 16,17插入空div
function divInto(num) {
    var html = '';
    for (var i = 0; i < num; i++) {
        html += "<div class=\"line-station\"></div>"
    }
    return html;
}

// 判断右边线路图显示几号线
function state(line, data) {
    $.each(data, function (i, n) {
        if (line == '5号线') {
            if (n.LINE_NAME == line) {
                fiveline(n.STATIONS)
            }
        } else if (line == '17号线') {
            if (n.LINE_NAME == line) {
                var resDiv = divInto(10);
                linePhoto(n.STATIONS, 30, resDiv);
            }
        } else if (line == '16号线') {
            if (n.LINE_NAME == line) {
                var resDiv = divInto(10);
                linePhoto(n.STATIONS, 30, resDiv);
            }
        } else if (line == '10号线') {
            if (n.LINE_NAME == line) {
                linePhoto(n.STATIONS, 0);
                $('.line-across3 .line-station:last-child .station-name').addClass('station-nameTen')
            }
        } else if (n.LINE_NAME == line) {
            linePhoto(n.STATIONS, 30)
        }
    })
}

/**
 * 线路区间图内容填充
 * @param aa
 * @param padding
 * @param resDiv
 */
function linePhoto(aa, padding, resDiv) {
    // 设置tit.right的padding值
    $('.tit-right').css({paddingLeft: padding + "px"});
    var htmlSta1 = '', htmlSta3 = '';
    $('.line-across2').html(' ');
    $('.line-across1').html(' ');
    $('.line-across3').html(' ');
    // 去除margin-left值
    $('.line-across1').css({'margin-left': '60px', 'margin-top': '5px'});
    $('.line-across2').css({'margin-left': '-5px', 'margin-top': '40px'});
    $('.line-across3').css({'margin-left': '-22px'});
    $.each(aa, function (i, n) {
        if (i <= 10) {
            htmlSta1 += "<div class='line-station' data-gray=" + n.IS_TUNNEL + "><div class='dot-station dot-parent'>" +
                "<img src='" + dot + "'> <p class='station-name'> " +
                n.STATION_NAME + "</p></div><p class='line-up-1 line-top arrow-left'><img src=" + green_left + " class='line-img img-up'></p>" +
                "<p class='line-down-1 line-bottom arrow-right'><img src=" + green_right + " class='line-img img-down'></p></div>"
        } else if (i > 10 && i <= 22) {
            var htmlSta2 = '';
            htmlSta2 += "<div class='line-station' data-gray=" + n.IS_TUNNEL + "><div class='dot-station-last dot-parent'>" +
                "<img src='" + dot + "'> <p class='station-name'> " +
                n.STATION_NAME + "</p></div> <p class='line-down-2 line-bottom arrow-left'><img src=" + green_left + " class='line-img img-down'></p>" +
                "<p class='line-up-2 line-top arrow-right'><img src=" + green_right + " class='line-img img-up'></p></div>"
            $('.line-across2').prepend(htmlSta2);
        } else if (i > 22) {
            htmlSta3 += "<div class='line-station' data-gray=" + n.IS_TUNNEL + "><div class='dot-station dot-parent'>" +
                "<img src='" + dot + "'> <p class='station-name'> " +
                n.STATION_NAME + "</p></div><p class='line-up  line-top arrow-left'><img src=" + green_left + " class='line-img img-up'></p> " +
                "<p class='line-down  line-bottom arrow-right'><img src=" + green_right + " class='line-img img-down'></p></div>"
        }
        ;
    })
    $('.line-across1').append(htmlSta1);
    $('.line-across3').append(htmlSta3);
    $('.line-across2').prepend(resDiv);
    // 查询最后一个子元素删除线
    $('.line-across3 .line-station:last-child>p').css({display: 'none'});
    if (resDiv) {
        $('.line-across2 .line-station:eq(-2)>p').css({display: 'none'});
        $('.line-across1').css({marginTop: '50px'})
    }
    // 是否经过隧道
    health('.tit-right .line-station');
    middle(line)
}

// 处理5号线线路图显示
function fiveline(aa) {
    $('.line-across2').html(' ');
    $('.line-across1').html(' ');
    $('.line-across3').html(' ');
    var htmlSta1 = '';
    $.each(aa, function (i, n) {
        if (i <= 9) {
            htmlSta1 += "<div class='line-station' data-gray=" + n.IS_TUNNEL + "><div class='dot-station dot-parent'>" +
                "<img src=" + dot + "> <p class='station-name'> " +
                n.STATION_NAME + "</p></div><p class='line-up-1 line-top'><img src=" + green_left + " class='line-img img-up'></p>" +
                "<p class='line-down-1 line-bottom'><img src=" + green_right + " class='line-img img-down'/></p></div>"
        } else if (i > 9) {
            var htmlSta2 = '';
            htmlSta2 += "<div class='line-station' data-gray=" + n.IS_TUNNEL + "><div class='dot-station-last dot-parent'>" +
                "<img src=" + dot + "> <p class='station-name'> " +
                n.STATION_NAME + "</p></div> <p class='line-down-2 line-bottom'><img src=" + green_left + " class='line-img img-down'/></p>" +
                "<p class='line-up-2 line-top'><img src=" + green_right + " class='line-img img-up'></p></div>"
            $('.line-across2').prepend(htmlSta2)
        }
    })
    $('.line-across1').append(htmlSta1);
    // 设置margin-left值
    var width = $('.line-station').width();
    $('.line-across1').css({'margin-left': "90px"});
    $('.line-across1').css({'margin-top': "60px"});
    $('.line-across2').css({'margin-left': "103px"});
    $('.line-across3').css({'margin-left': width * 2});
    // 查询最后一个子元素删除线
    $('.line-across2 .line-station:first-child>p').css({display: 'none'})
    $('.line-across2 .line-station:first-child').css({
        "transform": "rotate(0)",
        "z-index": "3"
    })
    $('.line-across2 .line-station:first-child .station-name').css({
        "transform": "translate(0,0)rotate(0)",
        "text-align": "right",
    })

    // 是否经过隧道
    health('.tit-right .line-station');
    middle(line);
}

// 处理11号线线路图显示
// function elevenLine(aa){

// }
// 点击线，弹出站与站之间的上下行和区间
// 第三行
$(document).on('click', '.line-up', function () {
    var a = $(this).parent().children('.dot-parent').text();
    var b = $(this).parent().next().children('.dot-parent').text();
    console.log(a + ' - ' + b + '上行');
});
$(document).on('click', '.line-down', function () {
    var a = $(this).parent().children('.dot-parent').text();
    var b = $(this).parent().next().children('.dot-parent').text();
    console.log(a + ' - ' + b + '下行');
});
// 第二行
$(document).on('click', '.line-up-2', function () {
    if ($(this).parent().prev().length > 0) {
        var a = $(this).parent().children('.dot-parent').text();
        var b = $(this).parent().prev().children('.dot-parent').text();
        console.log(a + ' - ' + b + '上行');
    } else {
        var a = $(this).parent().children('.dot-parent').text();
        var c = $(this).parent().parent().next().children('.line-station').eq(0).text();
        console.log(a + ' - ' + c + '上行');
    }

});
$(document).on('click', '.line-down-2', function () {
    if ($(this).parent().prev().length > 0) {
        var a = $(this).parent().children('.dot-parent').text();
        var b = $(this).parent().prev().children('.dot-parent').text();
        console.log(a + ' - ' + b + '下行');
    } else {
        var a = $(this).parent().children('.dot-parent').text();
        var c = $(this).parent().parent().next().children('.line-station').eq(0).text();
        console.log(a + '-' + c + '下行');
    }

});
//  第一行
$(document).on('click', '.line-up-1', function () {
    if ($(this).parent().next().length > 0) {
        var a = $(this).parent().children('.dot-parent').text();
        var b = $(this).parent().next().children('.dot-parent').text();
        // console.log(a + ' - ' + b + '上行');
        // clickSegmentName = a + ' - ' + b + '上行';
    } else {
        var a = $(this).parent().children('.dot-parent').text();
        var c = $(this).parent().parent().next().children('.line-station').last().text();
        // console.log(a + '-' + c + '上行');
        // clickSegmentName = a + ' - ' + c + '上行';
    }

});
$(document).on('click', '.line-down-1', function () {
    if ($(this).parent().next().length > 0) {
        var a = $(this).parent().children('.dot-parent').text();
        var b = $(this).parent().next().children('.dot-parent').text();
        //console.log(a + ' - ' + b + '下行');
    } else {
        var a = $(this).parent().children('.dot-parent').text();
        var c = $(this).parent().parent().next().children('.line-station').last().text();
        //console.log(a + '-' + c + '下行');
    }

});
// 点击站区间高亮显示右箭头
$(document).on('click', '.arrow-right', function () {
    var dataGray = $(this).parent().attr("data-gray");
    // if ($(this).attr('data-src') == undefined) {
    //     var higlight = $(this).children().attr('src');
    //     $(this).attr('data-src', higlight);
    // }
    // loopup(HealthUP, '.tit-right .line-top')
    // loopdown(healthDOWN, '.tit-right .line-bottom');
    // if ($(this).parent().attr("data-gray") == "1") {
    //     var health = $(this).attr('data-top');
    //     if (health == "0" || health == "1" || health == undefined) {
    //         $(this).find('img').attr('src', gray_right2)
    //     } else if (health == "2" || health == "3") {
    //         $(this).find('img').attr('src', blue_right2)
    //     } else if (health == "4" || health == "5") {
    //         $(this).find('img').attr('src', green_right2)
    //     }
    // }

    // $('.arrow-right').each(function (i, n) {
    //     if ($('.arrow-right').eq(i).attr('data-src') != undefined) {
    //         $(this).children().attr("src", $(this).attr('data-src'));
    //     }
    // })
    // $('.arrow-left').each(function (i, n) {
    //     if ($('.arrow-left').eq(i).attr('data-src') != undefined) {
    //         $(this).children().attr("src", $(this).attr('data-src'));
    //     }
    // })
    if(dataGray==1){
        window.open(ctx + "/segment?clickSegmentName=" + clickSegmentName, '_blank');
    }
});
// 点击站区间高亮显示左箭头箭头
$(document).on('click', '.arrow-left', function () {
    var dataGray = $(this).parent().attr("data-gray");
    // if ($(this).attr('data-src') == undefined) {
    //     var higlight = $(this).children().attr('src');
    //     $(this).attr('data-src', higlight);
    // }
    // $('.arrow-left').each(function (i, n) {
    //     if ($('.arrow-left').eq(i).attr('data-src') != undefined) {
    //         $(this).children().attr("src", $(this).attr('data-src'));
    //     }
    // })
    // $('.arrow-right').each(function (i, n) {
    //     if ($('.arrow-right').eq(i).attr('data-src') != undefined) {
    //         $(this).children().attr("src", $(this).attr('data-src'));
    //     }
    // })
    // if ($(this).children().attr('src') != red_left) {
    //     $(this).children().attr('src', red_right);
    // }
    // loopup(HealthUP, '.tit-right .line-top')
    // loopdown(healthDOWN, '.tit-right .line-bottom');
    // if ($(this).parent().attr("data-gray") == "1") {
    //     var health = $(this).attr('data-top');
    //     if (health == "0" || health == "1" || health == undefined) {
    //         $(this).find('img').attr('src', gray_left2)
    //     } else if (health == "2" || health == "3") {
    //         $(this).find('img').attr('src', blue_left2)
    //     } else if (health == "4" || health == "5") {
    //         $(this).find('img').attr('src', green_left2)
    //     }
    // };
    if(dataGray==1) {
        window.open(ctx + "/segment?clickSegmentName=" + clickSegmentName, '_blank');
    }
});

// 根据是否隧道显示不同的颜色
function health(bieming) {
    $(bieming).each(function (i, n) {
        if ($(bieming).eq(i).attr('data-gray') == '0' || $(bieming).eq(i).attr('data-gray') == 'undefined') {
            $(this).find('img').eq(1).attr('src', black_left2)
            $(this).find('img').eq(2).attr('src', black_right2)
        }
    })
};

// 地铁健康度下行
function loopup(data, top) {
    $.each(data, function (i, n) {
        $(top).each(function (j, v) {
            if (j == n.START_STATION_CODE - 1) {
                $(top).eq(j).attr("data-top", n.MIDDLE)
            }
        })
    })
    $(top).each(function (i, n) {
        if ($(top).parent().eq(i).attr("data-gray") == "1") {
            var attrBottom = $(top).eq(i).attr('data-top')
            if (attrBottom == "0" || attrBottom == '1' || attrBottom == undefined) {
                if ($(this).is(".line-up-2")) {
                    $(this).find('img').attr('src', gray_right);
                } else {
                    $(this).find('img').attr('src', gray_left);
                }
            } else if (attrBottom == "2" || attrBottom == '3') {
                if ($(this).is(".line-up-2")) {
                    $(this).find('img').attr('src', blue_right);
                } else {
                    $(this).find('img').attr('src', blue_left);
                }
            } else if (attrBottom == "4" || attrBottom == '5') {
                if ($(this).is(".line-up-2")) {
                    $(this).find('img').attr('src', green_right);
                } else {
                    $(this).find('img').attr('src', green_left);
                }
            }
        }
    })
};

// 地铁健康度上行
function loopdown(data, top) {
    $.each(data, function (i, n) {
        $(top).each(function (j, v) {
            if (j == n.START_STATION_CODE - 1) {
                $(top).eq(j).attr("data-top", n.MIDDLE)
            }
        })
    });
    $(top).each(function (i, n) {
        var attrTop = $(top).eq(i).attr('data-top')
        if ($(top).parent().eq(i).attr("data-gray") == "1") {
            if (attrTop == "0" || attrTop == '1' || attrTop == undefined) {
                if ($(this).is(".line-down-2")) {
                    $(this).find('img').attr('src', gray_left);
                } else {
                    $(this).find('img').attr('src', gray_right);
                }
            } else if (attrTop == "2" || attrTop == '3') {
                if ($(this).is(".line-down-2")) {
                    $(this).find('img').attr('src', blue_left);
                } else {
                    $(this).find('img').attr('src', blue_right);
                }
            } else if (attrTop == "4" || attrTop == '5') {
                if ($(this).is(".line-down-2")) {
                    $(this).find('img').attr('src', green_left);
                } else {
                    $(this).find('img').attr('src', green_right);
                }
            }
        }

    })
}

function middle(line) {
    $.ajax({
        type: "get",
        url: ctx + "/module/report/healthMarck",
        // url: ctx + "/static/js/mock/lineHealth.json",
        dataType: "json",
        contentType: "application",
        success: function (data) {
            $.each(data, function (i, n) {
                if (n.LINE_NAME == line) {
                    HealthUP = n[0];
                    healthDOWN = n[1];
                    debugger
                    loopup(HealthUP, '.tit-right .line-top');
                    loopdown(healthDOWN, '.tit-right .line-bottom');
                }
            })
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function echartParam(Add, Delete, tabType) {
    xzdata = All(Add);
    xjdata = All(Delete);
    var arr1 = [], arr2 = [];
    $.each(xzdata, function (i, n) {
        if (i != 'sum') {
            arr1.push(n)
        }
    });
    var arr2 = [];
    $.each(xjdata, function (i, n) {
        if (i != 'sum') {
            arr2.push(n)
        }
    });
    eachartsBar(arr1, arr2, tabType)
}
