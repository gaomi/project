var echarts2;
//曲率折线
var echarts3;
//坡度折线
var echarts4;
//与设计差
var echarts5;
//速率图
var echarts6;
//所有echartsXAxis长度
var monitorSize = {};

//详情点号页的X轴数据用于前端查询
var x_detail = [];
var detail_echar;
var second_dom = document.getElementById("echarts2");
var second_dom1 = document.getElementById("echarts3");
var second_dom2 = document.getElementById("echarts4");
var second_dom3 = document.getElementById("echarts5");
var second_dom6 = document.getElementById("echarts6");
$(function () {
    common.status.tab_monitor = segmentId;
    //点击外侧关闭多选
    // $.each(["complete", "error", "cjsl_box", "ptd_cjsl_box", "em_error", "ptd_complete", "em_complete", "em_cjsl_box"], function (i, n) {
    // $.each(["panel", "astringe", "now_astringe", "sink", "complete", "error","cjsl_box","qlpd_box", "diff_val"], function (i, n) {
    $.each(["complete", "error", "cjsl_box"], function (i, n) {
        $("#select_" + n).on("click", function (e) {
            e.stopPropagation();
            $(document).one("click", function () {
                hide(n);
            });
            $(".popover").on("click", function (e) {
                e.stopPropagation();
            });
        });
    });
    second_dom.style.width = common.mainEchartsWith;
    second_dom1.style.width = common.mainEchartsWith;
    second_dom2.style.width = common.mainEchartsWith;
    second_dom3.style.width = common.mainEchartsWith;
    second_dom6.style.width = common.mainEchartsWith;
    echarts2 = echarts.init(second_dom);
    echarts3 = echarts.init(second_dom1);
    echarts4 = echarts.init(second_dom2);
    echarts5 = echarts.init(second_dom3);
    echarts6 = echarts.init(second_dom6);

    function echartGlob(echartsNum) {
        echartsNum.on("globalout", function () {
            echarts1.dispatchAction({
                type: 'hideTip'
            })
        });
    }

    echartGlob(echarts2);
    echartGlob(echarts3);
    echartGlob(echarts4);
    echartGlob(echarts5);
    echartGlob(echarts6);
    echarts2.on('click', function (param) {
        var detailDate;
        var detailType;
        $("#monitor_tab li:eq(1) a").tab('show');
        if (param.seriesName.indexOf("沉") >= 0) {
            detailDate = echarts2.getOption().xAxis[1].data.map(function (item) {
                return item[0];
            });
            detailType = 'CJN';
        } else if (param.seriesName.indexOf("收") >= 0) {
            detailDate = echarts2.getOption().xAxis[0].data.map(function (item) {
                return item[0];
            });
            detailType = 'SLY';
        }
        var start = param.dataIndex - 10 < 0 ? 0 : param.dataIndex - 10;
        var end = param.dataIndex + 10 > detailDate.length ? detailDate.length - 1 : param.dataIndex + 10;
        var showData = detailDate.slice(start, end);
        //初始化详情页
        monitorDetails(detailDate[param.dataIndex], showData, detailType);
    });
    //当Echarts被点击刷新旁通道高度
    SlLegend();
    initSlEcharts();
})


/***
 * 处理折线是否显示
 * @param name
 * @param falge
 * @param input
 * @param id
 * @param type
 * @returns {string}
 */
