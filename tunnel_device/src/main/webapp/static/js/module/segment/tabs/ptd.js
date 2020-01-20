var ptd_echarts2;
//曲率折线
var ptd_echarts3;
//坡度折线
var ptd_echarts4;
//设计差
var ptd_echarts5;
var ptd_detail_echar;
var ptdMeter = 100;
var ptd_second_dom = document.getElementById("ptd_echarts2");
var ptd_second_dom5 = document.getElementById("ptd_echarts5");
$(function () {
    $.each(["ptd_cjsl_box", "ptd_complete"], function (i, n) {
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
    ptd_second_dom.style.width = common.mainEchartsWith;
    ptd_second_dom5.style.width = common.mainEchartsWith;
    ptd_echarts2 = echarts.init(ptd_second_dom);
    ptd_echarts5 = echarts.init(ptd_second_dom5);
    //     //调用函数，初始化表格
    var agewayTable = new agewayTableInit();
    agewayTable.Init('#ptd_list');

})

function initPtdCjSlBox() {
    if (segmentId != common.initSegment) {
        //初始化时间
        getCharInitTime();
    }
    // 旁通道
    initSelect("ptd_cj_select", common.initCjDate, null, 'CJ');
    initSelect("ptd_sl_select", common.initSlDate, null, 'SL');
    getCheckbox('ptd_panel', common.initCjDate, false, "", "CJS", "CJ");
    getCheckbox('ptd_now_astringe', common.initSlDate, false, "", "SLY", "SL");
    getCheckbox('ptd_astringe', common.initSlDate, false, "", 'SLS', "SL");
    getCheckbox('ptd_diff_val', common.initSlDate, false, "", 'SLC', "SL");
    getCheckbox('ptd_sink', common.initCjDate, false, "", 'CJN', "CJ");
    checkEcharFun("ptd_echarts_complete", "");
}

var agewayTableInit = function () {
    var oTableInit = new Object()
    oTableInit.Init = function (id) {
        //记录页面bootstrap-table全局变量$table，方便应用
        var queryUrl = ctx + "/module/segment/ptd/list";
        var tableOption = common.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                param.updown = getDirectionCode();
                param.segment = segmentId;
                param.start_mileage = common.start_mileage;
                param.end_mileage = common.end_mileage;
                param.group_segment = group_segment;
                param.line = getline(lineId);
                return {
                    pageSize: params.limit,                         //页面大小
                    pageNum: (params.offset / params.limit) + 1, //页码
                    params: param
                };
            },
            columns: [
                {checkbox: true, visible: false, align: 'center', cellStyle: {css: {"text-align": "center"}}},                 //是否显示复选框
                {field: 'POINTSGUID', title: 'POINTSGUID', visible: false, align: 'center'},
                {field: 'LINECODE', title: '地铁线路编号', align: 'center', width: 100},
                {field: 'POINTSNAME', title: '监测点点号', align: 'center'},
                {field: 'POINTSMILEAGE', title: '监测点里程(m)', align: 'center'},
                {field: 'pointsRing', title: '监测点环号', align: 'center'},
                {field: 'POINTSPOSITION', title: '监测点位置', align: 'center'},
                {
                    field: 'BEGINDATE', title: '建设日期', align: 'center',
                    formatter: function (value, row, index) {
                        //return new Date(parseInt(value)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                        return timestampToTime(value);
                    }
                },
                {field: 'pointsDemo', title: '监测点备注', align: 'center'},
                {
                    field: '', title: '详情', align: 'center', width: 120,
                    formatter: function (value, row, index) {
                        var details = '<div  class="updates_bt"  onclick=ptdDetailsData(\'' + row.POINTSMILEAGE + '\',\'' + row.POINTSNAME + '\')>' +
                            '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                        return details;
                    }
                }
            ], responseHandler: function (result) {
                if (result.code == 200) {
                    common.status.tab_ptd = segmentId;
                    return result.data;
                } else {
                    return result.message;
                }
            }
        });
        $(id).bootstrapTable(tableOption);
    };
    return oTableInit;
};


//日期格式化显示
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    return Y + M + D;
}

