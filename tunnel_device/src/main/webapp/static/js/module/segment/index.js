var mrakEcharts = '', ptdMrakEcharts;
var common = {
    emun: {TCXX: "土层信息", WGSG: "违规施工", BHXX: "病害信息", BJXM: "综合详情", JHXM: "监护项目", WXXX: "大修信息"},
    echartsColor: ['#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3', '#12c764'],
    //判断区间是否被修改
    resetSearch: null,
    status: {
        tab_monitor: "",
        tab_maintain: "",
        tab_ptd: "",
        tab_sand: "",
        tab_dise: "",
        tab_violation: "",
        tab_custody: ""
    },
    faultDuct: null,
    //雷达图=》线路item宽度
    commonData: null,
    legend_arr: [],
    xDataDetail: [],
    xDataDim: [],
    tableOptions: function (options) {
        var tableOption = {
            url: options.url,
            method: options.method == undefined ? 'post' : options.method,
            // contentType:"application/x-www-form-urlencoded",
            striped: true,
            cache: false,
            dataType: "json",
            pagination: true,
            sortable: false,
            sidePagination: "server",
            pageNumber: 1,
            pageSize: 10,
            pageList: [5, 10, 15, 20, 25],
            strictSearch: true,
            showColumns: false,
            silent: true,
            search: true,
            searchText: "",
            minimumCountColumns: 2,
            clickToSelect: false,
            cardView: false,
            detailView: false,
            smartDisplay: false,
            queryParams: options.queryParams == undefined ? getqueryParams : options.queryParams,
            onClickRow: options.onClickRow == undefined ? undefined : options.onClickRow,
            columns: options.columns,
            onLoadSuccess: function (r, d) {
                // if($(".bootstrap-table tbody .no-records-found").length > 0){
                //     $(".bootstrap-table tbody .no-records-found td").text("");
                // }
                if (options.siwper != undefined) {
                    $('#dise-desc').css('display', 'none');
                }
                $('.bootstrap-table tr td').each(function () {
                    $(this).attr("title", $(this).text());
                    $(this).css("cursor", 'pointer');
                });
            },
            responseHandler: options.responseHandler,
        };
        return tableOption;
    },
    //echarts折线图
    lineOptions: function (options) {
        var result = {
            title: {
                left: 'center',
                text: options.title
            },
            animation: false,
            tooltip: {
                trigger: 'axis'
            },
            dataZoom: [{
                start: 0,
                show: false,
                end: 100,
                top: '0%'
            }],
            legend: {
                show: true,
                data: options.legend != null ? options.legend : [],
                orient: 'vertical',
                type: 'scroll',
                left: 20,
                top: 35,
                bottom: 50,
                itemGap: 10,
                itemWidth: 18
            },
            grid: {
                top: 50,
                right: 80,
                bottom: 40,
                left: 180

            },
            xAxis: {
                name: options.xname,
                type: 'category',
                data: options.xaxis
            },
            yAxis: {
                scale: true,
                name: '测量结果',
                boundaryGap: [0.1, 0.1],
                type: 'value'
            },
            series: []
        };
        var series_item = {
            name: "",
            type: "line",
            data: []
        }
        var temp = JSON.parse(JSON.stringify(series_item));
        temp.name = options.series.name;
        temp.data = options.series.data;
        result.series.push(temp);
        return result;
    },
    //变化最值图（单个）
    getChangeOptions: function (options) {
        var resultOption = {
            title: {
                text: options.title,
                top: 20,
                textStyle: {
                    color: '#fff',
                    fontSize: 12
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: options.xdata,
                    axisLine: {
                        lineStyle: {
                            color: '#fff',
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#fff',
                        }
                    }
                }
            ],
            textStyle: {
                color: '#fff',

            },
            series: [
                {
                    type: 'bar',
                    data: options.ydata,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    },
                    itemStyle: {
                        color: options.color,
                    },
                }
            ]
        };
        return resultOption;
    },
    /***
     * 区间环号详情
     * @param options
     */
    getMainOption: function (options) {
        var for_matter = [];
        var legend_data = (function () {
            var return_list = [];
            $.each(options.data, function (i, n) {
                var temp = new Object();
                temp.name = n.name;
                temp.icon = 'none';
                return_list.unshift(temp);
                for_matter.unshift(n.name)
            });
            return return_list;
        })();
        if (common.legend_arr.length < 1 && for_matter.length > 0) {
            common.legend_arr = for_matter;
        }
        var resultOptions = {
            title: {
                top: 0,
                left: 'center',
                text: options.title
            },
            animation: false,
            tooltip: {
                trigger: 'axis',
                alwaysShowContent: true,
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow',
                    animation: false,// 默认为直线，可选为：'line' | 'shadow'
                    shadowStyle: {
                        color: "rgba(150,150,150,0.7)"
                    },
                },
                transitionDuration: 0,
                formatter: function (params, ticket, callback) {
                    common.paramsIndex = params[0].dataIndex;
                    if (mrakEcharts == "echarts1") {
                        if ($("#main_ul li").eq(1).attr("class") == "active") {
                            if (!common.echarts1Tooltip) {
                                common.echarts1Tooltip = true;
                                if (typeof echarts2 != "undefined") {
                                    XqdataBar(params[0].dataIndex);
                                }
                                common.echarts1Tooltip = false;
                            }
                        }
                        if ($("#main_ul li").eq(7).attr("class") == "active") {
                            common.PtdParamsIndex = params[0].dataIndex;
                            if (!common.echarts1Tooltip) {
                                common.echarts1Tooltip = true;
                                if (typeof ptd_echarts2 != "undefined") {
                                    PtdDataBar(params[0].dataIndex);
                                }
                                common.echarts1Tooltip = false;
                            }
                        }
                    }

                    var QuJianRes = '', paramsData;
                    paramsData = params.reverse();
                    for (var i = 0; i < paramsData.length - 1; i++) {
                        QuJianRes += params[i].seriesName + ":" + params[i].data.mock + "项</br>"
                    }
                    return QuJianRes
                }

            },
            legend: {
                data: legend_data,
                itemGap: 6,
                formatter: function (value) {
                    if (value == common.emun.BHXX) {
                        return value + "\n\n\n";
                    } else {
                        return value;
                    }
                },
                orient: 'vertical',
                left: 0,
                bottom: 35,
                itemWidth: 10
            },
            grid: {
                top: 110,
                left: 85,
                bottom: 40,
                right: 50
            },
            xAxis: {
                name: options.xAxis.name,
                type: 'category',
                data: options.xAxis.data,
                axisLabel: {
                    formatter: function (value, index) {
                        // var temp = getXdata();
                        var arr = value.split(",");
                        return arr[0] + "\n" + arr[1];

                    }
                },
                z: 5
            },
            dataZoom: [{
                start: 0,
                show: true,
                end: 100,
                height: 14,
                labelFormatter: function (value, valueStr) {
                    var arr = valueStr.split(",");
                    return arr[0];
                },
                fillerColor: {
                    type: 'linear',
                    x: 0,
                    x2: 1,
                    colorStops: [{
                        offset: 0,
                        color: '#1374be'
                    }, {
                        offset: 0.33,
                        color: '#2b94e4'
                    }, {
                        offset: 0.66,
                        color: '#57e1f6'
                    }, {
                        offset: 1,
                        color: '#c1f7fd'
                    }]
                },
                backgroundColor: {
                    type: 'linear',
                    x: 0,
                    x2: 0,
                    y: 0,
                    y2: 1,
                    colorStops: [{
                        offset: 0.45,
                        color: '#d4d3d3'
                    }, {
                        offset: 0.55,
                        color: '#f3f3f3'
                    }]
                },
                top: 55,
                showDetail: true,
                minSpan: 3,
                // zoomLock: true,
                handleIcon: 'image://' + ctx + '/static/images/hkan.png',
                handleSize: 30,
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            yAxis: {
                type: 'value',
                splitNumber: 8,
                interval: 1,
                max: 8,
                splitLine: {
                    show: false
                },
                axisLabel: '',
                z: 5
            },
            series: [
                //查询区段背景色变化
                // {type: "line",
                // markArea:{
                //     data:[[{
                //         xAxis:''
                //     },{
                //         xAxis:''
                //     }]]
                // },}

            ]
        };

        var item = {
            name: "",
            stack: '总量',
            type: "bar",
            label: {
                show: true,
                fontSize: 10,
                color: '#000',
                formatter: ""
            },
            barWidth: '90%',
            data: [],
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: '#fff',
                    borderColor: '#fff'
                }
            }
        };

        //添加旁通道
        if (options.xPtd != null && options.xPtd != undefined) {
            var coords = new Array();
            $.each(options.xPtd, function (i, n) {
                coords.push({
                    coord: [n[0] + ',' + n[1], 8],
                    label: {
                        formatter: function (value) {
                            return "旁通道" + (i + 1);
                        }
                    }
                });
            });
            var temp1 = Object.assign({}, item);
            temp1.markPoint = {
                data: coords,
                symbolSize: 40,
                itemStyle: {
                    color: '#e74c3c'
                }
            };
            resultOptions.series.push(temp1)
        }
        $.each(options.data, function (i, n) {
            var temp = JSON.parse(JSON.stringify(item));
            temp.name = n.name;
            temp.label.formatter = function (params) {
                if (n.data[params.dataIndex] == '0' || n.name == common.emun.TCXX || n.name == common.emun.WXXX) {
                    return "";
                } else {
                    return n.data[params.dataIndex];
                }
            };
            temp.data = (function () {
                var res = [];
                var index = 0;
                var len = n.data.length;
                while (index <= len) {
                    var obj = new Object();
                    obj.name = options.xAxis.data[index];
                    obj.mock = n.data[index];
                    obj.value = n.weight;
                    res.push(obj);
                    index++;
                }
                return res;
            })();
            temp.itemStyle.normal.color = function (params) {
                return n.color[params.dataIndex];
            };
            resultOptions.series.push(temp)
        });
        return resultOptions;
    },
    /**曲率半径*/
    getQvlvOption: function (options) {
        var result = {
            title: {
                top: 15,
                left: 'center',
                text: options.title
            },
            animation: false,
            legend: {
                type: 'scroll',
                orient: 'vertical',
                show: true,
                data: [],
                left: 5,
                top: 35,
                bottom: 50,
                itemGap: 10,
                itemWidth: 18
            },
            grid: {
                top: 75,
                right: 80,
                bottom: 40,
                left: 180

            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'line',
                    animation: false         // 默认为直线，可选为：'line' | 'shadow'
                },
                transitionDuration: 0,
                formatter: function (params, ticket, callback) {
                    if (mrakEcharts == options.cont) {
                        // if (common.echartsqlTooltip != params[0].dataIndex) {
                        //     common.echartsqlTooltip = params[0].dataIndex;
                        linkMainQLTooltip(params[0].dataIndex);
                        // }
                    }
                    var res = "", sum = {cj: '', sl: ''};
                    for (var i = 0; i < params.length; i++) {
                        var series = params[i];
                        var name = series.axisValue.substr(0, series.axisValue.indexOf(','));
                        if (!sum[name]) {
                            sum[name] = name + "<br/>" + series.marker + series.seriesName + ":" + series.data + "<br/>";
                        } else {
                            sum[name] += series.marker + series.seriesName + ":" + series.data + "<br/>";
                        }
                    }
                    for (key in sum) {
                        res += sum[key];
                    }
                    return res;
                }
            },
            xAxis: {
                name: options.xname,
                type: 'category',
                data: options.xdata,
                nameGap: 15,
                axisLabel: {
                    formatter: function (value, index) {
                        var arr = value.split(",");
                        return arr[0] + "\n" + arr[1];
                    }
                },
                z: 5
            }
            ,
            dataZoom: [{
                start: 0,
                show: false,
                end: zoomEnd,
                top: '0%',
                showDetail: true,
                // zoomLock: true,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            yAxis: {
                name: options.yname,
                boundaryGap: [0.1, 0.1],
                type: 'value',

            },
            series: [],

        };
        var series_item = {
            name: "",
            type: 'bar'
        };
        if (options.CjPtd != null && options.CjPtd != undefined) {
            var coords = new Array();
            $.each(options.CjPtd, function (i, n) {
                var coord_item = new Array();
                coord_item.push({coord: [n[0] + ',' + n[1], 0]});
                coord_item.push({
                    coord: [n[0] + ',' + n[1], 0],
                    label: {
                        formatter: function (value) {
                            return "旁通道";
                        }
                    }
                });
                coords.push(coord_item);
            });
            var temp1 = JSON.parse(JSON.stringify(series_item));
            temp1.markLine = {
                lineStyle: {
                    width: 2,
                    type: 'dashed'
                },
                data: coords,
                itemStyle: {
                    color: '#e74c3c'
                }
            };
            result.series.push(temp1)
        }
        return result;
    },
    /***
     * 沉降收敛曲线图
     * @param options
     */
    getMonotorOption: function (options) {
        var _cont = options.cont;
        var result = {
            title: {
                top: 15,
                left: 'center',
                text: options.title
            },
            animation: false,
            legend: {
                type: 'scroll',
                orient: 'vertical',
                show: true,
                data: [],
                selected: {},
                left: 5,
                // selectedMode:false,
                top: 35,
                bottom: 50,
                itemGap: 10,
                itemWidth: 18
            },
            grid: {
                top: 75,
                right: 80,
                bottom: 40,
                left: 180

            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'line',
                    animation: false,// 默认为直线，可选为：'line' | 'shadow'
                },
                transitionDuration: 0,
                formatter: function (params, ticket, callback) {
                    // if (options.tooltip) {
                    //     if (!common[options.tooltipMark]) {
                    //         common[options.tooltipMark] = true;
                    if (mrakEcharts == _cont) {
                        linkMainTooltip(params[0].dataIndex);
                    } else if (ptdMrakEcharts == _cont) {
                        PtdlinkMainTooltip(params[0].dataIndex)
                    }

                    // }
                    // common[options.tooltipMark] = false;
                    // }
                    var res = "", sum = {cj: '', sl: ''};
                    for (var i = 0; i < params.length; i++) {
                        var series = params[i];
                        var name = series.axisValue.substr(0, series.axisValue.indexOf(','));
                        if (!sum[name]) {
                            sum[name] = name + "<br/>" + series.marker + series.seriesName + ":" + series.data + "<br/>";
                        } else {
                            sum[name] += series.marker + series.seriesName + ":" + series.data + "<br/>";
                        }
                    }
                    for (key in sum) {
                        res += sum[key];
                    }
                    return res;
                }
            },
            xAxis: [
                {
                    name: options.name,
                    type: 'category',
                    data: options.xdate.xdateOne,
                    nameGap: 35,
                    axisLabel: {
                        formatter: function (value, index) {
                            var arr = value.split(",");
                            return arr[0] + "\n" + arr[1];

                        },
                    },
                    z: 5
                },
                {
                    name: options.name,
                    type: 'category',
                    show: false,
                    data: options.xdate.xdateTwo,
                    nameGap: 35,
                    axisLabel: {
                        formatter: function (value, index) {
                            var arr = value.split(",");
                            return arr[0] + "\n" + arr[1];

                        },
                    },
                    z: 5
                }
            ],
            dataZoom: [{
                start: 0,
                show: false,
                end: zoomEnd,
                top: '0%',
                showDetail: true,
                // zoomLock: true,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                },
                xAxisIndex: [0, 1]
            }],
            yAxis: [],
            series: [],

        };
        var yAxis_item = {
            name: '',
            boundaryGap: [0, 0.1],
            type: 'value'
        };
        var series_item = {
            name: "",
            type: "line",
            yAxisIndex: 0,
            xAxisIndex: 1,
            data: []
        }
        $.each(options.yAxis, function (i, n) {
            var temp = JSON.parse(JSON.stringify(yAxis_item));
            temp.name = n;
            result.yAxis.push(temp);
        });
        if (options.CjPtd != null && options.CjPtd != undefined) {
            var coords = new Array();
            $.each(options.CjPtd, function (i, n) {
                var coord_item = new Array();
                coord_item.push({coord: [n[0] + ',' + n[1], 0]});
                coord_item.push({
                    coord: [n[0] + ',' + n[1], 0],
                    label: {
                        formatter: function (value) {
                            return "旁通道";
                        }
                    }
                });
                coords.push(coord_item);
            });
            var temp1 = JSON.parse(JSON.stringify(series_item));
            temp1.markLine = {
                lineStyle: {
                    width: 2,
                    type: 'dashed',
                },
                data: coords,
                itemStyle: {
                    color: '#e74c3c'
                }
            }
            result.series.push(temp1)
        }

        $.each(options.series, function (i, n) {
            var temp = JSON.parse(JSON.stringify(series_item));
            temp.name = n.name;
            temp.yAxisIndex = n.yAxisIndex;
            temp.yAxisIndex = n.yAxisIndex;
            // temp.connectNulls = true;
            temp.data = n.data;
            result.series.push(temp);
        });
        return result;
    },
    /***
     * 区间概况饼图
     */
    getPerEchartOption: function (options) {
        var surveyoption2 = {
            title: {
                text: options.title,
                left: 'center',
                textStyle: {
                    fontSize: 15
                },
                top: 20,
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            animation: false,
            legend: {
                orient: 'vertical',
                right: 50,
                bottom: 20,
                itemGap: 5
            },
            series: [
                {
                    name: '',
                    type: 'pie',
                    radius: '50%',
                    center: ['50%', '50%'],
                    data: options.date,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        return surveyoption2;
    },
    /***
     * 区间概况柱状图
     */
    getBarEcharts: function (options) {
        var surveyoption = {
            title: {
                text: options.title,
                left: 'center',
                textStyle: {
                    fontSize: 15
                },
                top: 20,
            },
            color: ['#e74c3c'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow',
                    animation: false,// 默认为直线，可选为：'line' | 'shadow'
                },
            },
            grid: {
                left: '5%',
                right: '5%',
                bottom: '10%',
                top: 90,
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: options.xAxisData,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '/环'
                }
            ],
            animation: false,
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: '40%',
                    data: options.seriesData
                }
            ]
        };
        return surveyoption;
    }

};
//弹出框显示变量
var isPanel = false;
var isAstringe = false;
var nowAstringe = false;
var isSink = false;
var isComp = false;
var isError = false;
var isDiff = false;
var isQlpu = false;
var isSlcj = false;
var ptdComp = false;
var ptdCS = false;
var emComp = false;
var emSlcj = false;
var emError = false;