function totailEchar(name, falge, input, id, type) {
    var endt = getEnd(id);
    if (endt.length < 1) {
        var option = detail_echar.getOption();
        var select_key = option.legend[0].data;
        var show_key = option.legend[0].selected;
        if (falge) {
            if (checkExist(detail_echar, name)) {
                $.ajax({
                    type: "post",
                    url: ctx + "/module/segment/getPointDetails",
                    dataType: 'json',
                    data: JSON.stringify({
                        params: {
                            "segment": segmentId,
                            group_segment: group_segment,
                            start_mileage: common.start_mileage,
                            end_mileage: common.end_mileage,
                            isDensity: '0',
                            "line": getline(lineId),
                            "type": type,
                            "point": name,
                            "updown": getDirectionCode()
                        }
                    }),
                    contentType: 'application/json',
                    async: false,
                    success: function (data) {
                        if (data.code == '200') {
                            option.series.push({
                                name: name,
                                type: "line",
                                data: getDetailValue(data.data, "YAIX")
                            });
                            if (select_key.indexOf(name) < 0) {
                                show_key[String(name)] = true;
                                select_key.push(name);
                            }
                        }
                    }
                });
            } else {
                select_key.push(name);
                show_key[String(name)] = true;
            }
        } else {
            show_key[String(name)] = false;
            var index = select_key.indexOf(name);
            if (index > -1) {
                select_key.splice(parseInt(index), 1);
            }
        }
        option.legend[0].data = select_key;
        option.legend[0].selected = show_key;
        detail_echar.setOption(option);
        return "";
    }
    var desc = $("#segment_select option:selected").attr("desc");
    $.each(endt, function (i, n) {
        var charTitle = name + n;
        if (falge) {
            if (checkExist(echarts2, charTitle)) {
                var init_date;
                if (type.indexOf("CJS") >= 0) {
                    init_date = $("#cj_select").val();
                } else if (type.indexOf("CJN") >= 0) {
                    if (n.indexOf("本") >= 0) {
                        init_date = input.attr("target-n");
                    } else if (n.indexOf("年") >= 0) {
                        init_date = input.attr("target-y");
                    }
                } else if ((type.indexOf("SLC") >= 0)) {

                } else if ((type.indexOf("SLY") >= 0)) {
                    init_date = input.attr("target-n");
                } else if ((type.indexOf("SLS") >= 0)) {
                    init_date = $("#sl_select").val();
                }
                var paramsError = getError("error");
                $.ajax({
                    type: "post",
                    url: ctx + "/module/segment/getSlByName",
                    data: JSON.stringify({
                        params: {
                            show_key: name,
                            "segment": segmentId,
                            group_segment: group_segment,
                            start_mileage: common.start_mileage,
                            desc: desc,
                            end_mileage: common.end_mileage,
                            isDensity: '0',
                            "type": n,
                            "updown": getDirectionCode(),
                            "line": getline(lineId),
                            "init_date": init_date
                        }
                    }),
                    // async: false,
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.code == '200') {
                            if (type == 'SLC') {
                                addEchartCJSL(echarts5, data, type, paramsError, charTitle, common.showCjSl);
                            } else {
                                addEchartCJSL(echarts2, data, type, paramsError, charTitle, common.showCjSl);
                            }
                        }
                    }
                });
                // 添加曲率坡度曲线
                if (type === 'CJS') {
                    var param = {
                        params: {
                            "segment": segmentId,
                            "updown": getDirectionCode(),
                            group_segment: group_segment,
                            start_mileage: common.start_mileage,
                            end_mileage: common.end_mileage,
                            'line': getline(lineId),
                            desc: desc,
                            "show_key": name,
                            "base_key": init_date
                        }
                    };
                    $.ajax({
                        type: "post",
                        url: ctx + "/module/segment/getQlByTime",
                        contentType: 'application/json',
                        data: JSON.stringify(param),
                        dataType: 'json',
                        // async: false,
                        success: function (data) {
                            if (data.code == '200') {
                                addEchartsQlPd(echarts3, data, 'Qvlv', paramsError, charTitle);
                                addEchartsQlPd(echarts4, data, 'Sulv', paramsError, charTitle);
                            }
                        }
                    });
                }
                //添加速率图
                if (type.indexOf('CJ') >= 0) {
                    $.ajax({
                        type: "post",
                        url: ctx + "/module/segment/getSlByName",
                        data: JSON.stringify({
                            params: {
                                show_key: name,
                                "segment": segmentId,
                                group_segment: group_segment,
                                start_mileage: common.start_mileage,
                                end_mileage: common.end_mileage,
                                desc: desc,
                                "type": n,
                                "updown": getDirectionCode(),
                                "line": getline(lineId),
                                "init_date": init_date,
                                "SULV": true
                            }
                        }),
                        // async: false,
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (data) {
                            if (data.code == '200') {
                                addEchartSULV(echarts6, data, charTitle);
                            }
                        }
                    });
                }
            } else {
                if ((common.showCjSl == undefined || type.indexOf(common.showCjSl) >= 0) && type == 'SLC') {
                    addExistEchart(echarts5, charTitle);
                } else if (common.showCjSl == undefined || type.indexOf(common.showCjSl) >= 0) {
                    addExistEchart(echarts2, charTitle);
                    addExistEchart(echarts6, charTitle);
                }
                // 添加曲率曲线
                if (type == 'CJS') {
                    addExistEchart(echarts3, charTitle);
                    addExistEchart(echarts4, charTitle);
                }
            }
        } else {
            //去除折线图
            if (type == 'SLC') {
                deleteEchars(echarts5, charTitle);
            } else {
                deleteEchars(echarts2, charTitle);

                deleteEchars(echarts6, charTitle);
            }

            // 去除曲率曲线
            if (type == 'CJS') {
                deleteEchars(echarts3, charTitle);
                //去除坡度曲线
                deleteEchars(echarts4, charTitle);
            }
        }
    });
}

/***
 * //初始化所有选项
 */