function toPtdList() {
    common.showPtdCjSl = undefined;
    $("#ptd_showQl").find("input").prop("checked", true);
    $("#ptd_showPd").find("input").prop("checked", true);
    var inputs = $("div[data-target='ptd_cjsl_box']").find("input");
    $.each(inputs, function (i, n) {
        if (i == inputs.length - 1) {
            $(n).prop("checked", true);
        } else {
            $(n).prop("checked", false);
        }
    });
    var arr = ["ptd_sink", "ptd_panel", "ptd_diff_val", "ptd_now_astringe", "ptd_astringe"];
    $.each(arr, function (i, n) {
        var inputs = $("div[data-target='" + n + "']").find("input");
        $.each(inputs, function (j, m) {
            $(m).prop("checked", false);
        });
    });

    $('#myPtdTab li:eq(0) a').tab('show');
}

function ptdDetailsData(meter, point) {
    $('#myPtdTab li:eq(1) a').tab('show');
    common.ptdMeter = meter;
    var ptdArray = new Array();
    var ptd = new Array();
    ptd.push(String(point));
    ptd.push(parseFloat(meter));
    ptdArray.push(ptd);
    common.ptdMeter = meter;
    common.ptdPoint = ptdArray;
    initPtd();
    SectionDetail.dataZoom[0].start = 46.26184679922747;
    SectionDetail.dataZoom[0].end = 50.70716758622489;
    setDetailEcharts(echarts1);
}

function toPtd() {
    $('#myPtdTab li:eq(1) a').tab('show');
}

$('#myPtdTab a[data-toggle="ptd_tab"]').on('shown.bs.tab', function (e) {
    switch ($(this).attr("href")) {
        case "#ptd_details":
            ptd_echarts2.resize();
            ptd_echarts5.resize();
            break;
        case "":
            break;
    }
});

//初始化详情页
function ptdDetails(name, data, type) {
    getCheckbox('ptd_detail_checkbox', data, false, name, type, "");
    checkEcharFun("ptd_detail_checkbox", ".detail-check");
    var detail_dom = document.getElementById("ptd_detail_echar");
    detail_dom.style.width = common.Detail;
    ptd_detail_echar = echarts.init(detail_dom);
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
                isDensity: '1',
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
                initSelect("ptd_detail_start", xaxis, "开始时间", null);
                initSelect("ptd_detail_end", xaxis, "结束时间", null);
                ptd_detail_echar.setOption(linecharts, true);
            }
        }
    });
}

function initPtd() {
    Ptdrefesh();
    ptd_echarts2.on('click', function (param) {
        var detailDate;
        var detailType;
        $("#myPtdTab li:eq(2) a").tab('show');
        if (param.seriesName.indexOf("沉") >= 0) {
            detailDate = ptd_echarts2.getOption().xAxis[1].data.map(function (item) {
                return item[0];
            });
            detailType = 'CJN';
        } else if (param.seriesName.indexOf("收") >= 0) {
            detailDate = ptd_echarts2.getOption().xAxis[0].data.map(function (item) {
                return item[0];
            });
            detailType = 'SLY';
        }
        var start = param.dataIndex - 10 < 0 ? 0 : param.dataIndex - 10;
        var end = param.dataIndex + 10 > detailDate.length ? detailDate.length - 1 : param.dataIndex + 10;
        var showData = detailDate.slice(start, end);
        //初始化详情页
        ptdDetails(detailDate[param.dataIndex], showData, detailType);
    });
    initPtDEcharts();
}

function Ptdrefesh() {
    var item_arr = [ptd_echarts2, ptd_echarts5];
    $.each(item_arr, function (i, n) {
        n.on('legendselectchanged', function (params) {
            changPtd(n, n.getOption());
        });
    });
}

//点号的日期查询
function queryPtdDetails() {
    var start = $("#ptd_detail_start").val();
    var end = $("#ptd_detail_end").val();
    if (end > start) {
        var selected = [];
        $.each(x_detail, function (i, n) {
            if (start <= n && n <= end) {
                selected.push(i);
            }
        });
        if (selected.length > 0) {
            changePtdDetail(detail_echar, selected);
        }
    } else if (start > end && end == "") {
        var selected = [];
        $.each(x_detail, function (i, n) {
            if (start <= n) {
                selected.push(i);
            }
        });
        if (selected.length > 0) {
            changePtdDetail(detail_echar, selected);
        }
    } else if (end == "" && start == "") {
        var selectOption = ptd_detail_echar.getOption();
        selectOption.dataZoom[0].start = 0;
        selectOption.dataZoom[0].end = 100;
        ptd_detail_echar.setOption(selectOption);
    }
}

