var diseX;
var diseThis;
var diseSum;
var repThis;
var repSum;
var dise_echarts;
var e_line;//雷达图echarts
var lineOffset = 0;
var lineLength;
var common = {remPx: null};
//缓存雷达图Data
var lineDate;
//缓存报警项目Date
var warning;
// 雷达图滑动当前下标
var lineColor;
var cjslwiper = {cjSwiper: '', slSwiper: ''};

$(document).ready(function () {
    var location = window.location + "";

});
$(".echar_title_bt").click(function () {
    if ($(this).text() == '变化值统计') {
        $(this).removeClass('echar_rep_none');
        $(this).addClass('echar_rep_select');
        $(this).prev().removeClass('echar_title_dise');
        $(this).prev().addClass('echar_dise_none');
        var dise_option = getdiseOption({
            xdata: diseX,
            data: [diseSum, repSum]
        });
        dise_echarts.setOption(dise_option, true);
    } else {
        $(this).removeClass('echar_dise_none');
        $(this).addClass('echar_title_dise');

        $(this).next().removeClass('echar_rep_select');
        $(this).next().addClass('echar_rep_none');
        var dise_option = getdiseOption({
            xdata: diseX,
            data: [diseThis, repThis]
        });
        dise_echarts.setOption(dise_option, true);
    }
});
getRem(1366, 100);
window.onresize = function () {
    getRem(1366, 100)
};

function getRem(pwidth, prem) {
    var html = document.getElementsByTagName("html")[0];
    var oWidth = document.body.clientWidth || document.documentElement.clientWidth;
    if (oWidth <= 1366) {
        oWidth = 1366;
    }
    html.style.fontSize = oWidth / pwidth * prem + "px";
    common.remPx = oWidth / pwidth;
    initEcharts();
}

function initEcharts() {
    if (lineDate != undefined) {
        initCheck("line_box", lineDate);
        e_line.resize();
        dise_echarts.resize();
    }
    if (warning != undefined) {
        cjslwiper.cjSwiper.destroy();
        cjslwiper.slSwiper.destroy();
        initSilde("swiper_box", warning.astrWarning, 'cjSwiper');
        initSilde("swiper_box_2", warning.settWarning, 'slSwiper');
    }
}

function initDynamicInfo() {
    var nowDay = new Date();
    nowDay.setTime(nowDay.getTime() - 7 * 24 * 60 * 60 * 1000);
    var time = String(nowDay.getFullYear()) + String(nowDay.getMonth() + 1) + String(nowDay.getDate());
    $.ajax({
        type: "get",
        url: ctx + "/module/visual/dynamicInfo",
        data: {"time": '20191201'},
        dataType: 'json',
        success: function (data) {
            if (data.code == '200') {
                $("#dynamic-info .chart-loader").css("display", "none");
                $("#dynamic-info .flex-one-box").css("display", "block");
                initDynamicSwiper("dise_info_cont", data.data.dise);
                initUl("ul_repair", data.data.repir);
                $('#repair_scroll').vTicker({
                    showItems: 5,
                    pause: 3000
                });
                // $(".li_action").click(function () {
                //     var url = $(this).data("url");
                //     var location = window.location + "";
                //     var boo = location.substr(0, location.indexOf('home'));
                //     window.location.href = boo + url;
                // });
            }
        }
    });
}