function initCjSlBox() {
    if (segmentId != common.initSegment) {
        //初始化时间
        getCharInitTime();
    }
    initSelect("cj_select", common.initCjDate, null, 'CJ');
    initSelect("sl_select", common.initSlDate, null, 'SL');
    getCheckbox('panel', common.initCjDate, false, "", "CJS", "CJ");
    getCheckbox('now_astringe', common.initSlDate, false, "", "SLY", "SL");
    getCheckbox('astringe', common.initSlDate, false, "", 'SLS', "SL");
    getCheckbox('diff_val', common.initSlDate, false, "", 'SLC', "SL");
    getCheckbox('sink', common.initCjDate, false, "", 'CJN', "CJ");
    checkEcharFun("echarts_complete", "");
}

/***
 * 删除折线图
 * @param o
 * @param name
 */
function deleteEchars(o, name) {
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    selected[String(name)] = false;
    var index = showed.indexOf(name);
    if (index > -1) {
        showed.splice(parseInt(index), 1);
    }
    if (name.indexOf(sl_diff) >= 0) {
        //如果为设计差不需要刷新旁通道
        o.setOption(option);
    } else {
        changPtd(o, option);
    }
}


/***
 * 添加速率折线图
 * @param o
 * @param data
 */
function addEchartSULV(o, data, name) {
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    var color = common.echartsColor[option.series.length % common.echartsColor.length];
    var series_item = {
        name: name,
        type: "line",
        itemStyle: {
            color: color,
        },
        lineStyle: {
            color: color,
        },
        data: data.data.data
    };
    series_item.yAxisIndex = data.data.yAxisIndex;
    series_item.xAxisIndex = data.data.xAxisIndex;
    series_item.symbol = 'circle';
    series_item.symbolSize = 7;
    option.series.push(series_item);
    if (showed.indexOf(name) < 0) {
        showed.push(name);
        selected[name] = true;
    }
    changPtd(o, option);
}

/***
 * 添加曲率坡度折线图
 * @param o
 * @param o1
 * @param data
 */
function addEchartsQlPd(o, data, type, params, name) {
    //曲率||坡度
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    var color = common.echartsColor[option.series.length % common.echartsColor.length];
    var item_series = {
        name: name,
        symbol: 'circle',
        type: "line",
        symbolSize: 7,
        itemStyle: {
            color: color,
        },
        lineStyle: {
            color: color,
        },
        data: data.data[type]
    };
    // if (params != null && params != undefined) {
    //     item_series = addFilterError(item_series, params,color);
    // }
    option.series.push(item_series);
    if (showed.indexOf(name) < 0) {
        showed.push(name);
        selected[name] = true;
    }
    changPtd(o, option);
}

/***
 * 添加与设计差和收敛沉降折线图
 * @param o
 * @param data
 * @param type
 */
function addEchartCJSL(o, data, type, params, name, showType) {
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    var color = common.echartsColor[option.series.length % common.echartsColor.length];
    var series_item = {
        name: name,
        type: "line",
        itemStyle: {
            color: color,
        },
        lineStyle: {
            color: color,
        },
        data: data.data.data
    };

    if (params != null && params != undefined) {
        series_item = addFilterError(series_item, params, color);
    }

    if (type == 'SLC') {
        if (showType == undefined || type.indexOf(showType) >= 0) {

        }
        option.series.push(series_item);
        if (showed.indexOf(name) < 0) {
            showed.push(name);
            selected[name] = true;
        }
        // o.setOption(option);
        changPtd(o, option);

    } else {
        series_item.yAxisIndex = data.data.yAxisIndex;
        series_item.xAxisIndex = data.data.xAxisIndex;
        series_item.symbol = data.data.xAxisIndex == 1 ? 'circle' : 'diamond';
        series_item.symbolSize = 7;
        option.series.push(series_item);
        if (showType == undefined || type.indexOf(showType) >= 0) {
            if (showed.indexOf(name) < 0) {
                showed.push(name);
                selected[name] = true;
            }
        } else {
            var index = showed.indexOf(name);
            if (index > -1) {
                showed.splice(parseInt(index), 1);
            }
            selected[name] = false;
        }
        changPtd(o, option);
    }
}

/***
 * 添加过滤
 * @param series
 * @param params
 */
function addFilterError(series_item, params, color) {
    if (Object.keys(params).length > 0) {
        for (param_key in params) {
            if (series_item.name.indexOf(param_key) >= 0) {
                var params_min = params[param_key].MIN;
                var params_max = params[param_key].MAX;
                series_item.itemStyle = {
                    //color颜色随机
                    color: function (value) {
                        return getErrorColor(value, params_min, params_max, color);
                    }
                }
            }
        }
    }
    return series_item;
}

/***
 * 当Echarts被点击刷新旁通道高度
 * @constructor
 */
function SlLegend() {
    var item_arr = [echarts2, echarts3, echarts4, echarts5];
    $.each(item_arr, function (i, n) {
        n.on('legendselectchanged', function (params) {
            changPtd(n, n.getOption());
        });
    });
}