function changePtdDetail(detail_echar, selected) {
    var selectOption = ptd_detail_echar.getOption();
    var zoom_start = selected.shift();
    var zoom_end = selected.pop();
    selectOption.dataZoom[0].start = zoom_start / (selectOption.xAxis[0].data.length - 1) * 100;
    selectOption.dataZoom[0].end = zoom_end / (selectOption.xAxis[0].data.length - 1) * 100;
    ptd_detail_echar.setOption(selectOption);
}

//收敛沉降曲线图初始化
function initPtDEcharts() {
    initPtdCjSlBox();
    var desc = $("#segment_select option:selected").attr("desc");
    var param = {
        params: {
            isDensity: "0",
            "segment": segmentId,
            group_segment: group_segment,
            table: "WHB",
            desc: desc,
            pointMeter: common.ptdMeter,
            marginMeter: 50,
            "updown": getDirectionCode(),
            'line': 7
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getPtdInitInfo",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        async: false,
        success: function (data) {
            if (data.code == '200') {
                data.data.monitor["title"] = "地铁" + lineId + "号线曲线图（" + directionVal + " " + segmentName + "）";
                data.data.monitor["yAxis"] = ["沉降量(mm)", "收敛(mm)"];
                data.data.monitor["name"] = "环号\n里程";
                data.data.monitor["CjPtd"] = common.ptdPoint;
                data.data.monitor["cont"] = "ptd_echarts2";
                var ptd2Options = common.getMonotorOption(data.data.monitor);
                ptd_echarts2.setOption(ptd2Options, true);
                // var chaOption = {
                //     xdata: data.data.monitor.xdate.xdateOne,
                //     title: "旁通道收敛与设计差曲线图（" + directionVal + " " + segmentName + "）",
                //     xname: "环号\n里程",
                //     yname: "cm",
                //     // CjPtd: data.data.CjPtd,
                // };
                data.data.monitor["yAxis"] = ["沉降量(mm)", "收敛(cm)"];
                data.data.monitor["cont"] = "ptd_echarts5";
                var ptd5Options = common.getMonotorOption(data.data.monitor);
                ptd5Options.yAxis.splice(0, 1);
                ptd_echarts5.setOption(ptd5Options, true);
                // var ptd5Options = common.getQvlvOption(chaOption);

                ptd_echarts2.resize();
                ptd_echarts5.resize();
            }
        }
    });
    initPtdCjSlData("ptd_sink", "CJN");
    initPtdCjSlData("ptd_panel", "CJS");
    initPtdCjSlData("ptd_astringe", "SLS");
    initPtdCjSlData("ptd_now_astringe", "SLY");
    initPtdCjSlData("ptd_diff_val", "SLC");
}

//初始化旁通道沉降收敛
function initPtdCjSlData(id, type) {
    var target = $("#ptd_echarts_complete div[data-target='" + id + "'] .checkechar:eq(0)");
    target.find("input").prop("checked", true);
    ptdShowQlEchar();
    toShowPtdEchar(target[0].textContent, true, target.find("input"), id, type);
}