var sl_type = '[收]';
var cj_type = '[沉]';
var sl_diff = "[收][差]";
var sl_year = "[收][本]";
var sl_sum = "[收][累]";
var cj_now = "[沉][本]";
var cj_year = "[沉][年]";
var cj_sum = "[沉][累]";
// 监护项目状态变量
var jState;

//echarts声明
var echarts1;
//是否显示滑动条
var zoomFalge;
var zoomEnd;
//保存X轴数据，用于X轴区间查询
var showItem = 0;
var lineId = "07";
// var segmentId = "93AAADF844A4424A814F7DF9DF78A5D0";
// var segmentId = "9101F066AD308F71E050007F01009CA2";
var segmentId = "9101F066AD218F71E050007F01009CA2";
//遇到风井可能是多个值
var group_segment = ["AFACF72B56D6465F807AFD5D15CE8F84"];
var directionVal = "上行";

//缩略
var SectionDetail;
var SectiomDim;
var changeDim = 0;
var main_dom = document.getElementById("echarts1");
var segmentName = null;
// //获取线、区间
getCommonData();
// initDict();
// initAllEcharts();
// initEcharts();
// initStatus();
$(function () {

    initAllEcharts();
    //初始化echarts图
    initEcharts();
    //初始化数据字典
    initDict();


//     // 初始话设备列表
//     // var deviceTable = new deviceTableInit();
//     // deviceTable.Init('#device_list');
//
    //自己设置多图联动
    echarts1.on('datazoom', function (params) {
        dataZoomResize(true);
    });
    window.onresize = function () {
        echarts1.resize();
        if ($("#tab_monitor").hasClass("active")) {
            if ($("#monitor_echarts").hasClass("active")) {
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
                if ($("#echarts6").css("display") == "block") {
                    echarts6.resize();
                }
            } else if ($("#monitor_details").hasClass("active")) {
                detail_echar.resize();
            }
        }
        if ($("#tab_ptd").hasClass("active")) {
            if ($("#ptd_details").hasClass("active")) {
                ptd_echarts2.resize();
                ptd_echarts5.resize();
            } else if ($("#ptd_time").hasClass("active")) {
                ptd_detail_echar.resize();
            }

        }
    }

    showItem = $("#direction_select").val();

    $("#main_ul li a").click(function () {
        switch ($(this).attr("href")) {
            case "#tab_custody":
                if ($("#tab_custody").children().length > 0 && common.status.tab_custody != segmentId) {
                    $("#custody_table").bootstrapTable('refresh');
                }
                break;
            case "#tab_violation":
                if ($("#tab_violation").children().length > 0 && common.status.tab_violation != segmentId) {
                    $("#aqbhq_list").bootstrapTable('refresh');
                }
                break;
            case "#tab_dise":
                if ($("#tab_dise").children().length > 0 && common.status.tab_dise != segmentId) {
                    $("#dise_list").bootstrapTable('refresh');
                }
                break;
            case "#tab_monitor":
                if ($("#tab_monitor").children().length > 0 && common.status.tab_monitor != segmentId) {
                    $(".wait-load").css("display", "block");
                    $(".monitor-content").css("display", "none");
                    initSlEcharts();
                    common.status.tab_monitor = segmentId;
                }
                break;
            //     case "#tab_emphasis":
            //         $(".wait-load-emphasis").css("display", "block");
            //         $(".emphasis-content").css("display", "none");
            //         initEmDom();
            //         break;
            case "#tab_sandy":
                if ($("#tab_sand").children().length > 0 && common.status.tab_sandy != segmentId) {
                    $("#tab_sand").bootstrapTable('refresh');
                }
                break;
            case "#tab_ptd":
                if ($("#tab_ptd").children().length > 0 && common.status.tab_ptd != segmentId) {
                    $("#ptd_list").bootstrapTable('refresh');
                }
                break;
            case "#tab_emphasis":
                if ($("#tab_emphasis").children().length > 0 && common.status.tab_emphasis != segmentId) {
                    var obj = {queryTime: new Date().getTime()};
                    $("#emphasis_list").bootstrapTable('resetSearch', JSON.stringify(obj));
                }
                break;
        }
    });
    initStatus();
});