/***
 * 当Echarts被点击刷新旁通道高度
 * @constructor
 */
function SlLegend() {
    var item_arr = [echarts2, echarts3, echarts4, echarts5];
    $.each(item_arr, function (i, n) {
        n.on('legendselectchanged', function (params) {
            changPtd(n, n.getOption());
        });
    });
}

/***
 * 初始沉降下拉框触发事件cj_select
 */
$("#cj_select").change(function () {
    var nodes = $("div[data-target='panel']");
    $.each(nodes, function (i, n) {
        var inputs = $(n).find("input");
        $.each(inputs, function (j, m) {
            if ($(m).is(':checked')) {
                $(m).prop("checked", false);
                // var text = $(m).val();
                // totailEchar(text,false,'panel',cj_sum);
            }
        });
    });
    changeEcharCJSL(echarts2);
    changeEcharCJSL(echarts6);

    //曲率
    changeEcharQVPU(echarts3)
    //坡度
    changeEcharQVPU(echarts4)
    // initCjSlData('panel', 'CJS');
    // showQlEchar('QLPDSL', true);
});

/***
 * 清空累曲率坡度折线
 * @param o
 */
function changeEcharQVPU(o) {
    var option = o.getOption();
    option.legend[0].data = [];
    option.legend[0].selected = {};
    var seriesed = option.series;
    for (var i = seriesed.length - 1; i > 0; i--) {
        if (String(seriesed[i].name).indexOf(cj_sum) > -1) {
            seriesed.splice(i, 1);
        }
    }
    o.setOption(option, true);
}

/***
 * 清空累计沉降折线
 * @param o
 */
function changeEcharCJSL(o) {
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    var seriesed = option.series;
    for (var i = showed.length - 1; i >= 0; i--) {
        var j = String(showed[i]).indexOf(cj_sum);
        if (j > -1) {
            showed.splice(i, 1);
        }
    }
    for (key in selected) {
        if (String(key).indexOf(cj_sum) > -1) {
            delete selected[String(key)];
        }
    }
    for (var i = seriesed.length - 1; i >= 0; i--) {
        if (String(seriesed[i].name).indexOf(cj_sum) > -1) {
            seriesed.splice(i, 1);
        }
    }
    // changPtd(o, option);
    o.setOption(option, true);
}

$("#sl_select").change(function () {
    var nodes = $("div[data-target='astringe']");
    $.each(nodes, function (i, n) {
        var inputs = $(n).find("input");
        $.each(inputs, function (j, m) {
            if ($(m).is(':checked')) {
                $(m).prop("checked", false);
                // var text = $(m).val();
                // totailEchar(text,false,'panel',cj_sum);
            }
        });
    });

    var char2Option = echarts2.getOption();
    var char2_show = char2Option.legend[0].data;
    var char2_selected = char2Option.legend[0].selected;
    var char2_series = char2Option.series;
    for (var i = char2_show.length - 1; i >= 0; i--) {
        var j = String(char2_show[i]).indexOf(sl_sum);
        if (j > -1) {
            char2_show.splice(i, 1);
        }
    }
    for (key in char2_selected) {
        if (String(key).indexOf(sl_sum) > -1) {
            delete char2_selected[String(key)];
        }
    }
    for (var i = char2_series.length - 1; i >= 0; i--) {
        if (String(char2_series[i].name).indexOf(sl_sum) > -1) {
            char2_series.splice(i, 1);
        }
    }
    echarts2.setOption(char2Option, true);
    initCjSlData('astringe', 'SLS');
});

//初始化详情页
function monitorDetails(name, data, type) {
    getCheckbox('detail_checkbox', data, false, name, type, "");
    checkEcharFun("detail_checkbox", ".detail-check");
    var detail_dom = document.getElementById("detail_echar");
    detail_dom.style.width = common.Detail;
    detail_echar = echarts.init(detail_dom);
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getPointDetails",
        dataType: 'json',
        data: JSON.stringify({
            params: {
                "segment": segmentId,
                group_segment: group_segment,
                start_mileage: common.start_mileage,
                end_mileage: common.end_mileage,
                isDensity: '0',
                "line": getline(lineId),
                "type": type,
                "point": name,
                "updown": getDirectionCode()
            }
        }),
        contentType: 'application/json',
        success: function (data) {
            if (data.code == '200') {
                var xaxis = getDetailValue(data.data, "XAIX");
                var linecharts = common.lineOptions({
                    title: '地铁' + lineId + '号线道床测量（' + directionVal + '线 ' + segmentName + '）历时统计图',
                    xaxis: xaxis,
                    legend: [name],
                    xname: '时间',
                    series: {
                        data: getDetailValue(data.data, "YAIX"),
                        name: name
                    },
                });
                x_detail = xaxis;
                initSelect("detail_start", xaxis, "开始时间", null);
                initSelect("detail_end", xaxis, "结束时间", null);
                detail_echar.setOption(linecharts, true);
            }
        }
    });
}