//处理折线是否显示
function toShowPtdEchar(name, falge, input, id, type) {
    var endt = getEnd(id);
    if (endt.length < 1) {
        var option = ptd_detail_echar.getOption();
        var select_key = option.legend[0].data;
        var show_key = option.legend[0].selected;
        if (falge) {
            if (checkExist(ptd_detail_echar, name)) {
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
                            isDensity: '1',
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
        ptd_detail_echar.setOption(option);
        return "";
    }
    var desc = $("#segment_select option:selected").attr("desc");
    $.each(endt, function (i, n) {
        // 收敛沉降
        var option = ptd_echarts2.getOption();
        var select_key = option.legend[0].data;
        var show_key = option.legend[0].selected;
        //与设计值差
        var cha_option = ptd_echarts5.getOption();
        var ptd_select_key = cha_option.legend[0].data;
        var ptd_show_key = cha_option.legend[0].selected;
        var charTitle = name + n;
        if (falge) {
            if (checkExist(ptd_echarts2, charTitle)) {
                var init_date;
                if (type.indexOf("CJS") >= 0) {
                    init_date = $("#ptd_cj_select").val();
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
                    init_date = $("#ptd_sl_select").val();
                }
                var params = {
                    params: {
                        show_key: name,
                        "segment": segmentId,
                        group_segment: group_segment,
                        table: "WHB",
                        desc: desc,
                        isDensity: '0',
                        pointMeter: common.ptdMeter,
                        marginMeter: 50,
                        "type": n,
                        "updown": getDirectionCode(),
                        "line": getline(lineId),
                        "init_date": init_date
                    }
                };
                $.ajax({
                    type: "post",
                    url: ctx + "/module/segment/getPtdDataByName",
                    data: JSON.stringify(params),
                    async: false,
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.code == '200') {
                            if (type == 'SLC') {
                                cha_option.series.push({
                                    name: charTitle,
                                    type: "line",
                                    data: data.data.data
                                });
                                if (common.showPtdCjSl == undefined || type.indexOf(common.showPtdCjSl) >= 0) {
                                    if (ptd_select_key.indexOf(charTitle) < 0) {
                                        ptd_select_key.push(charTitle);
                                        ptd_show_key[charTitle] = true;
                                    }
                                } else {
                                    ptd_show_key[charTitle] = false;
                                }
                                changPtd(ptd_echarts5, cha_option);
                            } else {
                                option.series.push({
                                    name: charTitle,
                                    type: "line",
                                    yAxisIndex: data.data.yAxisIndex,
                                    xAxisIndex: data.data.xAxisIndex,
                                    symbol: data.data.yAxisIndex == 1 ? 'circle' : 'diamond',
                                    symbolSize: 5,
                                    data: data.data.data
                                });
                                if (common.showPtdCjSl == undefined || type.indexOf(common.showPtdCjSl) >= 0) {
                                    if (select_key.indexOf(charTitle) < 0) {
                                        select_key.push(charTitle);
                                        show_key[charTitle] = true;
                                    }
                                } else {
                                    show_key[charTitle] = false;
                                }
                                changPtd(ptd_echarts2, option);
                            }
                        }
                    }
                });
            } else {
                if (common.showPtdCjSl == undefined || type.indexOf(common.showPtdCjSl) >= 0) {
                    if (type == 'SLC') {
                        ptd_select_key.push(charTitle);
                        ptd_show_key[charTitle] = true;
                        changPtd(ptd_echarts5, cha_option);
                    } else {
                        select_key.push(charTitle);
                        show_key[charTitle] = true;
                        changPtd(ptd_echarts2, option);
                    }
                }
            }
        } else {
            if (type == 'SLC') {
                ptd_show_key[String(charTitle)] = false;
                var index = ptd_select_key.indexOf(charTitle);
                if (index > -1) {
                    ptd_select_key.splice(parseInt(index), 1);
                }
                changPtd(ptd_echarts5, cha_option);
            } else {
                show_key[charTitle] = false;
                var index = select_key.indexOf(charTitle);
                if (index > -1) {
                    select_key.splice(parseInt(index), 1);
                }
                changPtd(ptd_echarts2, option);
            }
        }
    });
}

//是否查看曲率
function ptdShowQlEchar() {
    var sl_cha = $("div[data-target='ptd_diff_val'] input:checkbox:checked");
    if ((common.showPtdCjSl == 'SL' || common.showPtdCjSl == undefined) && sl_cha.length > 0) {
        $("#ptd_echarts5").css("display", "block");
        ptd_echarts5.resize();
    } else {
        $("#ptd_echarts5").css("display", "none");
    }
}


//只看收敛或沉降
function ptdShowEchar(o, type) {
    var echartsOption = ptd_echarts2.getOption();
    var legendData = echartsOption.legend[0].selected;
    var lendData = echartsOption.legend[0].data;
    var inputs = $(o).parent().find("input");
    $.each(inputs, function (i, n) {
        $(n).prop("checked", false);
    });
    if (type == 'SL') {
        common.showPtdCjSl = "SL";
        var temp = getCheckBoxs(["ptd_now_astringe", "ptd_astringe"]);
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
        ($(o).find("input")).prop("checked", true);
        changPtd(ptd_echarts2, echartsOption);
        var slinput = $("#ptd_echarts_complete input[data-type^='SL']:checkbox:checked").length;
        if (slinput < 1) {
            initPtdCjSlData("ptd_now_astringe", "SLY");
        }
        ptdShowQlEchar();
    } else if (type == 'CJ') {
        common.showPtdCjSl = "CJ";
        var temp = getCheckBoxs(["ptd_panel", "ptd_sink"]);
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
        ($(o).find("input")).prop("checked", true);
        changPtd(ptd_echarts2, echartsOption);
        var slinput = $("#ptd_echarts_complete input[data-type^='CJ']:checkbox:checked");
        if (slinput < 1) {
            initPtdCjSlData("ptd_sink", "CJN");
            initPtdCjSlData("ptd_panel", "CJS");
        }
        ptdShowQlEchar();
    } else if (type == 'ALL') {
        common.showPtdCjSl = undefined;
        for (var name in legendData) {
            if (legendData[name] == false) {
                legendData[name] = true;
                lendData.push(name);
            }
        }
        // ptdShowQlEchar('QLPD');
        ($(o).find("input")).prop("checked", true);
        changPtd(ptd_echarts2, echartsOption);
        ptdShowQlEchar();
    }
}