/***
 * 添加折线图
 * @param o
 * @param title
 */
function addExistEchart(o, title) {
    var option = o.getOption();
    var showed = option.legend[0].data;
    var selected = option.legend[0].selected;
    showed.push(title);
    selected[title] = true;
    if (title.indexOf(sl_diff) >= 0) {
        //如果为设计差不需要刷新旁通道
        o.setOption(option);
    } else {
        changPtd(o, option);
    }
}

//区间概况图初始化
function initEcharts() {
    SectionDetail = null;
    SectiomDim = null;
    segmentName = $("#segment_select option[value=" + segmentId + "]").text();
    if (segmentName.length < 1) {
        segmentName = null;
    }
    var param = {
        params: {
            updown: getDirectionCode(), segment: segmentId,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            line: '7',
            aqbhqStatus: '施工'
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getMainCharDate",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {

                setMapLevel(data.data.colorLevel);
                var colorMap = mapLevel(data.data.itemColorLevel);
                data.data.sectionDetail.data = dealMainDate(data.data.sectionDetail);
                var dataInfo = getSetionInfo(colorMap, data.data.sectionDetail.data);
                data.data.sectionDetail.xAxis.data = dealxAxis(data.data.sectionDetail.xAxis.data)
                data.data.sectionDetail.data = dataInfo;

                var xdata_length = data.data.sectionDetail.xAxis.data.length;
                data.data.sectionDetail.title = "地铁" + lineId + "号线概况图（" + directionVal + " " + segmentName + "）";
                var detOptions = common.getMainOption(data.data.sectionDetail);
                detOptions.type = true;
                if (xdata_length > 100) {
                    var Dim = DimXData(data.data.sectionDetail.xAxis.data, data.data.sectionDetail.xPtd, dataInfo);
                    changeDim = 10000 / xdata_length;
                    Dim.title = "地铁" + lineId + "号线概况图（" + directionVal + " " + segmentName + "）";
                    var dimOptions = common.getMainOption(Dim);
                    dimOptions.type = false;
                    SectionDetail = detOptions;
                    echarts1.setOption(dimOptions, true);
                    SectiomDim = echarts1.getOption();
                } else {
                    echarts1.setOption(detOptions, true);
                }
                common.xDataDetail = data.data.sectionDetail.xAxis.data;
                common.xDataDim = Dim.xAxis.data;
            }
        }
    });
    SectionLegend();
}

function getCommonData() {
    $.ajax({
        type: "post",
        url: ctx + "/module/data/base/getSegmentInfo",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({}),
        async: false,
        success: function (data) {
            if (data.code == '200') {
                data.data.sort(sortLine);
                common.commonData = data.data;
                //加载线路下拉框
                loadingLine();
                //加载区间下拉框
                loadSegmentName();
            }
        }
    });
}

//对象排序
function sortLine(a, b) {
    return parseInt(a.LINE_CODE) - parseInt(b.LINE_CODE);
}

function getCharInitTime() {
    var param = {
        params: {
            "segment": segmentId,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            "updown": getDirectionCode(),
            'line': getline(lineId)
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getCharInitTime",
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        data: JSON.stringify(param),
        success: function (data) {

            if (data.code == '200') {
                //监测数据初始化checkbox
                common.initSegment = segmentId;
                common.initCjDate = data.data.legend.cjleglend;
                common.initSlDate = data.data.legend.slleglend;
                // common.initEmSlDate = data.data.legend.emlegend;
            }
        }
    });
}

//加载区间
function loadSegmentName() {
    $('#segment_select').html('');
    var segmentOpt = "<option value='' selected='selected'>--请选择区间--</option>";
    $.each(common.commonData, function (i, n) {
        if (lineId == n.LINE_CODE) {
            $.each(n[getDirectionCode()], function (j, m) {
                var desc = 0;
                if (parseInt(m.START_MILEAGE_CODE) > parseInt(m.END_MILEAGE_CODE)) {
                    var temp = m.START_MILEAGE_CODE;
                    m.START_MILEAGE_CODE = m.END_MILEAGE_CODE;
                    m.END_MILEAGE_CODE = temp;
                    desc = 1;
                }
                if ((segmentName == null && segmentId == m.ID) || (segmentName != null && m.SEGMENT_NAME == segmentName)) {
                    segmentOpt += "<option value='" + m.ID + "' mileage='" + m.START_MILEAGE_CODE + "-" + m.END_MILEAGE_CODE + "'desc='" + desc + "'  station='" + m.START_STATION_CODE + "-" + m.END_STATION_CODE + "' selected='selected' data-content=" + JSON.stringify(m.UUID_LIST) + " >" + m.SEGMENT_NAME + "</option>";
                    segmentName = m.SEGMENT_NAME;
                    common.start_mileage = m.START_MILEAGE_CODE;
                    common.end_mileage = m.END_MILEAGE_CODE;

                    if ((m.START_STATION_CODE + "-" + m.END_STATION_CODE) === '05-06') {
                        $("#tab_pmt_img").attr("src", ctx + "/static/images/segment/07_sl/" + m.START_STATION_CODE + "-" + m.END_STATION_CODE + ".gif");

                    } else {
                        $("#tab_pmt_img").attr("src", ctx + "/static/images/segment/07_sl/" + m.START_STATION_CODE + "-" + m.END_STATION_CODE + ".png");

                    }
                } else {
                    segmentOpt += "<option value='" + m.ID + "' mileage='" + m.START_MILEAGE_CODE + "-" + m.END_MILEAGE_CODE + "'desc'=" + desc + "'   station='" + m.START_STATION_CODE + "-" + m.END_STATION_CODE + "' data-content=" + JSON.stringify(m.UUID_LIST) + " >" + m.SEGMENT_NAME + "</option>"
                }
            });
        }
    });
    $('#segment_select').html(segmentOpt);
}