//初始化沉降收敛
function initCjSlData(target, type) {
    var targetDiv = $("#echarts_complete div[data-target='" + target + "'] .checkechar:eq(0)");
    targetDiv.find("input").prop("checked", true);
    totailEchar(targetDiv[0].textContent, true, targetDiv.find("input"), target, type)
}


function getError(id) {
    var params = new Object();
    var inpits = $("#" + id + " input");
    $.each(inpits, function (i, n) {
        if ($(n).val().length > 0) {
            if (params[String($(n).attr("data-target"))] == undefined) {
                params[String($(n).attr("data-target"))] = new Object();
                params[String($(n).attr("data-target"))][String($(n).attr("data-type"))] = $(n).val();
            } else {
                params[String($(n).attr("data-target"))][String($(n).attr("data-type"))] = $(n).val();
            }
        }
    });
    return params;
}

/***
 * /收敛沉降曲线图初始化
 */
function initSlEcharts() {
    //初始化多选框
    initCjSlBox();
    var desc = $("#segment_select option:selected").attr("desc");
    var param = {
        params: {
            "segment": segmentId,
            isDensity: '0',
            desc: desc,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            "updown": getDirectionCode(),
            'line': getline(lineId)
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getSLInitInfo",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        // async:false,
        success: function (data) {
            if (data.code == '200') {
                data.data.monitor["title"] = "地铁" + lineId + "号线曲线图（" + directionVal + " " + segmentName + "）";
                data.data.monitor["yAxis"] = ["沉降量(mm)", "收敛(mm)"];
                data.data.monitor["name"] = "环号\n里程";
                data.data.monitor.tooltip = true;
                data.data.monitor["cont"] = "echarts2";
                var cont2Options = common.getMonotorOption(data.data.monitor);
                echarts2.setOption(cont2Options, true);
                monitorSize.slDataLength = data.data.monitor.xdate.xdateOne.length;
                monitorSize.cjDataLength = data.data.monitor.xdate.xdateTwo.length;
                data.data.monitor["title"] = "收敛与设计差曲线图（" + directionVal + " " + segmentName + "）",
                    data.data.monitor["yAxis"] = ["沉降量(mm)", "收敛(cm)"];
                data.data.monitor["cont"] = "echarts5";
                var cont5Options = common.getMonotorOption(data.data.monitor);
                cont5Options.yAxis.splice(0, 1);
                echarts5.setOption(cont5Options, true);
                data.data.monitor["title"] = "沉降速率曲线图（" + directionVal + " " + segmentName + "）";
                data.data.monitor["cont"] = "echarts6";
                var cont6Options = common.getMonotorOption(data.data.monitor);
                cont6Options.yAxis[1].show = false;
                echarts6.setOption(cont6Options, true);
                echarts2.resize();
                //初始化 曲率
                initQLEcharts(desc);
            }
        }
    });
}

/***
 * 联动区间环号详情tooltip
 * @param index
 */
// function linkMainTooltip(index) {
//     // console.log(index)
// //     var legend = echarts2.getOption().legend[0].data;
// //     var legendStr = legend.join("");
// //     var percent;
// //     if(legendStr.indexOf("[收]") >= 0){
// //         percent = (index + 1) / monitorSize.slDataLength;
// //         console.log("收")
// //     }else{
// //         percent = (index + 1) / monitorSize.cjDataLength;
// //         console.log("沉")
// //     }
// //
// //     var mainIndex = Math.round(percent * echarts1.getOption().xAxis[0].data.length);
// //     echartTooltip.echarts1=false;
// //     echarts1.dispatchAction({
// //             type: 'showTip',
// //         // 系列的 index，在 tooltip 的 trigger 为 axis 的时候可选。
// //         seriesIndex: 1,
// //         // 数据的 index，如果不指定也可以通过 name 属性根据名称指定数据
// //         dataIndex: mainIndex
// // });
// //     // }catch (e) {}
// //
// // }
// function linkMainQLTooltip(index) {
//     // console.log(index)
// //     var percent = (index + 1) / xAxisSize;
// //     //echarts!
// //     var mainIndex = Math.round(percent * echarts1.getOption().xAxis[0].data.length);
// //     echartTooltip.echarts1=false;
// //     echarts1.dispatchAction({
// //         type: 'showTip',
// //         // 系列的 index，在 tooltip 的 trigger 为 axis 的时候可选。
// //         seriesIndex: 1,
// //         // 数据的 index，如果不指定也可以通过 name 属性根据名称指定数据
// //         dataIndex: mainIndex
// //     })
// //     //echarts
//
// }
/***
 * 初始化曲率折线图
 * @param desc 是否反序
 */