function initDynamicSwiper(id, data) {
    $("#" + id).empty();
    $("#aa_count").text(data.AA.length);
    $("#a_count").text(data.A.length);
    $.each(data.A, function (i, n) {
        if (n.IMAGE_URL == undefined || n.IMAGE_URL.length < 1) {
            n.IMAGE_URL = ctx + "/static/images/zwtp.jpg";
        }

        var str = " <div class=\"swiper-slide\"><div class=\"img_div\"><img src='" + n.IMAGE_URL + "' class=\"swiper-lazy swiper_dise_img\"/></div><div class=\"desc_div\"><div  class=\"dise_item_info\">线路:&ensp;" + n.LINE_ID + "</div>\n" +
            "<div  class=\"dise_item_info\">区间:&ensp;" + n.START_STATIONNAME + "-" + n.END_STATIONNAME + "</div><div  class=\"dise_item_info\">类型:&ensp;" + n.FAULT_TYPE + "</div>\n" +
            "<div  class=\"dise_item_info\">等级:&ensp;A</div></div></div>";
        $("#" + id).append(str);
    });
    $.each(data.AA, function (i, n) {
        if (n.IMAGE_URL == undefined || n.IMAGE_URL.length < 1) {
            n.IMAGE_URL = ctx + "/static/images/zwtp.jpg";
        }
        var str = " <div class=\"swiper-slide\"><div class=\"img_div\"><img src='" + n.IMAGE_URL + "' class=\"swiper-lazy swiper_dise_img\"/></div><div class=\"desc_div\"><div  class=\"dise_item_info\">线路:&ensp;" + n.LINE_ID + "</div>\n" +
            "<div  class=\"dise_item_info\">区间:&ensp;" + n.START_STATIONNAME + "-" + n.END_STATIONNAME + "</div><div  class=\"dise_item_info\">类型:&ensp;" + n.FAULT_TYPE + "</div>\n" +
            "<div  class=\"dise_item_info\">等级:&ensp;AA</div></div></div>";
        $("#" + id).append(str);
    });
    var desc = new Swiper({
        el: '.' + id,
        paginationClickable: true,
        slideToClickedSlide: true,
        lazy: true,
        mousewheel: {
            enabled: true,
        },
        loop: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
    desc.lazy.load();
}

function initlineInfo() {
    $.ajax({
        type: "get",
        url: ctx + "/module/visual/lineInfo",
        dataType: 'json',
        success: function (data) {
            if (data.code == '200') {
                $("#line-echarts .chart-loader").css("display", "none");
                $("#line-echarts .content_body").css("display", "block");
                var line_dom = document.getElementById("echar_line");
                e_line = echarts.init(line_dom);
                lineDate = data.data.lineData.lineSeries;
                $.each(lineDate, function (i, n) {
                    try {
                        n.line = n.line.substr(0, n.line.indexOf('号'));
                    } catch (e) {
                        lineDate.splice(i, 1);
                    }
                });
                var option_legend = lineDate.map(function (item) {
                    return item.line;
                });
                initCheck("line_box", lineDate);
                var option = getradarOptions({
                    legend: option_legend,
                    indicator: data.data.lineData.indicator,
                    data: data.data.lineData.lineSeries
                });
                e_line.setOption(option, true);
                var $count = $('.line_box p:eq(4) .line_unselect');
                $($count).click();
            }
        }
    });
}

function sortLine(a, b) {
    return parseInt(a.line) - parseInt(b.line);
}

/***
 * 初始化雷达图线路
 * @param id
 * @param data
 */
function initCheck(id, data) {
    lineLength = data.length - 5;
    data.sort(sortLine);
    $("." + id).css("width", data.length * common.remPx * 59 + "px");
    $("." + id).empty();
    var str = '';
    $.each(data, function (i, n) {
        if (i == 0) {
            str += '<p style=""><span class="line_select" style="background-color: ' + n.color + '" data-color="' + n.color + '">' + n.line + '</span></p>';
        } else {
            str += '<p style=""> <span  class="line_unselect" style="background-color: ' + n.color + '" data-color="' + n.color + '">' + n.line + '</span></p>';
        }
    });
    $("." + id).append(str);
    var newColor = $('.line_box p').eq(lineColor).children().attr("data-color")
    $('.line_box p').eq(lineColor).css({border: "1px solid" + newColor});

};

$(".line-bt-left").click(function () {
    if (lineOffset > 0) {
        lineOffset -= 5;
        if (lineOffset < 0) {
            lineOffset = 0;
        }
        $(".line_box").animate({marginLeft: -lineOffset * common.remPx * 58 + 'px'}, 400, function () {
        });
    }
});
$(".line-bt-right").click(function () {

    if (lineOffset < lineLength) {
        lineOffset += 5;
        if (lineOffset > lineLength) {
            lineOffset = lineLength
        }
        $(".line_box").animate({marginLeft: -(lineOffset * common.remPx * 58) + 'px'}, 400, function () {
        });
    }
});


function initWarning() {
    $.ajax({
        type: "get",
        url: ctx + "/module/visual/warningInfo",
        dataType: 'json',
        success: function (data) {
            if (data.code == '200') {
                $("#warning-echarts .chart-loader").css("display", "none");
                $("#warning-echarts .e_warning").css("display", "block");
                warning = data.data.warning;
                initSilde("swiper_box", warning.astrWarning, 'cjSwiper');
                initSilde("swiper_box_2", warning.settWarning, 'slSwiper');
            }
        }
    });
}

function initDiseInfo() {
    var nowDay = new Date();
    nowDay.setTime(nowDay.getTime() - 30 * 24 * 60 * 60 * 1000);
    var time = String(nowDay.getFullYear()) + String(nowDay.getMonth() + 1) + String(nowDay.getDate());
    $.ajax({
        type: "get",
        // url: ctx + "/module/visual/diseInfo",
        url: ctx + "/static/js/mock/dapingTXeacharts.json",
        data: {'data': '2018'},
        dataType: 'json',
        success: function (data) {
            if (data.code == '200') {
                $("#dise_div .chart-loader").css("display", "none");
                $("#dise_div .dise_div").css("display", "block");
                var dise_dom = document.getElementById('disease_echar');
                dise_echarts = echarts.init(dise_dom);
                dise_echarts.on('click', function (param) {
                    if (param.name != "最大值" && param.name != "最小值" && param.name != "平均值") {
                        var name = param.dataIndex + 1;
                        window.open(ctx + "/report?lineCode=" + name, '_blank');
                    }
                });
                diseX = data.data.diseLine.map(function (item) {
                    return item.line;
                });
                diseThis = data.data.diseLine.map(function (item) {
                    return item.diseThis;
                });
                diseSum = data.data.diseLine.map(function (item) {
                    return item.diseSum;
                });
                repThis = data.data.diseLine.map(function (item) {
                    return item.repThis;
                });
                repSum = data.data.diseLine.map(function (item) {
                    return item.repSum;
                });
                var dise_option = getdiseOption({
                    xdata: diseX,
                    data: [diseThis, repThis]
                });
                dise_echarts.setOption(dise_option, true);
            }
        }
    });
}

$(document).on('click', '.line_unselect', function () {
    var boxShodw = $(this).attr("data-color");
    echartsShow($(this), boxShodw);
});

// $(".gis_title_bt").click(function () {
//     if ($(this).attr("data-id") == 'gis-bt1') {
//         $(this).removeClass('gis_unselect');
//         $(this).addClass('gis_select');
//         $(this).next().removeClass('gis_select');
//         $(this).next().addClass('gis_unselect');
//     } else {
//         $(this).removeClass('gis_unselect');
//         $(this).addClass('gis_select');
//         $(this).prev().removeClass('gis_select');
//         $(this).prev().addClass('gis_unselect');
//     }
// });

function initUl(ul, data) {
    var str = "";
    $.each(data, function (i, n) {
        str += "<li class='li_action' data-url='gis'><div>" + i + "</div><div>" + n.line + "</div><div>" + n.temp + "</div><div>" + n.desc + "</div><div class='li_action_lock'><a href=\"" + n.url + "\">查看</a></div></li>"
    });
    $("#" + ul).append(str);
}

function initUlDise(ul, data) {
    $.each(data, function (i, n) {
        var str = "<li class='li_action' data-url='gis'><div>" + i + "</div><div>" + n.lineId + "</div><div>" + n.startStationname + "—" + n.endStationname + "</div><div>" + n.typeName + "</div><div >" + n.faultLevel + "</div></li>"
        $("#" + ul).append(str);
    });
}

/***
 * 初始化报警swiper
 */
function initSilde(id, array, swiper) {
    $("#" + id).empty();
    $("#" + id).removeAttr("style");
    $("#" + id + "~span").remove();
    var array_length = array.length;
    var silde_count = array_length % 2 == 0 ? parseInt(array_length / 2) : parseInt(array_length / 2) + 1;
    for (var i = 0; i < silde_count; i++) {
        $("#" + id).append('<div class="swiper-slide slide_' + i + '"></div>');
        if (i == silde_count - 1) {
            initGauge(id, i, array.slice(i * 2, array_length));
            // console.log("end=" + array.slice(i * 2, array_length));
        } else {
            // console.log("start=" + array.slice(i * 2, i * 2 + 1));
            initGauge(id, i, array.slice(i * 2, i * 2 + 2));
        }
    }
    var loop_flage = silde_count < 2 ? false : true;
    cjslwiper[swiper] = new Swiper('.' + id, {
        direction: 'vertical',
        loop: loop_flage,
        autoplay: {
            autoplay: true,
            delay: 4000,
        }
    });
    if (loop_flage) {
        var canvas = $('#' + id + ' .swiper-slide:eq(1) canvas')[0];
        var clonedCanvas = cloneCanvas(canvas);
        $('#' + id + ' .swiper-slide:last-child div').html(clonedCanvas);
    }
}

//初始化仪表盘
function initGauge(id, index, arr) {
    var dom2 = $("#" + id + " .slide_" + index)[0];
    var myChart1 = echarts.init(dom2);
    var listOption = [];
    $.each(arr, function (i, n) {
        var temp = new Object();
        temp.name = n.line;
        temp.max = n.max;
        temp.value = n.value;
        listOption.push(temp);
    });
    myChart1.setOption(getGaugeOption(listOption), true);

}


/*雷达图*/
function echartsShow(o, color) {
    var array = $(".line_box span");
    $.each(array, function (i, n) {
        if ($(n).attr("class") == 'line_select') {
            $(n).removeClass("line_select");
            $(n).addClass("line_unselect");
        }
    });
    var name = $(o).text();
    $(o).removeClass("line_unselect");
    $(o).addClass("line_select");
    $(o).parent().siblings().css({border: "1px solid transparent"});
    $(o).parent().css({border: "1px solid " + color});
    lineColor = $(o).parent().index();

    var temp_option = e_line.getOption();
    var temp_selected = temp_option.legend[0].selected;
    var temp_data = temp_option.legend[0].data;
    $.each(temp_data, function (i, n) {
        if (n == name) {
            temp_selected[n] = true;
        } else {
            temp_selected[n] = false;
        }
    });
    temp_option.legend[0].data = temp_data;
    temp_option.legend[0].selected = temp_selected;
    e_line.setOption(temp_option, true);
}


//保留小数点后两位
function toDecimal2(x) {
    var f = Math.round(x * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}

//克隆Canvas
function cloneCanvas(oldCanvas) {
    //create a new canvas
    var newCanvas = document.createElement('canvas');
    var temp = $(oldCanvas).parent().width() / oldCanvas.width;
    // console.log($(oldCanvas).parent().width());
    newCanvas.width = oldCanvas.width;
    newCanvas.height = oldCanvas.height;
    var context = newCanvas.getContext('2d');
    //apply the old canvas to the new one
    context.scale(temp, temp);
    context.drawImage(oldCanvas, 0, 0);
    //return the new canvas
    return newCanvas;
}

//显示模态框
var _gaq = _gaq || [];

function showDialog() {
    // var nightImage = document.getElementById("mapDiv");
    // nightImage.className = "content_body2";
    // $("#modal-close-bt").css("display", "block")
    // $("#mapDiv").removeClass("content_body");
    // $("#content_id").removeClass("content_id");
    // $("#mapDiv").addClass("content_body2");
    // $("#content_id").addClass("content_id2");

    // if(closeFn != undefined || closeFn != null ){
    //     return "";
    // }
    // _gaq.push(['_trackEvent', 'ctajs', 'modal']);
    // closeFn = cta( $("#mapDiv")[0], $("#this_con")[0], {relativeToWindow: true}, function showModal(modal) {
    //     modal.classList.add('show');
    //     document.body.classList.add('disable-mouse');
    // });
}

// $("#modal-close-bt").click(function () {
//     var nightImage = document.getElementById("mapDiv");
//     nightImage.className = "content_body";
//     $("#modal-close-bt").css("display", "none")
// });

//关闭模态框
var closeFn;

function closeShowingModal(liked) {
    if (!liked) {
        return "";
    }
    if (liked !== undefined) {
        _gaq.push(['_trackEvent', 'ctajs', liked ? 'liked' : 'unliked']);
    }
    var showingModal = document.querySelector('.modal.show');
    if (!showingModal) return;
    showingModal.classList.remove('show');
    document.body.classList.remove('disable-mouse');
    if (closeFn) {
        closeFn();
        closeFn = null;
    }
}

/*echarts仪表盘*/
function getGaugeOption(options) {
    var GaugeOption = {
        animation: false,
        series: []
    };
    var item = {
        type: 'gauge',
        min: 1,
        max: null,
        splitNumber: 3,
        center: null,
        radius: "60%", //仪表大小
        axisLine: {
            lineStyle: {
                width: 4
            }
        },
        //指针宽度
        pointer: {
            width: 2
        },
        axisTick: {
            show: false
        },
        splitLine: {
            length: 6,
            lineStyle: {
                width: 1,
            }
        },
        axisLabel: {
            fontSize: 7 * common.remPx,
            distance: 1
        },
        axisLine: {
            show: false,
            lineStyle: { //设置仪表盘颜色
                color: [
                    [0.33, '#20Ae51'],
                    [0.66, '#0ff'],
                    [1, '#ffed44']
                ],
                width: 4
            }
        },
        title: {
            offsetCenter: ['10%', '120%'],
            color: '#fff',
            fontSize: 13 * common.remPx
        },
        detail: {
            color: '#fff',
            fontSize: 10 * common.remPx,
            formatter: function (value) {
                return toDecimal2(value / 15) * 100 + "";
            }
        },
        data: [{value: null, name: null}]
    };
    $.each(options, function (i, n) {
        var temp = JSON.parse(JSON.stringify(item));
        temp.data[0].value = n.value;
        var nameSplit = n.name.split("-");
        temp.data[0].name = '\n' + nameSplit[1] + '\n至' + nameSplit[2];
        temp.max = n.max;
        temp.center = [(i * 50 + 15) + '%', '40%'];
        temp.detail.formatter = n.value;
        GaugeOption.series.push(temp);
    });
    return GaugeOption;
}

/*累计值，变化值*/
function getdiseOption(options) {
    var dise_option = {
        legend: {
            right: 40,
            itemGap: 30,
            textStyle: {
                color: '#fff',
                fontSize: 14
            },
        }, grid: {
            bottom: 40,
            left: 55,
            right: 60
        },
        // toolbox:{
        //     show: true,
        //         feature: {
        //             mark: {show: true},
        //             dataView: {show: true, readOnly: false},
        //             magicType: {show: true, type: ['line', 'bar']},
        //             restore: {show: true},
        //             saveAsImage: {show: true}
        //         }
        // },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

        },
        xAxis: [

            {
                type: 'category',
                boundaryGap: true,
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                    }
                },
                data: options.xdata
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                splitNumber: 6,
                name: '个数',
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                    }
                },
                splitLine: {
                    show: false
                },
                // boundaryGap: [0.5, 0.5]
            },
            {
                // type: 'value',
                // scale: true,
                // axisLine: {
                //     lineStyle: {
                //         color: '#fff',
                //     }
                // },
                // splitLine: {
                //     show: false
                // },
                // boundaryGap: [0.1, 0.2]
            }
        ],
        series: [
            {
                name: '病害',
                type: 'bar',
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    label: {
                        position: 'end',
                        formatter: ' {c}',
                    },
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                },
                itemStyle: {
                    color: '#c23531',
                },
                data: options.data[0],
            },
            // {
            //     name: '维修',
            //     type: 'line',
            //     yAxisIndex: 1,
            //     markPoint: {
            //         data: [
            //             {type: 'max', name: '最大值'},
            //             {type: 'min', name: '最小值'}
            //         ]
            //     },
            //     markLine: {
            //         label: {
            //             position: 'end',
            //             formatter: '        {c}',
            //         },
            //         data: [
            //             {type: 'average', name: '平均值'}
            //         ]
            //     },
            //     itemStyle: {
            //         color: '#3fdcfd',
            //     },
            //     data: options.data[1],
            // }
        ]
    };
    return dise_option;

}