function loadingLine() {
    $("#lineList").empty();
    var lineOpt = "";
    $.each(common.commonData, function (i, n) {
        if (lineId == n.LINE_CODE) {
            lineOpt += "<option value='" + n.LINE_CODE + "' selected='selected'>" + n.LINE_NAME + "</option>";
        } else {
            lineOpt += "<option value='" + n.LINE_CODE + "' >" + n.LINE_NAME + "</option>"
        }
    });
    $('#lineList').html(lineOpt);
    $("#direction_select option[value='" + directionVal + "']").prop("selected", "selected");
}

/***
 * 配置Echarts初始项
 */
function initAllEcharts() {
    if ($(".mains_width").width()) {
        common.mainEchartsWith = $(".mains_width").width() - 70 + 'px';
        common.CSEchartsWith = $(".mains_width").width() - 190 + 'px';
        main_dom.style.width = common.CSEchartsWith;
    }
    common.Detail = $(main_dom).width() - 100 + 'px';
    echarts1 = echarts.init(main_dom);
    echarts1.dispatchAction({
        type: "showTip",
        x: 5,
        y: 5
    });
    // 离开echarts1之后echarts2tooltip消失
    // echarts1.on("globalout", function () {
    //     if (common.slDataLength) {
    //         echarts2.dispatchAction({
    //             type: 'hideTip'
    //         })
    //     }
    // });
    //区间环号概况点击事件
    echarts1.on('click', function (param) {
        var paramName = String(param.name);
        var navTabsName = $(".nav-tabs-custom li[data-content='" + param.seriesName + "']");
        $(".nav-tabs-custom li[data-content='" + param.seriesName + "'] a").tab("show");
        var tabarName = navTabsName.attr("data-type");
        var tabarHtml = $("#tab_" + tabarName).html();
        if (tabarHtml.length < 1) {
            $('#tab_' + tabarName).load(ctx + "/module/segment/page/" + tabarName);
        }
        var attr = $(".show_map_box div:last-child").attr("style");
        var color = attr.substr(attr.indexOf('#'));
        if (param.color == color && param.seriesName != common.emun.BJXM) {
            return;
        }
        if (param.seriesName == common.emun.JHXM) {
            SubeEch1Click("#custody_table", paramName,"tab_custody");
        } else if (param.seriesName == common.emun.BJXM) {
            //处理详情
            if (echarts1.getOption().type) {
                var duct = paramName.substring(0, paramName.indexOf(','));
                ductRandom(parseInt(duct));
                detailXiangQing(duct);
                // deatilXiangqingTab(duct);
                // getSandyInfoByDuct(duct);
            } else {
                queryByDuct(param.name);
            }
        } else if (param.seriesName == common.emun.WGSG) {
            //违规项目
            SubeEch1Click("#aqbhq_list", paramName,"tab_violation");
        } else if (param.seriesName == common.emun.WXXX) {
            SubeEch1Click("#emphasis_list", paramName,"tab_emphasis");
        } else if (param.seriesName == common.emun.BHXX) {
            $("#diseKey").val("");
            $('#dise-desc').css('display', 'none');
            if (echarts1.getOption().type) {
                var duct_arr = new Object();
                duct_arr.duct = param.name.toString().substring(0, param.name.toString().indexOf(','));
                if (param.seriesName == common.emun.BHXX) {
                    duct_arr.status = "20";
                    changeDise("20");
                } else {
                    duct_arr.status = "60";
                    changeDise("60");
                }
                $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(duct_arr));
            } else {
                var duct_arr = queryByDuct(param.name);
                if (param.seriesName == common.emun.BHXX) {
                    duct_arr.status = "20";
                    changeDise("20");
                } else {
                    duct_arr.status = "60";
                    changeDise("60");
                }
                if($("#tab_fault").html() < 1){
                    common.resetSearch=JSON.stringify(duct_arr);
                    return;
                }else{
                    $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(duct_arr));
                }
            }
        }
    });
    $(document).on("mouseout", "#echarts1", function () {
        if ($("#main_ul li").eq(1).attr("class") == "active") {
            echarts1.dispatchAction({
                type: 'showTip',
                seriesIndex: 1,
                dataIndex: common.paramsIndex
            });
        } else if ($("#main_ul li").eq(7).attr("class") == "active") {
            echarts1.dispatchAction({
                type: 'showTip',
                seriesIndex: 1,
                dataIndex: common.PtdParamsIndex
            });
        } else {
            var o = echarts1.getOption();
            if (o != undefined) {
                o.tooltip[0].alwaysShowContent = false;
                echarts1.setOption(o);
                echarts1.dispatchAction({
                    type: 'hideTip',
                });
            }
        }
    });
    $(document).on("mouseenter", "#echarts1", function (params) {
        var o = echarts1.getOption();
        if (o != undefined) {
            o.tooltip[0].show = true;
            echarts1.setOption(o);
        }
        ;
        mrakEcharts = "echarts1";
        ptdMrakEcharts = "echarts1";
    })
}

/***
 * 初始化数据字典
 */
function initDict() {
    var param = {
        params: {
            "segment": segmentId,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            "updown": getDirectionCode(),
            'line': getline(lineId)
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getDict",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code === 200) {
                var aqbhq = data.data.AQBHQ_LEVEL.map(function (item) {
                    return item.dictValue;
                });
                jState = data.data.JHJC_STATE.map(function (item) {
                    return item.dictValue;
                });
                var status = data.data.AQBHQ_STATUS.map(function (item) {
                    return item.dictValue;
                });
                initSelect("metroline_name", aqbhq, "全部", null);
                initSelect("aqbhq_status", status, "全部", null);
            }
        }

    });
}

/***
 * Tab初始化区间详情表
 */
function initStatus() {
    var param = {
        params: {
            "segment": segmentId,
            start_mileage: common.start_mileage,
            end_mileage: common.end_mileage,
            group_segment: group_segment,
            "updown": getDirectionCode(),
            'line': 7
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getStatus",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {
                initStatusItem(data.data.statis);
                $("#survey_info").empty();
                $.each(data.data.deviceInfo, function (i, n) {
                    var str = " <table><tr><td>设备名称</td><td>" + n.DEVICE_NAME + "</td></tr>\n" +
                        " <tr><td>起始环号(环)</td><td>" + n.START_DUCT_CODE + "-" + n.END_DUCT_CODE + "</td></tr><tr><td>起始里程(m)</td>\n" +
                        " <td>" + n.START_MILEAGE_CODE + "-" + n.END_MILEAGE_CODE + "</td></tr><tr><td>管片宽度(m)</td><td>" + n.DUCT_WIDTH + "</td></tr><tr><td>起始里程标</td>\n" +
                        " <td>" + n.START_MILEAGE_FLAG + "-" + n.END_MILEAGE_FLAG + "</td></tr></table>";
                    $("#survey_info").append(str);
                });

            }
        }
    });
}

/***
 * Tab区间概况统计
 * @param data
 */
function initStatusItem(data) {
    var BH_STATIS = {A: 0, B: 0, C: 0, COUNT: 0, duct: 0};
    var JH_STATIS = {A: 0, B: 0, C: 0, COUNT: 0, duct: 0};
    var WG_STATIS = {A: 0, B: 0, C: 0, COUNT: 0, duct: 0};
    var statis = {BHXX: BH_STATIS, JHXM: JH_STATIS, WGSG: WG_STATIS};
    //处理统计
    for (key in data) {
        for (keys in statis) {
            if (key == keys) {
                $.each(data[key], function (i, n) {
                    var level_A = 'AA', level_B = 'A', step = n.BH_COUNT;
                    if (keys != 'BHXX') {
                        step = 1;
                        try {
                            statis[keys].duct += Math.abs(parseInt(n.END_DUCT_CODE) - parseInt(n.START_DUCT_CODE));
                        } catch (e) {
                        }
                    }
                    if (keys == 'JHXM') {
                        level_A = '特级';
                        level_B = '一级';
                    } else if (keys == 'WGSG') {
                        level_A = '一级';
                        level_B = '二级';
                    }
                    statis[keys].COUNT += step;
                    if (n.LEVEL == level_A) {
                        statis[keys].A += step;
                    } else if (n.LEVEL == level_B) {
                        statis[keys].B += step;
                    } else {
                        statis[keys].C += step;
                    }
                });
            }
        }
    }
    //
    for (s_key in statis) {
        $("span[target-name='" + s_key + "']").text(statis[s_key].COUNT);
        var parent = $("span[target-name='" + s_key + "']").parent().find(".detail-item span");
        $.each(parent, function (i, n) {
            var target = $(n).attr("target-data");
            $(n).text(statis[s_key][target]);
        });
    }
    var surveyLeft = echarts.init(document.getElementById("survey_left"));
    var surveyRight = echarts.init(document.getElementById("survey_right"));
    var barEchar = common.getBarEcharts({
        title: '区间（监护-违规）影响环数概况统计',
        xAxisData: ['监护项目', '违规施工'],
        seriesData: [JH_STATIS.duct, WG_STATIS.duct]
    });
    surveyLeft.setOption(barEchar, true);
    var perEchar = common.getPerEchartOption({
        title: '区间病害统计饼图',
        date: [
            {value: BH_STATIS.A, name: 'AA', itemStyle: {color: '#e74c3c'}},
            {value: BH_STATIS.B, name: 'A', itemStyle: {color: '#f9c922'}},
            {value: BH_STATIS.C, name: '其他', itemStyle: {color: '#2d9a2a'}}
        ]
    });
    surveyRight.setOption(perEchar, true);
}