function initQLEcharts(desc) {
    var param = {
        params: {
            "segment": segmentId,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            desc: desc,
            "updown": getDirectionCode(),
            'line': 7
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getQLInitInfo",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {
                monitorSize.qlDataLength = data.data.xAxis.length;
                var qvlvOption = {
                    xdata: data.data.xAxis,
                    title: "沉降曲率半径曲线图（" + directionVal + " " + segmentName + "）",
                    xname: "监测点\n里程",
                    yname: "半径(m)",
                    CjPtd: data.data.CjPtd,
                    cont: "echarts3"
                };
                var cont3Options = common.getQvlvOption(qvlvOption);
                echarts3.setOption(cont3Options, true);

                var pdOption = {
                    xdata: data.data.xAxis,
                    title: "差异沉降坡度曲线图（" + directionVal + " " + segmentName + "）",
                    xname: "监测点\n里程",
                    yname: "",
                    CjPtd: data.data.CjPtd,
                    cont: "echarts4"
                };
                var cont4Options = common.getQvlvOption(pdOption);
                echarts4.setOption(cont4Options, true);
                initCjSlData('sink', 'CJN');
                initCjSlData('panel', 'CJS');
                initCjSlData('now_astringe', 'SLY');
                initCjSlData('astringe', 'SLS');
                initCjSlData('diff_val', 'SLC');
                $(".wait-load").css("display", "none");
                $(".monitor-content").css("display", "block");
                echarts2.resize();
                echarts3.resize();
                echarts4.resize();
                echarts5.resize();
                echarts6.resize();
                dataZoomResize(true);
            }
        }
    });
}

// 设置预警值
function initError() {
    hide('error');
    var params = getError("error");
    var option = echarts2.getOption();
    var ql_Option = echarts3.getOption();
    var cha_Option = echarts5.getOption();
    var pu_Option = echarts4.getOption();
    $.each(option.series, function (i, n) {
        var falge = true;
        for (param_key in params) {
            if (n.name.indexOf(param_key) >= 0) {
                falge = false;
                var params_min = params[param_key].MIN;
                var params_max = params[param_key].MAX;
                n.itemStyle = {
                    //color颜色随机
                    color: function (value) {
                        return getErrorColor(value, params_min, params_max, n.lineStyle.color);
                    },
                };
            }
        }
        if (falge) {
            n.itemStyle = {
                //color颜色随机
                color: function (value) {
                    return n.lineStyle.color;
                }
            };
        }
    });
    $.each(cha_Option.series, function (i, n) {
        var falge = true;
        for (param_key in params) {
            if (n.name.indexOf(param_key) >= 0) {
                falge = false;
                var params_min = params[param_key].MIN;
                var params_max = params[param_key].MAX;
                n.itemStyle = {
                    //color颜色随机
                    color: function (value) {
                        return getErrorColor(value, params_min, params_max, n.lineStyle.color);
                    },
                };
            }
        }
        if (falge) {
            n.itemStyle = {
                //color颜色随机
                color: function (value) {
                    return n.lineStyle.color;
                }
            };
        }
    });
    $.each(ql_Option.series, function (i, n) {
        var falge = true;
        for (param_key in params) {
            if (param_key == "QL") {
                falge = false;
                var params_min = params[param_key].MIN;
                var params_max = params[param_key].MAX;
                n.itemStyle = {
                    //color颜色随机
                    color: function (value) {
                        return getErrorColor(value, params_min, params_max, n.lineStyle.color);
                    },
                };
            }
        }
        if (falge) {
            n.itemStyle = {
                //color颜色随机
                color: function (value) {
                    return n.lineStyle.color;
                }
            };
        }
    });
    $.each(pu_Option.series, function (i, n) {
        var falge = true;
        for (param_key in params) {
            if (param_key == "PD") {
                falge = false;
                var params_min = params[param_key].MIN;
                var params_max = params[param_key].MAX;
                n.itemStyle = {
                    //color颜色随机
                    color: function (value) {
                        return getErrorColor(value, params_min, params_max, n.lineStyle.color);
                    },

                };
            }
        }
        if (falge) {
            n.itemStyle = {
                //color颜色随机
                color: function (value) {
                    return n.lineStyle.color;
                }
            };
        }
    });
    echarts2.setOption(option);
    echarts5.setOption(cha_Option);
    echarts3.setOption(ql_Option);
    echarts4.setOption(pu_Option);
}

/***
 * 获取异常点标记红色
 * @param value 值
 * @param params_min 最小值
 * @param params_max 最大值
 * @param color 正常值颜色
 * @returns {*}
 */