/*雷达图*/
function getradarOptions(options) {
    var resultOtion = {
        legend: {
            show: false,
            data: options.legend,
            selected: {},

        },
        grid: {
            top: 0
        },
        radar: {
            startAngle: 60,
            name: {
                textStyle: {
                    color: '#fff',
                    padding: [0, 15 * common.remPx, 0, 15 * common.remPx],
                    fontSize: 17 * common.remPx
                }
            },
            center: ['50%', '47%'],
            radius: '86%',
            nameGap: 5,
            splitArea: {
                show: false
            },
            indicator: []
        },
        series: [{
            type: 'radar',
            areaStyle: {
                normal: {
                    opacity: 0.2
                }
            },
            // areaStyle: {normal: {}},
            data: []
        }]
    };

    var item = {
        value: null,
        itemStyle: {
            normal: {
                color: '#0f0'
            }
        },
        name: null,
    };
    var indicator_temp = [];
    $.each(options.indicator, function (i, n) {
        var item = new Object();
        item.name = n.line;
        item.max = n.max;
        indicator_temp.push(item);
    });
    resultOtion.radar.indicator = indicator_temp;
    $.each(options.legend, function (i, n) {
        if (i == 0) {
            resultOtion.legend.selected[n] = true;
        } else {
            resultOtion.legend.selected[n] = false;
        }
    });
    $.each(options.data, function (i, n) {
        var temp = JSON.parse(JSON.stringify(item));
        temp.value = n.value;
        temp.name = n.line;
        (n.color).toString().length > 0 ? (temp.itemStyle.normal.color = n.color) : "";
        resultOtion.series[0].data.push(temp)
    });

    return resultOtion;
}