//初始沉降下拉框触发事件ptd_cj_select
$("#ptd_cj_select").change(function () {
    var nodes = $("div[data-target='ptd_panel']");
    $.each(nodes, function (i, n) {
        var inputs = $(n).find("input");
        $.each(inputs, function (j, m) {
            if ($(m).is(':checked')) {
                $(m).prop("checked", false);
            }
        });
    });

    var char2Option = ptd_echarts2.getOption();
    var char2_show = char2Option.legend[0].data;
    var char2_selected = char2Option.legend[0].selected;
    var char2_series = char2Option.series;
    for (var i = char2_show.length - 1; i >= 0; i--) {
        var j = String(char2_show[i]).indexOf(cj_sum);
        if (j > -1) {
            char2_show.splice(i, 1);
        }
    }
    for (key in char2_selected) {
        if (String(key).indexOf(cj_sum) > -1) {
            delete char2_selected[String(key)];
        }
    }
    for (var i = char2_series.length - 1; i >= 0; i--) {
        if (String(char2_series[i].name).indexOf(cj_sum) > -1) {
            char2_series.splice(i, 1);
        }
    }
    changPtd(ptd_echarts2, char2Option);
});

$("#ptd_sl_select").change(function () {
    var nodes = $("div[data-target='ptd_astringe']");
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

    var char2Option = ptd_echarts2.getOption();
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
    changPtd(ptd_echarts2, char2Option);
});

$('.mrakPtdBox>div').mouseenter(function () {
    ptdMrakEcharts = $(this).attr("id");
});

// 旁通道
function PtdDataBar(params) {
    var bfbdata;
    var xqDataGk = common.xDataDetail.length;
    bfbdata = params / xqDataGk;
    try {
        var cjTooltip = Math.round(bfbdata * 497);
        var slTooltip = Math.round(bfbdata * 370);
        var PtdEcharts2Index;
        var legend = ptd_echarts2.getOption().legend[0].data;
        var legendStr = legend.join("");
        if (legendStr.indexOf("[收]") >= 0) {
            PtdEcharts2Index = slTooltip - 171;
        } else {
            PtdEcharts2Index = cjTooltip - 171;
        }
        var Ptdect2DataIndex = ptd_echarts2.getOption().legend[0].selected;
        var ect2IndexNum = 1;
        for (var key in Ptdect2DataIndex) {
            if (Ptdect2DataIndex[key] == true) {
                $.each(ptd_echarts2.getOption().series, function (i, n) {
                    if (key == n.name) {
                        ect2IndexNum = i
                    }
                })
            }
        }
        PtdDownAction(params, ect2IndexNum, PtdEcharts2Index)

    } catch (e) {

    }
}

function PtdlinkMainTooltip(params) {
    if (ptdMrakEcharts == "ptd_echarts2") {
        var legend = ptd_echarts2.getOption().legend[0].data;
    } else if (ptdMrakEcharts == "ptd_echarts5") {
        var legend = ptd_echarts5.getOption().legend[0].data;
    }
    var legendStr = legend.join("");
    var percent;
    if (legendStr.indexOf("[收]") >= 0) {
        percent = (params + 171) / 370;
    } else {
        percent = (params + 171) / 497;
    }
    var mainIndex = Math.round(percent * echarts1.getOption().xAxis[0].data.length);
    PtdDataBar(mainIndex)
}

// 旁通道标线联动
function PtdDownAction(params, ect2IndexNum, PtdEcharts2Index) {
    if (ptdMrakEcharts != "echarts1") {
        echarts1.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: params
        });
    }
    if (ptdMrakEcharts != "ptd_echarts2") {
        ptd_echarts2.dispatchAction({
            type: 'showTip',
            seriesIndex: ect2IndexNum,
            dataIndex: PtdEcharts2Index
        });
    }
    if (ptdMrakEcharts != "ptd_echarts2") {
        ptd_echarts5.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: PtdEcharts2Index
        });
    }
}