function getErrorColor(value, params_min, params_max, color) {
    if (params_min != undefined && params_max != undefined && params_max > params_min) {
        if (value.value < params_min || value.value > params_max)
            return "#f00";
    } else if (params_min != undefined && params_max != undefined && params_max < params_min) {
        if (value.value < params_min && value.value > params_max)
            return "#f00";
    } else if (params_min != undefined) {
        if (value.value < params_min) {
            return "#f00";
        }
    } else if (params_max != undefined) {
        if (value.value > params_max) {
            return "#f00";
        }
    }
    return color;
}

/***
 * 返回之折线图
 */
function blackMonitor() {
    $("#monitor_tab li:eq(0) a").tab('show');
}

$('#monitor_tab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    if ($(this).attr("href") == "#monitor_echarts") {
        echarts2.resize();
        if ($("#echarts3").css("display") == "block") {
            echarts3.resize();
        }
        if ($("#echarts5").css("display") == "block") {
            echarts5.resize();
        }
        if ($("#echarts4").css("display") == "block") {
            echarts4.resize();
        }
        dataZoomResize(false);
    }

});


//点号的日期查询
function queryDetails() {
    var start = $("#detail_start").val();
    var end = $("#detail_end").val();
    if (end > start) {
        var selected = [];
        $.each(x_detail, function (i, n) {
            if (start <= n && n <= end) {
                selected.push(i);
            }
        });
        if (selected.length > 0) {
            changeDetail(detail_echar, selected);
        }
    } else if (start > end && end == "") {
        var selected = [];
        $.each(x_detail, function (i, n) {
            if (start <= n) {
                selected.push(i);
            }
        });
        if (selected.length > 0) {
            changeDetail(detail_echar, selected);
        }
    } else if (end == "" && start == "") {
        var selectOption = detail_echar.getOption();
        selectOption.dataZoom[0].start = 0;
        selectOption.dataZoom[0].end = 100;
        detail_echar.setOption(selectOption);
    }
}

function changeDetail(detail_echar, selected) {
    var selectOption = detail_echar.getOption();
    var zoom_start = selected.shift();
    var zoom_end = selected.pop();
    selectOption.dataZoom[0].start = zoom_start / (selectOption.xAxis[0].data.length - 1) * 100;
    selectOption.dataZoom[0].end = zoom_end / (selectOption.xAxis[0].data.length - 1) * 100;
    detail_echar.setOption(selectOption);
}

//是否查看曲率
function showQlEchar(type, falge) {
    var a = $("div[data-target='panel'] input:checkbox:checked");
    var sl_cha = $("div[data-target='diff_val'] input:checkbox:checked");
    if (type.indexOf('QL') >= 0) {
        var isShow = ($("#showQl").find("input")).is(':checked');
        if (type == 'QL') {
            $("#showQl").find("input").prop("checked", !isShow);
            isShow = !isShow;
        }

        if (isShow && falge) {
            if (falge && a.length < 1) {
                initCjSlData('panel', 'CJS');
                $("#echarts3").css("display", "block");
                echarts3.resize();
            } else if (a.length < 1) {
                $("#echarts3").css("display", "none");
            } else if (a.length > 0) {
                $("#echarts3").css("display", "block");
                echarts3.resize();
            }

        } else {
            $("#echarts3").css("display", "none");
        }
    }
    if (type.indexOf('PD') >= 0) {
        var isShow = ($("#showPd").find("input")).is(':checked');
        if (type == 'PD') {
            $("#showPd").find("input").prop("checked", !isShow);
            isShow = !isShow;
        }
        if (isShow && falge) {
            if (falge && a.length < 1) {
                initCjSlData('panel', 'CJS');
                $("#echarts4").css("display", "block");
                echarts4.resize();
            } else if (a.length < 1) {
                $("#echarts4").css("display", "none");
            } else if (a.length > 0) {
                $("#echarts4").css("display", "block");
                echarts4.resize();
            }
        } else {
            $("#echarts4").css("display", "none");
        }
    }
    if (type.indexOf('SL') >= 0) {
        var isShow = ($("#showSL").find("input")).is(':checked');
        if (type == 'SL') {
            $("#showSL").find("input").prop("checked", !isShow);
            isShow = !isShow;
        }
        if (isShow && falge) {
            if (falge) {
                $("#echarts6").css("display", "block");
                echarts6.resize();
            }
        } else {
            $("#echarts6").css("display", "none");
        }
    }
    if (common.showCjSl == 'SL' && sl_cha.length > 0 || common.showCjSl == undefined && sl_cha.length > 0) {
        $("#echarts5").css("display", "block");
        echarts5.resize();
    } else {
        $("#echarts5").css("display", "none");
    }
}