//为统计添加详情
function StatisCount() {
    var target = $("#tab_gen span[target-name]");
    for (var i = 0; i < target.length; i++) {
        var item = $(target[i]).attr("target-name");
        if (common[item] == undefined) {
            continue;
        }
        $(target[i]).text(common[item].COUNT);
        var detail_item = $(target[i]).parent().find(".detail-item span");
        $.each(detail_item, function (j, m) {
            var item_i = $(m).attr("target-data");
            $(m).text(common[item][item_i]);
        });
    }


}

/***
 * Echats Legend的隐藏位置
 * @constructor
 */
function SectionLegend() {
    echarts1.on('legendselectchanged', function (params) {
        var text = params.name;
        var is_check = params.selected[text];
        if (is_check) {
            var echarOption = echarts1.getOption();
            var data_arr = echarOption.legend[0].data;
            var data_select = echarOption.legend[0].selected;
            var obj, rem_index, offset = 0, add_index = -1;
            var sub_arr;
            $.each(common.legend_arr, function (i, n) {
                if (add_index >= 0 && data_select[String(n)] == false) {
                    offset++;
                }
                if (n == text) {
                    add_index = i;
                    sub_arr = common.legend_arr.slice(i);
                }
            });
            $.each(data_arr, function (i, n) {
                if (n.name == text) {
                    obj = n;
                    rem_index = i;
                }
            })
            if (rem_index > -1 && add_index > -1) {
                data_arr.splice(rem_index, 1);
                data_arr.splice(add_index + offset, 0, obj);
                echarts1.setOption(echarOption);
            }

        } else {
            var echarOption = echarts1.getOption();
            var data_arr = echarOption.legend[0].data;
            var obj, remindex;
            $.each(data_arr, function (i, n) {
                if (n.name == text) {
                    obj = n;
                    remindex = i;
                }
            });
            data_arr.splice(remindex, 1);
            data_arr.unshift(obj);
            echarts1.setOption(echarOption);
        }
    });
}


function changPtd(o, setOption) {
    o.setOption(setOption)
    var _o = o;
    var item_option = _o.getOption();
    if (item_option.series[0].name == "") {
        var temp_min = o.getModel().getComponent('yAxis').axis.scale._extent[0];
        var temp_max = o.getModel().getComponent('yAxis').axis.scale._extent[1];
        var temp_data = item_option.series[0].markLine.data;
        $.each(temp_data, function (i, n) {
            n[0].coord[1] = temp_min;
            n[1].coord[1] = temp_max;
        });
        o.setOption(item_option);
    }
}


//上下行下拉框要触发的事件
$("#direction_select").change(function () {
    directionVal = $("#direction_select").val();
    loadSegmentName();
});
//线路下拉框触发事件
$("#lineList").change(function () {
    lineId = $("#lineList").val();
    segmentId = null;
    group_segment = null;
    segmentName = null;
    loadSegmentName();
});
//区间下拉框要触发的事件
$("#segment_select").change(function () {
    var temp = $("#segment_select").val();
    segmentName = $("#segment_select option[value=" + temp + "]").text();
});
/***
 * 当选值下拉框被改变时触发，指定显示多少环数50-->100
 */
$("#step_echar").change(function () {
    var value_temp = $("#step_echar").val();
    changeEchar(value_temp);
});

//手动设置Echarts联动
function dataZoomResize(falge) {
    if (echarts1.getModel() == undefined) {
        return;
    }
    var startPercent = echarts1.getModel().option.dataZoom[0].start;
    var endPercent = echarts1.getModel().option.dataZoom[0].end;
    if (changeDim > 0 && endPercent - startPercent > changeDim) {
        SectiomDim.dataZoom[0].start = startPercent;
        SectiomDim.dataZoom[0].end = endPercent;
        echarts1.setOption(SectiomDim);
    } else if (changeDim > 0 && endPercent - startPercent <= changeDim) {
        SectionDetail.dataZoom[0].start = startPercent;
        SectionDetail.dataZoom[0].end = endPercent;
        setDetailEcharts(echarts1);
    }
    if ($("#tab_monitor").hasClass("active")) {
        //曲率联动
        var cont2Op = echarts2.getOption();
        var opstart = cont2Op.dataZoom[0].start;
        var opsend = cont2Op.dataZoom[0].end;
        var size_l = Math.abs(opstart - startPercent) + Math.abs(opsend - endPercent);
        if (size_l > 2) {
            cont2Op.dataZoom[0].start = startPercent;
            cont2Op.dataZoom[0].end = endPercent;
            changPtd(echarts2, cont2Op);
            if ($("#echarts3").css("display") == "block") {
                var cont3Op = echarts3.getOption();
                cont3Op.dataZoom[0].start = startPercent;
                cont3Op.dataZoom[0].end = endPercent;
                changPtd(echarts3, cont3Op);
            }
            if ($("#echarts5").css("display") == "block") {
                var cont4Op = echarts5.getOption();
                cont4Op.dataZoom[0].start = startPercent;
                cont4Op.dataZoom[0].end = endPercent;
                echarts5.setOption(cont4Op);
            }
            if ($("#echarts4").css("display") == "block") {
                var cont4Op = echarts4.getOption();
                cont4Op.dataZoom[0].start = startPercent;
                cont4Op.dataZoom[0].end = endPercent;
                changPtd(echarts4, cont4Op);
            }
        }
        if (size_l < 10 && falge) {
            $("#step_echar").val("");
        }
    }
    if (falge) {
        $("#step_echar").val("");
    }
}

/***
 * 折线图单选事件
 * @param id
 * @param clzss_name
 */
function checkEcharFun(id, clzss_name) {
    $("#" + id + " .checkechar" + clzss_name).click(function () {
        var dataId = $(this).attr("data-id");
        var nodes = $("div[data-id=\"" + dataId + "\"]");
        var text = $(this).find("div").text();
        var type = $(this).find("div").attr("data-type");
        var input = $(this).find("input");
        var falge = input.is(':checked');
        // if (falge) {
        $.each(nodes, function (i, n) {
            ($(n).find("input")).prop("checked", !falge);
        });
        if (id.indexOf("ptd") >= 0) {
            toShowPtdEchar(text, !falge, input, dataId.substring(0, dataId.indexOf("-")), type);
        } else if (id.indexOf("em") >= 0) {
            toShowEmEchar(text, !falge, input, dataId.substring(0, dataId.indexOf("-")), type);
        } else {
            totailEchar(text, !falge, input, dataId.substring(0, dataId.indexOf("-")), type);

        }
        // }
    });
}

$('#main_ul a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    switch ($(this).attr("href")) {
        case "#tab_dise":
            break;
        case "#tab_monitor":
            if ($("#monitor_echarts").hasClass("active")) {
                if (echarts2.getOption() != undefined) {
                    dataZoomResize(true);
                }
                echarts2.resize();
                if ($("#echarts3").css("display") == "block") {
                    echarts3.resize();
                }
                if ($("#echarts5").css("display") == "block") {
                    echarts5.resize();
                }
                if ($("#echarts6").css("display") == "block") {
                    echarts6.resize();
                }
                if ($("#echarts4").css("display") == "block") {
                    echarts4.resize();
                }
            } else if ($("#monitor_details").hasClass("active")) {
                detail_echar.resize();
            }
            break;
        case "#tab_ptd":
            if ($("#ptd_details").hasClass("active")) {
                ptd_echarts2.resize();
                ptd_echarts5.resize();
            } else if ($("#ptd_time").hasClass("active")) {
                ptd_detail_echar.resize();
            }
            break;
        case "#tab_emphasis":
            if (common.emphasis_segmentId == undefined) {
                //initEmDom();
                common.emphasis_segmentId = segmentId;
            } else if (common.emphasis_segmentId !== segmentId) {
                $(".wait-load").css("display", "block");
                $(".monitor-content").css("display", "none");
                //initEmDom();
                common.emphasis_segmentId = segmentId;
            } else {
                if ($("#emphasis_echarts").hasClass("active")) {
                    em_echarts2.resize();
                    em_echarts5.resize();
                    em_echarts3.resize();
                    em_echarts4.resize();
                } else if ($("#em_monitor_details").hasClass("active")) {
                    em_detail_echar.resize();
                }
            }
            break;
    }
});