//模态框
//!function(){function a(a){for(var b,c=0,d="string"==typeof a?a.split(/\s*,\s*/):[],e=d.length;e--;)b=d[e],c=Math.max(parseFloat(b)||0,c);return c}function b(b){var c=0,d=0,g=0,k=0,l=window.getComputedStyle(b)||{},m=l[e+h];c=Math.max(a(m),c);var n=l[e+i];d=Math.max(a(n),d);l[f+i];k=Math.max(a(l[f+i]),k);var o=a(l[f+h]);return o>0&&(o*=parseInt(l[f+j],10)||1),g=Math.max(o,g),g||c}function c(a){var b=window.getComputedStyle(a);return b.background||b.backgroundColor}function d(a,e,f,h){if(!g)return void(h&&h(e));var i,j,l,m,n,o=1;"function"==typeof f&&(h=f,f={}),f=f||{},f.duration=f.duration||k.duration,f.targetShowDuration=f.targetShowDuration||b(e)||k.targetShowDuration,f.relativeToWindow=f.relativeToWindow||k.relativeToWindow,"none"===window.getComputedStyle(e).display&&e.style.setProperty("display","block","important"),i=c(e),j=c(a),l=e.getBoundingClientRect(),m=a.getBoundingClientRect(),scaleXRatio=m.width/l.width,scaleYRatio=m.height/l.height,diffX=m.left-l.left,diffY=m.top-l.top,e.style.removeProperty("display"),n=document.createElement("div"),n.style.setProperty("pointer-events","none","important"),n.style.setProperty("position",f.relativeToWindow?"fixed":"absolute","important"),n.style.setProperty("-webkit-transform-origin","top left","important"),n.style.setProperty("transform-origin","top left","important"),n.style.setProperty("transition",f.duration+"s ease"),n.style.setProperty("width",l.width+"px","important"),n.style.setProperty("height",l.height+"px","important"),n.style.setProperty("left",l.left+(f.relativeToWindow?0:window.pageXOffset)+"px","important"),n.style.setProperty("top",l.top+(f.relativeToWindow?0:window.pageYOffset)+"px","important"),n.style.setProperty("background",j,"important"),n.style.setProperty("-webkit-transform","translate("+diffX+"px, "+diffY+"px) scale("+scaleXRatio+", "+scaleYRatio+")","important"),n.style.setProperty("transform","translate("+diffX+"px, "+diffY+"px) scale("+scaleXRatio+", "+scaleYRatio+")","important"),document.body.appendChild(n);n.offsetTop;return n.style.setProperty("background",i,"important"),n.style.removeProperty("-webkit-transform"),n.style.removeProperty("transform"),n.addEventListener("transitionend",function p(){n.removeEventListener("transitionend",p),h&&h(e),n.style.transitionDuration=f.targetShowDuration+o+"s",n.style.opacity=0,setTimeout(function(){n.parentNode.removeChild(n)},1e3*(f.targetShowDuration+o))}),function(b,c){d(e,a,b,c)}}var e,f,g=function(){return void 0!==window.ontransitionend||void 0!==document.documentElement.style.transition}(),h="Duration",i="Delay",j="IterationCount";e=void 0===window.ontransitionend&&void 0!==window.onwebkittransitionend?"WebkitTransition":"transition",f=void 0===window.onanimationend&&void 0!==window.onwebkitanimationend?"WebkitAnimation":"animation";var k={duration:.3,targetShowDuration:0,extraTransitionDuration:1,relativeToWindow:!1};d.isSupported=g,"object"==typeof exports?module.exports=d:"function"==typeof define&&define.amd?define([],d):window.cta=d}();
//滚动条设置
(function (e) {
    e.fn.vTicker = function (c) {
        c = e.extend({
            speed: 700,
            pause: 4E3,
            showItems: 3,
            animation: "",
            mousePause: true,
            isPaused: false,
            direction: "up",
            height: 0
        }, c);
        moveUp = function (a, d, b) {
            if (!b.isPaused && !document.hidden) {
                a = a.children("ul");
                var f = a.children("li:first").clone(true);
                if (b.height > 0) d = a.children("li:first").height();
                a.animate({top: "-=" + d + "px"}, b.speed, function () {
                    e(this).children("li:first").remove();
                    e(this).css("top", "0px")
                });
                if (b.animation == "fade") {
                    a.children("li:first").fadeOut(b.speed);
                    b.height == 0 && a.children("li:eq(" + b.showItems + ")").hide().fadeIn(b.speed).show()
                }
                f.appendTo(a)
            }
        };
        moveDown = function (a, d, b) {
            if (!b.isPaused && !document.hidden) {
                a = a.children("ul");
                var f = a.children("li:last").clone(true);
                if (b.height > 0) d = a.children("li:first").height();
                a.css("top", "-" + d + "px").prepend(f);
                a.animate({top: 0}, b.speed, function () {
                    e(this).children("li:last").remove()
                });
                if (b.animation == "fade") {
                    b.height == 0 && a.children("li:eq(" + b.showItems + ")").fadeOut(b.speed);
                    a.children("li:first").hide().fadeIn(b.speed).show()
                }
            }
        };
        return this.each(function () {
            var a = e(this), d = 0;
            a.css({overflow: "hidden", position: "relative"}).children("ul").css({
                position: "absolute",
                margin: 0,
                padding: 0
            }).children("li").css({margin: 0, padding: 0});
            if (c.height == 0) {
                a.children("ul").children("li").each(function () {
                    if (e(this).height() > d) d = e(this).height()
                });
                a.children("ul").children("li").each(function () {
                    e(this).height(d)
                });
                a.height(d * c.showItems)
            } else a.height(c.height);
            setInterval(function () {
                c.direction == "up" ? moveUp(a, d, c) : moveDown(a, d, c)
            }, c.pause);

            c.mousePause && a.bind("mouseenter", function () {
                c.isPaused = true
            }).bind("mouseleave", function () {
                c.isPaused = false
            })
        })
    }
})(jQuery);
// 跳转区间详情的变量
var clickDescName = '';
// 新增病害值点击
$(document).on("click", ".desc_div", function () {
    var a = $(this).find('div').eq(1).text();
    var b = a.slice(4);
    clickDescName = b + "上行";
    window.open(ctx + "/segment?clickSegmentName=" + clickDescName, '_blank');
});
// 雷达图点击
$('#echar_line').click(function () {
    var name = 7;
    window.open(ctx + "/report?lineCode=" + name, '_blank');
});
// 仪表图
$(document).on("click", ".echart_box", function () {
    clickDescName = "上行";
    window.open(ctx + "/segment?clickSegmentName=" + clickDescName, '_blank');
});
// 新增维修点击
$(document).on("click", ".li_action .li_action_lock", function () {
    clickDescName = "上行";
    window.open(ctx + "/segment?clickSegmentName=" + clickDescName, '_blank');
});

initWarning();
//初始化病害维修
initDiseInfo();
//初始化线路健康度
initlineInfo();
//初始化滚动动态信息
initDynamicInfo();
// 大屏线路图模态框
// 模态框
function showLintSta() {
    // var a =  $(".mapDiv_StaCount").html();
    // $(".layer_StaCount").html(a);
    $('#layer_StationModal').fadeIn(300);
}

$(document).on("click", ".layer_StaHide,.layer_background", function () {
    $('#layer_StationModal').fadeOut(300);
});