//只看收敛或沉降
function showEchar(o, type) {
    var echartsOption = echarts2.getOption();
    var legendData = echartsOption.legend[0].selected;
    var lendData = echartsOption.legend[0].data;
    var inputs = $(o).parent().find("input");
    $.each(inputs, function (i, n) {
        $(n).prop("checked", false);
    });
    if (type == 'SL') {
        common.showCjSl = "SL";
        var temp = getCheckBoxs(["diff_val", "now_astringe", "astringe"]);
        for (var name in legendData) {
            if (name.indexOf(sl_type) < 0) {
                legendData[name] = false;
                var index = lendData.indexOf(name);
                if (index >= 0) {
                    lendData.splice(index, 1);
                }
            } else {
                if (temp.indexOf(name) >= 0) {
                    legendData[name] = true;
                    if (lendData.indexOf(name) < 0) {
                        lendData.push(name);
                    }
                }
            }
        }
        showQlEchar('QLPDSL', false);
        ($(o).find("input")).prop("checked", true);
        changPtd(echarts2, echartsOption);
        var slinput = $("#echarts_complete input[data-type^='SL']:checkbox:checked").length;
        if (slinput < 1) {
            initCjSlData('now_astringe', 'SLY');
            initCjSlData('astringe', 'SLS');
            initCjSlData('diff_val', 'SLC');
        }
    } else if (type == 'CJ') {
        common.showCjSl = "CJ";
        var temp = getCheckBoxs(["panel", "sink"]);
        for (var name in legendData) {
            if (name.indexOf(cj_type) < 0) {
                legendData[name] = false;
                var index = lendData.indexOf(name);
                if (index >= 0) {
                    lendData.splice(index, 1);
                }

            } else {
                if (temp.indexOf(name) >= 0) {
                    legendData[name] = true;
                    if (lendData.indexOf(name) < 0) {
                        lendData.push(name);
                    }
                }
            }
        }
        showQlEchar('QLPDSL', true);
        ($(o).find("input")).prop("checked", true);
        changPtd(echarts2, echartsOption);
        var slinput = $("#echarts_complete input[data-type^='CJ']:checkbox:checked");
        if (slinput < 1) {
            initCjSlData('sink', 'CJN');
            initCjSlData('panel', 'CJS');
        }
    } else if (type == 'ALL') {
        common.showCjSl = undefined;
        for (var name in legendData) {
            if (legendData[name] == false) {
                legendData[name] = true;
                lendData.push(name);
            }
        }
        showQlEchar('QLPDSL', true);
        ($(o).find("input")).prop("checked", true);
        changPtd(echarts2, echartsOption);
    }
}

$('.mrakMonitorBox>div').mouseenter(function () {
    mrakEcharts = $(this).attr("id");
});

// 监测数据echarts图组链接echarts1
function linkMainTooltip(index) {
    if (mrakEcharts == "echarts2") {
        var legend = echarts2.getOption().legend[0].data;
    } else if (mrakEcharts == "echarts5") {
        var legend = echarts5.getOption().legend[0].data;
    } else if (mrakEcharts == "echarts6") {
        var legend = echarts6.getOption().legend[0].data;
    }
    var legendStr = legend.join("");
    var percent;
    if (legendStr.indexOf("[收]") >= 0) {
        percent = (index + 1) / monitorSize.slDataLength;
    } else {
        percent = (index + 1) / monitorSize.cjDataLength;
    }
    var mainIndex = Math.round(percent * echarts1.getOption().xAxis[0].data.length);
    XqdataBar(mainIndex)
}

function XqdataBar(params) {
    var bfbdata;
    if (echarts1.getOption().type) {
        var xqDataGk = common.xDataDetail.length;
        bfbdata = params / xqDataGk;
    } else {
        var slDataGk = common.xDataDim.length;
        bfbdata = params / slDataGk;
    }
    try {
        var cjTooltip = Math.round(bfbdata * monitorSize.cjDataLength);
        var slTooltip = Math.round(bfbdata * monitorSize.slDataLength);
        var echarts2Index;
        var qlIndex = Math.round(bfbdata * monitorSize.qlDataLength);
        var legend = echarts2.getOption().legend[0].data;
        var legendStr = legend.join("");
        if (legendStr.indexOf("[收]") >= 0) {
            echarts2Index = slTooltip;
        } else {
            echarts2Index = cjTooltip;
        }
        var ect2DataIndex = echarts2.getOption().legend[0].selected;
        var ect2IndexNum = 1;
        for (var key in ect2DataIndex) {
            if (ect2DataIndex[key] == true) {
                $.each(echarts2.getOption().series, function (i, n) {
                    if (key == n.name) {
                        ect2IndexNum = i - 1;
                    }
                })
            }
        }
        DownAction(ect2IndexNum, echarts2Index, qlIndex, slTooltip, cjTooltip, params)
    } catch (e) {
    }
}