//初始化MapColor
function setMapLevel(arr) {
    $(".show_map_box").empty();
    var str = "";
    var title = "";
    $.each(arr, function (i, item) {
        str += '<div class="show_map" style="background-color: ' + item.dictValue + '">' + item.dictKey + '</div>';
        var rem = "";
        var tem = item.remark.split('#');
        $.each(tem, function (j, n) {
            if (j < tem.length - 1) {
                rem += n + "\n  ";
            } else {
                rem += n;
            }
        });
        if (i < arr.length) {
            title += rem + "\n";
        } else {
            title += rem;
        }
    });
    $(".show_map_box").attr("title", title);
    $(".show_map_box").append(str);
}


//echarts自带联动
// echarts.connect([echarts1,echarts2]);
/***
 * 初始化下拉框
 * @param id
 * @param data []数据
 * @param firstOption 自定义第一个属性“---请选择--”
 * @param type
 */
function initSelect(id, data, firstOption, type) {
    $("#" + id).empty();
    var str = "";
    if (firstOption != null) {
        str += "<option value=''>" + firstOption + "</option>";
    }
    if (type == "CJ") {
        $.each(data, function (i, n) {
            if (firstOption == null && i == data.length - 1) {
                str += "<option value=\"" + n.SHOWKEY + "\" selected='selected'>" + n.SHOWKEY + "</option>";
            } else {
                str += "<option value=\"" + n.SHOWKEY + "\">" + n.SHOWKEY + "</option>";

            }
        });
    } else if (type == "SL") {
        $.each(data, function (i, n) {
            if (firstOption == null && i == data.length - 1) {
                str += "<option value=\"" + n.SL_YEAR + "\" selected='selected'>" + n.SL_YEAR + "</option>";
            } else {
                str += "<option value=\"" + n.SL_YEAR + "\">" + n.SL_YEAR + "</option>";

            }
        });
    } else {
        $.each(data, function (i, n) {
            if (firstOption == null && i == data.length - 1) {
                str += "<option value=\"" + n + "\" selected='selected'>" + n + "</option>";
            } else {
                str += "<option value=\"" + n + "\">" + n + "</option>";

            }
        });
    }
    $("#" + id).append(str);
}

/***
 * 放切换区间进行初始化操作
 */
function initMainEcharts() {
    var change_segment = $.trim($("#segment_select").val());
    if (change_segment.length > 0 && change_segment != segmentId) {
        segmentId = change_segment;
        group_segment = JSON.parse($("#segment_select option:selected").attr("data-content"));
        var mileage = $("#segment_select option:selected").attr("mileage").split('-');
        var station = $("#segment_select option:selected").attr("station");
        common.start_mileage = mileage[0];
        common.end_mileage = mileage[1];
        if (station === '05-06') {
            $("#tab_pmt_img").attr("src", "./static/images/home/aqbhq/07_sl/" + station + ".gif");
        } else {
            $("#tab_pmt_img").attr("src", "./static/images/home/aqbhq/07_sl/" + station + ".png");
        }

        // $("#tab_pmt_img").each(function(){
        //     var img = $(this);
        //     img.attr("isLoad","false");
        // $("#tab_pmt_img").attr("src", "./static/images/home/aqbhq/07_sl/" + station + ".png");
        //     img.load(function(){
        //         img.attr("isLoad","true");
        //     });
        //     img.error(function(){
        //         img.attr("src","./static/images/home/aqbhq/07_sl/image069.png");
        //     });
        // });
        // .attr("src","./static/images/home/aqbhq/07_sl/"+station+".png");
        initEcharts();
        initStatus();
        var tabList = ["main_ul", "myPtdTab", "monitor_tab", "myCustodyTab", "violation_tab", "myFaultTab"];
        $.each(tabList, function (i, n) {
            try {
                $('#' + n + ' li:eq(0) a').tab('show');
            } catch (e) {
            }
        });
    }
}


/***
 * 条件查询选择(根据里程)
 */
function selectlic() {
    initMainEcharts();
    var dataList = common.xDataDetail;
    var start_lic = parseInt($("#start_licheng").val());
    var end_lic = parseInt($("#end_licheng").val());
    var ding_wei = [];
    if (start_lic > 0 && end_lic > 0 && end_lic > start_lic) {
        $.each(dataList, function (i, n) {
            if (parseInt(n[1]) >= start_lic && parseInt(n[1]) <= end_lic) {
                ding_wei.push(i);
            }
        });
        if (ding_wei.length > 0) {
            selectDw(echarts1, ding_wei);
        }
    } else if (start_lic > 0) {
        $.each(dataList, function (i, n) {
            if (parseInt(n[1]) >= start_lic) {
                ding_wei.push(i);
            }
        });
        if (ding_wei.length > 0) {
            selectDw(echarts1, ding_wei);
        }
    } else if (end_lic > 0) {
        $.each(dataList, function (i, n) {
            if (parseInt(n[1]) <= end_lic) {
                ding_wei.push(i);
            }
        });
        if (ding_wei.length > 0) {
            selectDw(echarts1, ding_wei);
        }
    } else {
        return null;
    }
}


/***
 * 条件查询选择(根据换号)
 */
function queryByDuct(duct) {
    var resu = new Object();
    var start;
    var end;
    var _duct = duct.toString();
    var dim_end_index;
    var min_step = 5000 / common.xDataDetail.length;
    var duct_num = _duct.substring(0, _duct.indexOf(','));
    $.each(common.xDataDim, function (i, n) {
        if (n[0] == duct_num) {
            dim_end_index = i;
        }
    });
    if (dim_end_index != 0) {
        var befor_duct = common.xDataDim[dim_end_index - 1];
        var start_index;
        var end_index;
        $.each(common.xDataDetail, function (i, n) {
            if (duct_num == n[0]) {
                start_index = i;
            } else if (befor_duct[0] == n[0]) {
                end_index = i;
            }
        });
        start = (start_index - 1) * 100 / common.xDataDetail.length;
        end = end_index * 100 / common.xDataDetail.length;
        var offset = (min_step + start - end) / 2;
        start -= offset;
        end += offset;
        resu.startDuct = befor_duct[0] < duct_num ? befor_duct[0] : duct_num;
        resu.endDuct = befor_duct[0] > duct_num ? befor_duct[0] : duct_num;
    } else {
        resu.duct = duct_num;
        start = 0;
        end = min_step;
    }
    SectionDetail.dataZoom[0].start = start;
    SectionDetail.dataZoom[0].end = end;
    setDetailEcharts(echarts1);
    $("#step_echar").val("50");
    return resu;
}

/***
 * 当修改显示环数，时操作
 * @param value 10,50，-1
 * @returns {string}
 */
function changeEchar(value) {
    var startPercent = echarts1.getModel().option.dataZoom[0].start;
    var endPercent = echarts1.getModel().option.dataZoom[0].end;
    var reality = endPercent - startPercent;
    if (value > 0) {
        var temp = (value * 100) / common.xDataDetail.length - 0.1;
        var chazhi = reality - temp;
        if (chazhi > 0) {
            var dou_cha = chazhi / 2;
            startPercent += dou_cha;
            endPercent -= dou_cha;

        } else if (chazhi < 0) {
            var dou_cha = chazhi / 2;
            startPercent += dou_cha;
            endPercent -= dou_cha;
        } else {
            return "";
        }
        SectionDetail.dataZoom[0].start = startPercent;
        SectionDetail.dataZoom[0].end = endPercent;
        setDetailEcharts(echarts1);
    } else if (value == -1) {
        if (changeDim > 0) {
            SectiomDim.dataZoom[0].start = 0;
            SectiomDim.dataZoom[0].end = 100;
            echarts1.setOption(SectiomDim);
        } else {
            SectionDetail.dataZoom[0].start = 0;
            SectionDetail.dataZoom[0].end = 100;
            echarts1.setOption(SectionDetail);
        }
        var actionTab = $("#main_ul>li[class='active']>a");
        var obj = new Object();
        obj.time = new Date().getTime();
        switch (actionTab.attr("href")) {

            case "#tab_sand":
                $("#td_sandy").bootstrapTable('resetSearch', JSON.stringify(obj));
                break;
            case "#tab_maintain":
                $("#maintain_list").bootstrapTable('resetSearch', JSON.stringify(obj));
                break;
            case "#tab_custody":
                $("#custody_table").bootstrapTable('resetSearch', JSON.stringify(obj));
                break;
            case "#tab_violation":
                $("#aqbhq_list").bootstrapTable('resetSearch', JSON.stringify(obj));
                break;
            case "#tab_fault":
                var tableId = $("#dise_param .dise_select_bt").attr("data-id");
                changTable(tableId);
                obj.status = tableId;
                $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(obj));
                $('#dise-desc').css('display', 'none');
                changeDise("20");
                break;
        }

    }
    dataZoomResize(false);
}

