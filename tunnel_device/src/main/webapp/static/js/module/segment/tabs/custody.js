$(function () {
    //监护项目列表
    var cutosyTable = new custodyTableInit();
    cutosyTable.Init('#custody_table');
    initSelect("custody_status", jState, "全部", null);
});
var custodyTableInit = function (number) {
    var oTableInit = new Object();
    oTableInit.Init = function (id) {
        var queryUrl = ctx + "/module/segment/jhjc/list";
        var tableOption = common.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                var custody_name = $.trim($("#custody_name").val());
                var custody_line = $.trim($("#custody_line").val());
                var custody_status = $.trim($("#custody_status").val());
                if (custody_name.length > 0) {
                    param.name = custody_name;
                }
                if (custody_line.length > 0) {
                    param.level = custody_line;
                }
                if (custody_status.length > 0) {
                    param.stats = custody_status;
                }
                /***
                 * 处理异步加载后的参数
                 */
                param = addTableParam(params.search,param);
                param = addTableParam(common.resetSearch,param);
                if(common.resetSearch != null) common.resetSearch=null;
                param.updown = getDirectionCode();
                param.line = getline(lineId);
                param.group_segment = group_segment;
                param.segment = segmentName;
                return {
                    pageSize: params.limit,                         //页面大小
                    pageNum: (params.offset / params.limit) + 1, //页码
                    params: param
                };
            },
            columns: [
                {checkbox: true, visible: false, align: 'center', cellStyle: {css: {"text-align": "center"}}},                 //是否显示复选框
                {field: 'id', title: 'id', visible: false, align: 'center'},
                {field: 'name', title: '项目名称', align: 'center', width: 250},
                {field: 'company', title: '施工单位', align: 'center', width: 250},

                {field: 'level', title: '等级', align: 'center', width: 100},
                {field: 'state', title: '状态', width: 100, align: 'center', width: 100}, {
                    field: 'uStartDuct', title: '影响范围（环）', align: 'center', width: 130,
                    formatter: function (value, row, index) {
                        return row.uStartDuct + "~" + row.uEndDuct;
                    }
                },
                {field: 'summary', title: '描述', width: 200},
                {
                    field: '', title: '操作', align: 'center', width: 120,
                    formatter: function (value, row, index) {
                        var details = '<div  class="updates_bt"  onclick=custodyDetailsData(\'' + row.id + '\')>' +
                            '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                        return details;
                    }
                }],
            onClickRow: function (row, el) {
                ductHuanDy(row.uStartDuct);
            },
            responseHandler: function (result) {
                if (result.code == 200) {
                    common.status.tab_custody = segmentId;
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

//合并列
function mergeColspan(data, colum, target) {
    if (data.length < 2 || data != null) {
        return "";
    }
    var num = 1;
    var index = 0;
    for (var i = 1; i < data.length; i++) {
        if (data[index][colum] != data[i][colum]) {
            if (num > 2) {
                $(target).bootstrapTable("mergeCells", {index: index, field: colum, colspan: 0, rowspan: num});
                num = 1;
            }
            index = i;
        } else {
            num++;
        }
        if (i == data.length - 1 && num > 1) {
            $(target).bootstrapTable("mergeCells", {index: index, field: colum, colspan: 0, rowspan: num});
        }
    }
}

function custodyRefresh() {
    var obj = {queryTime: new Date().getTime()};
    $("#custody_table").bootstrapTable('resetSearch', JSON.stringify(obj));
}

function custodyDetailsData(id) {
    $('#myCustodyTab li:eq(1) a').tab('show');
    $.ajax({
        type: "post",
        data: {"id": id},
        url: ctx + "/module/segment/jhjc/detail",
        success: function (data) {
            if (data.code == '200') {
                initTimeData(data.data);
                $(".timeline li .timeline-body").click(function () {
                    var d_json = $(this).attr("data-content");
                    var obj = JSON.parse(d_json);
                    for (key in obj) {
                        if (key == 'url') {
                            $("#detail_" + key).attr("src", obj[key]);
                        } else {
                            $("#detail_" + key).text(obj[key]);
                        }
                    }
                });
            }
        }
    });
}

function initTimeData(data) {
    data.reverse();
    var main_map = new Object();
    var line_time = [];
    $.each(data, function (i, n) {
        if (main_map[n.status] != undefined) {
            main_map[n.status].push(n);
        } else {
            main_map[n.status] = [];
            main_map[n.status].push(n);
        }
    });
    for (key in main_map) {
        var temp_map = new Object();
        var temp_arr = main_map[key];
        var length = temp_arr.length;
        if (length > 1) {
            var start = temp_arr[0].date;
            var end = temp_arr[length - 1].date;
            temp_map['time'] = end.substr(0, end.indexOf(" ")) + "——" + start.substr(0, start.indexOf(" "));
        } else {
            var thisTime = temp_arr[length - 1].date;
            temp_map['time'] = thisTime.substr(0, thisTime.indexOf(" "));
        }
        temp_map['size'] = length;
        temp_map['title'] = key;
        temp_map['data'] = temp_arr;
        line_time.push(temp_map);
    }
    $(".work_statue span").text("共计" + line_time.length + "个工况");
    initTimeLine(line_time);
}

function initTimeLine(data) {
    $("#timeline").empty();
    var str_heart = "<li><i class=\"fa fa-camera bg-purple\"></i><div class=\"timeline-item \"><div class=\"panel-default\">" +
        "<div class=\"timline_title\" data-toggle=\"collapse\" data-parent=\"#accordion\"";
    var str_end = "</div></div></div></li>";
    $.each(data, function (i, n) {
        var str = "href=\"#collapse_" + i + "\">" + n.title + "<br/><span><i class=\"fa fa-clock-o\"></i>" + n.time + "</span><span class=\"item_length\">" + n.size + "</span></div><div id=\"collapse_" + i + "\" class=\"panel-collapse collapse\">";
        $.each(n.data, function (j, m) {
            str += "<div class=\"timeline-body\" data-content='" + JSON.stringify(m) + "'><span class=\"glyphicon glyphicon-picture\"></span><div class=\"timeline-body-text\"><nobr>"
                + m.detail + "</nobr><br/>" + m.date + "</div></div>";
        });
        $("#timeline").append(str_heart + str + str_end);
    });

}

function toCustodyList() {
    $('#myCustodyTab li:eq(0) a').tab('show');
}