//选择echarts区间
function selectDw(obj, dw) {
    var falge = true;
    var zoom_start = parseInt(dw.shift());
    var zoom_end = parseInt(dw.pop());
    var selectOption = dw > 100 ? SectiomDim : SectionDetail;
    if (selectOption.progressive == undefined) {
        falge = false;
        var m_length = selectOption.xAxis.data.length - 1;
    } else {
        var m_length = selectOption.xAxis[0].data.length - 1;
    }
    if (dw.length > showItem) {
        selectOption.dataZoom[0].start = zoom_start / m_length * 100;
        selectOption.dataZoom[0].end = (zoom_start + showItem) / m_length * 100;
    } else {
        selectOption.dataZoom[0].start = zoom_start / m_length * 100;
        selectOption.dataZoom[0].end = zoom_end / m_length * 100;
        // selectOption.series[0].markArea={data:[[{xAxis:selectOption.xAxis[0].data[zoom_start]},{xAxis:selectOption.xAxis[0].data[zoom_end]}]]};
    }
    if (falge) {
        obj.setOption(selectOption);
    } else {
        obj.setOption(selectOption, true);
        SectionDetail = obj.getOption();
    }
    dataZoomResize(true);
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

//获取上下行在沉降表中的上下行Code
function getDirectionCode() {
    var updown = "1";
    if (directionVal == '下行') {
        updown = "1";
    } else if (directionVal == '上行') {
        updown = "0";
    }
    return updown;
}

//将页面中的线路07--》7 用于查询沉降数据
function getline(id) {
    if (id.substring(0, 1) == '0') {
        return id.substring(1);
    } else {
        return id;
    }
}

//判断是否添加true,extend
function setDetailEcharts(echarts) {
    if (SectionDetail.progressive == undefined) {
        echarts.setOption(SectionDetail, true);
        SectionDetail = echarts.getOption();
    } else {
        echarts.setOption(SectionDetail);
    }
}

// 显示大图片
function showPriceture(url) {
    // let consont = common.diseClickRow;
    // let str = "等级:"+ consont.FAULT_LEVEL+"&nbsp;&nbsp;&nbsp;&nbsp;"+consont.LINE_ID+"号线&nbsp;&nbsp;&nbsp;&nbsp;"+ consont.DEVICE_NAME+"&nbsp;&nbsp;&nbsp;&nbsp;环号:"+consont.DUCT_CODE+"~"+consont.DUCT_CODE2+"&nbsp;&nbsp;&nbsp;&nbsp;"+consont.FAULT_TYPE;
    // $("#myModalLabel").html(str);
    var resurl = $("#segment_select option:selected").attr("station");
    //$("#layer_ImgModal").find(".modal-dialog").css("width", '80%', "height", '80%');
    if (resurl === '05-06') {
        $("#diserModelImg").attr("src", ctx + "/static/images/segment/07_sl/" + resurl + ".gif");
    } else {
        $("#diserModelImg").attr("src", ctx + "/static/images/segment/07_sl/" + resurl + ".png");
    }
    var resVal = 1;
    $("#diserModelImg").css({transform: "scale(" + resVal + ")"});
    $("#diserModelImg").css({left: 0, top: 0});
    $('#layer_ImgModal').modal('show');
    //设置大小。
    // $("#layer_ImgModal").find(".modal-dialog").css("width", '80%', "height", '80%');
    // $("#layer_ImgModal").modal({
    //     remote: ctx + '/module/home/tab/getPmtImg?id=' + str,
    //     keyboard: true
    // });
}


/*
$(".diserModelPag").mousedown(function (ev) {
    var disX = 0, disY = 0;
    disX = ev.pageX - $(this).offset().left;
    disY = ev.pageY - $(this).offset().top;
    $(document).mousemove(function (ev) {
        $(".diserModelPag").css({left: ev.pageX - disX - 240});
        $(".diserModelPag").css({top: ev.pageY - disY - 80});
    });
    $(document).mouseup(function () {
        if ($(".diserModelPag").position().left > 0) {
            $(".diserModelPag").css({left: 0});
        } else if ($(".diserModelPag").position().left < -1930) {
            $(".diserModelPag").css({left: -1930});
        }
        if ($(".diserModelPag").position().top < -1020) {
            $(".diserModelPag").css({top: -1020});
        } else if ($(".diserModelPag").position().top > 0) {
            $(".diserModelPag").css({top: 0});
        }
        $(document).off()
    })
    return false;
})*/
/*
$('#layer_ImgModal').on('hide.bs.modal', function (e) {
    // debugger;
    $(e.target).removeData("bs.modal").find(".modal-content").empty();
});
$('#layer_ImgModal').on('hidden.bs.modal', function (e) {
    // debugger;
    $(e.target).removeData("bs.modal").find(".modal-content").empty();
});*/

// 点击对应的选项卡跳转
$("#main_ul li").click(function () {
    var tabList = $(this).attr("data-table");
    var tabId = $(this).attr("data-type");
    if ($('#tab_' + tabId).html() != '') {
        if (tabList != undefined && tabList != '') {
            $("#" + tabList).bootstrapTable("refresh")
        }
    } else {
        $('#tab_' + tabId).load(ctx + "/module/segment/page/" + tabId);
    }
    if ($(this).attr("calss") != "active") {
        var o = echarts1.getOption();
        o.tooltip[0].alwaysShowContent = false;
        o.tooltip[0].show = false;
        echarts1.setOption(o);
    }
    ;
});

//显示多选框
function showPopover(t, id) {
    //设置多选框显示的位置，在选择框的中间
    var item_width_str = document.getElementById(id).style.width;
    var item_width = parseInt(item_width_str.substr(0, item_width_str.length - 2));
    var left = t.offsetLeft + t.clientWidth / 2 - item_width / 2;
    var top = t.offsetTop + t.clientHeight + document.body.scrollTop;
    switch (id) {
        case "panel":
            if (isPanel) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isPanel = false;
                return "";
            }
            isPanel = true;
            break;
        case "astringe":
            if (isAstringe) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isAstringe = false;
                return "";
            }
            isAstringe = true;
            break;
        case "now_astringe":
            if (nowAstringe) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                nowAstringe = false;
                return "";
            }
            nowAstringe = true;
            break;
        case "diff_val":
            if (isDiff) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isDiff = false;
                return "";
            }
            isDiff = true;
            break;
        case "sink":
            if (isSink) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isSink = false;
                return "";
            }
            isSink = true;
            break;
        case "cjsl_box":
            if (isSlcj) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isSlcj = false;
                return "";
            }
            isSlcj = true;
            break;
        case "em_cjsl_box":
            if (emSlcj) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emSlcj = false;
                return "";
            }
            emSlcj = true;
            break;
        case "qlpd_box":
            if (isQlpu) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isQlpu = false;
                return "";
            }
            isQlpu = true;
            break;
        case "complete":
            if (isComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isComp = false;
                return "";
            }
            isComp = true;
            left = 0;
            break;
        case "ptd_complete":
            if (ptdComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                ptdComp = false;
                return "";
            }
            ptdComp = true;
            left = 0;
            break;
        case "em_complete":
            if (emComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emComp = false;
                return "";
            }
            emComp = true;
            left = 0;
            break;
        case "ptd_cjsl_box":
            if (ptdCS) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                ptdCS = false;
                return "";
            }
            ptdCS = true;
            break;
        case "error":
            if (isError) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isError = false;
                return "";
            }
            isError = true;
            break;
        case "em_error":
            if (emError) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emError = false;
                return "";
            }
            emError = true;
            break;
        default:
            return "";
    }
    changeBt(id, true);
    $("#" + id).css("display", "block");
    $("#" + id).css("margin-left", left);
    $("#" + id).css("margin-top", top + 5);
}

//显示多选框
function showPopover(t, id) {
    //设置多选框显示的位置，在选择框的中间
    var item_width_str = document.getElementById(id).style.width;
    var item_width = parseInt(item_width_str.substr(0, item_width_str.length - 2));
    var left = t.offsetLeft + t.clientWidth / 2 - item_width / 2;
    var top = t.offsetTop + t.clientHeight + document.body.scrollTop;
    switch (id) {
        case "panel":
            if (isPanel) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isPanel = false;
                return "";
            }
            isPanel = true;
            break;
        case "astringe":
            if (isAstringe) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isAstringe = false;
                return "";
            }
            isAstringe = true;
            break;
        case "now_astringe":
            if (nowAstringe) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                nowAstringe = false;
                return "";
            }
            nowAstringe = true;
            break;
        case "diff_val":
            if (isDiff) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isDiff = false;
                return "";
            }
            isDiff = true;
            break;
        case "sink":
            if (isSink) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isSink = false;
                return "";
            }
            isSink = true;
            break;
        case "cjsl_box":
            if (isSlcj) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isSlcj = false;
                return "";
            }
            isSlcj = true;
            break;
        case "em_cjsl_box":
            if (emSlcj) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emSlcj = false;
                return "";
            }
            emSlcj = true;
            break;
        case "qlpd_box":
            if (isQlpu) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isQlpu = false;
                return "";
            }
            isQlpu = true;
            break;
        case "complete":
            if (isComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isComp = false;
                return "";
            }
            isComp = true;
            left = 0;
            break;
        case "ptd_complete":
            if (ptdComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                ptdComp = false;
                return "";
            }
            ptdComp = true;
            left = 0;
            break;
        case "em_complete":
            if (emComp) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emComp = false;
                return "";
            }
            emComp = true;
            left = 0;
            break;
        case "ptd_cjsl_box":
            if (ptdCS) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                ptdCS = false;
                return "";
            }
            ptdCS = true;
            break;
        case "error":
            if (isError) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                isError = false;
                return "";
            }
            isError = true;
            break;
        case "em_error":
            if (emError) {
                changeBt(id, false);
                $("#" + id).css("display", "none");
                emError = false;
                return "";
            }
            emError = true;
            break;
        default:
            return "";
    }
    changeBt(id, true);
    $("#" + id).css("display", "block");
    $("#" + id).css("margin-left", left);
    $("#" + id).css("margin-top", top + 5);
}

//隐藏多选框
function hide(id) {
    switch (id) {
        case "panel":
            isPanel = false;
            break;
        case "astringe":
            isAstringe = false;
            break;
        case "now_astringe":
            nowAstringe = false;
            break;
        case "diff_val":
            isDiff = false;
            break;
        case "sink":
            isSink = false;
            break;
        case "complete":
            isComp = false;
            break;
        case "ptd_complete":
            ptdComp = false;
            break;
        case "cjsl_box":
            isSlcj = false;
            break;
        case "em_cjsl_box":
            emSlcj = false;
            break;
        case "em_complete":
            emComp = false;
            break;
        case "ptd_cjsl_box":
            ptdCS = false;
            break;
        case "qlpd_box":
            isQlpu = false;
            break;
        case "error":
            isError = false;
            break;
        case "em_error":
            emError = false;
            break;
        default:
            return "";
    }
    changeBt(id, false);
    $("#" + id).css("display", "none");
}

//生成多选框
function getCheckbox(id, legend, selectone, check_name, type, paramType) {
    var item_class = "";
    if (id.indexOf("detail_checkbox") >= 0) {
        item_class = "detail-check";
    }
    var str = "";
    if (paramType.indexOf('CJ') >= 0) {
        if (legend.length > 0) {
            $.each(legend, function (i, n) {
                if (i == 0 && selectone || check_name == n.SHOWKEY) {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n + '"><input type="checkbox"  data-type="' + type + '" value="' + n.SHOWKEY + '" target-n="' + n.CJN + '" target-y="' + n.CJY + '" checked="checked"/><div>' + n.SHOWKEY + '</div></div>'
                } else {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n.SHOWKEY + '"><input type="checkbox" data-type="' + type + '" value="' + n.SHOWKEY + '" target-n="' + n.CJN + '" target-y="' + n.CJY + '" /><div data-type="' + type + '">' + n.SHOWKEY + '</div></div>'
                }
            });
        }
        $.each($("div[data-target='" + id + "']"), function (i, n) {
            $(n).empty();
            $(n).append(str);
        });
    } else if (paramType.indexOf('SL') >= 0) {
        if (legend.length > 0) {
            $.each(legend, function (i, n) {
                if (i == 0 && selectone || check_name == n.SL_YEAR) {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n + '"><input type="checkbox"  data-type="' + type + '" value="' + n.SL_YEAR + '" target-n="' + n.YEARN + '"  checked="checked"/><div>' + n.SL_YEAR + '</div></div>'
                } else {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n.SL_YEAR + '"><input type="checkbox" data-type="' + type + '" value="' + n.SL_YEAR + '" target-n="' + n.YEARN + '"  /><div data-type="' + type + '">' + n.SL_YEAR + '</div></div>'
                }
            });
        }
        $.each($("div[data-target='" + id + "']"), function (i, n) {
            $(n).empty();
            $(n).append(str);
        });
    } else {
        if (legend.length > 0) {
            $.each(legend, function (i, n) {
                if (i == 0 && selectone || check_name == n) {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n + '"><input type="checkbox" data-type="' + type + '" value="' + n + '" checked="checked"/><div>' + n + '</div></div>'
                } else {
                    str += '<div class="checkechar ' + item_class + '" data-id="' + id + '-' + n + '"><input type="checkbox" data-type="' + type + '" value="' + n + '" /><div data-type="' + type + '">' + n + '</div></div>'
                }
            });
        }
        $.each($("div[data-target='" + id + "']"), function (i, n) {
            $(n).empty();
            $(n).append(str);
        });
    }
}

//获取echarts名后的标签如‘[收][累]’
function getEnd(id) {
    var endt = [];
    switch (id) {
        case "panel":
            endt = [cj_sum];
            break;
        case "ptd_panel":
            endt = [cj_sum];
            break;
        case "em_panel":
            endt = [cj_sum];
            break;
        case "now_astringe":
            endt = [sl_year];
            break;
        case "ptd_now_astringe":
            endt = [sl_year];
            break;
        case "em_now_astringe":
            endt = [sl_year];
            break;
        case "diff_val":
            endt = [sl_diff];
            break;
        case "em_diff_val":
            endt = [sl_diff];
            break;
        case "ptd_diff_val":
            endt = [sl_diff];
            break;
        case "sink":
            endt = [cj_now, cj_year];
            break;
        case "em_sink":
            endt = [cj_now, cj_year];
            break;
        case "ptd_sink":
            endt = [cj_now, cj_year];
            break;
        case "astringe":
            endt = [sl_sum];
            break;
        case "em_astringe":
            endt = [sl_sum];
            break;
        case "ptd_astringe":
            endt = [sl_sum];
            break;
        case "detail_checkbox":
            break;
        default:
            return [];
    }
    return endt;
}

//改变选择按钮样式
function changeBt(id, falge) {
    if (falge) {
        $("#select_" + id).removeClass("aqbhq_unselect_bt");
        $("#select_" + id).addClass("aqbhq_select_bt");
    } else {
        $("#select_" + id).removeClass("aqbhq_select_bt");
        $("#select_" + id).addClass("aqbhq_unselect_bt");
    }
}

/***
 * 获取该数组下被选择的input
 */
function getCheckBoxs(arr) {
    var result = new Array();
    $.each(arr, function (d, id) {
        var checked = $("div[data-target='" + id + "'] input:checkbox:checked");
        var endt = getEnd(id);
        $.each(endt, function (i, n) {
            $.each(checked, function (j, m) {
                result.push($(m).val() + n);
            });
        });
    });
    return result;
}

//获取数组对象值
function getDetailValue(data, key) {
    var result = data.map(function (item) {
        return item[key];
    });
    return result;
}

/***
 * 切换病害与维修按钮显示
 * @param id
 */
function changeDise(id) {
    if (id == '20') {
        $("#dise_button").attr("class", "tbale_dise_bt dise_select_bt");
        $("#maintain_button").attr("class", "tbale_dise_bt dise_unselect_bt");
    } else {
        $("#maintain_button").attr("class", "tbale_dise_bt dise_select_bt");
        $("#dise_button").attr("class", "tbale_dise_bt dise_unselect_bt");
    }
}

/***
 * 判断该数据Echarts是否已经存在
 * @param name
 */
function checkExist(o, name) {
    var flage = true;
    var option = o.getOption();
    $.each(option.series, function (i, n) {
        if (n.name == name) {
            flage = false;
        }
    });
    return flage;
}

function ductHuanDy(code) {
    if (code) {
        var numdelete, numadd;
        if (code - 25 <= 0) {
            numdelete = 0;
            numadd = 50;
        } else if (code + 25 >= 1832) {
            numadd = 1832;
            numdelete = 1782;
        } else {
            numdelete = code - 25
            numadd = code + 25
        }
        var strat = 100 - numdelete / 1832 * 100;
        var end = 100 - numadd / 1832 * 100;

        SectionDetail.dataZoom[0].start = strat;
        SectionDetail.dataZoom[0].end = end;
        setDetailEcharts(echarts1);
        var ductCodeIndex = 1832 - code;
        echarts1.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: ductCodeIndex
        });
    }
}


// 监测数据标线联动
function DownAction(ect2IndexNum, echarts2Index, qlIndex, slTooltip, cjTooltip, params) {
    if (mrakEcharts != "echarts1") {
        echarts1.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: params
        });
    }
    if (mrakEcharts != "echarts2") {
        echarts2.dispatchAction({
            type: 'showTip',
            seriesIndex: ect2IndexNum,
            dataIndex: echarts2Index
        });
    }
    if (mrakEcharts != "echarts3") {
        echarts3.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: qlIndex
        });
    }
    if (mrakEcharts != "echarts4") {
        echarts4.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: qlIndex
        });
    }
    if (mrakEcharts != "echarts5") {
        echarts5.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: slTooltip
        });
    }
    if (mrakEcharts != "echarts6") {
        echarts6.dispatchAction({
            type: 'showTip',
            seriesIndex: 3,
            dataIndex: cjTooltip
        });
    }
}

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

function linkMainQLTooltip(index) {
    var percentQl = (index + 1) / monitorSize.qlDataLength;
    var mainIndex = Math.round(percentQl * echarts1.getOption().xAxis[0].data.length);
    XqdataBar(mainIndex)
}


/**
 * 管片分布图点击事件
 * @param id
 * @param Name
 * @constructor
 */
function SubeEch1Click(id, Name,wrapper) {
    if (echarts1.getOption().type) {
        var duct_arr = {queryTime: new Date().getTime()};
        duct_arr.duct = Name.toString().substring(0, Name.toString().indexOf(','));
    } else {
        var duct_arr = queryByDuct(Name);
    }
    if($("#"+wrapper).html() < 1){
        common.resetSearch=JSON.stringify(duct_arr);
        return;
    }else{
        $(id).bootstrapTable('resetSearch', JSON.stringify(duct_arr));
    }
}

/***
 * 为查询添加参数
 * @param date
 * @param param
 */
function addTableParam(data,param){
    if(data != null && data != "" ){
        var obj_param = JSON.parse(data);
        for (key in obj_param) {
            param[String(key)] = obj_param[String(key)];
        }
    }
    return param;
}