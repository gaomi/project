var $sandyTable = $("#data_sandy_table");
var $sandySearchForm = $("#data_sandy_search_form");
var sandyEditData;//edit隧道与土层变量
var sandyChangeLine;//list线，上下行
;!function () {
var segmentSyData = {};
    function addSandyBtn(){
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');
    };
    var operateEvents = {
        'click .paoding_btn_del': function (e, value, row, index) {
            $MB.confirm({
                text: "确定删除记录？",
                confirmButtonText: "确定删除"
            }, function () {
                var arr = [];
                arr.push("'"+row.UUID+"'");
                var res = arr.join(',')
                var id = {
                    id:res
                };
                deleteSandy(id)
            });

        },
        "click .paoding_btn_edit":function(e ,value,row,index){
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID + "";
            editRow = row;
            openEditPage('data_sandy', editRow, _clickBtn);
        }
    };
    var settings = {
        url: ctx + "/module/data/sandy/list",
        queryParams: function (params) {
            var paramsSy = {
                keyWord: $sandySearchForm.find("input[name='keyWord']").val().trim(),
                lineUuid: $("#lineType_search_select").val() == null ? '' : $("#lineType_search_select").val(),
                //classType: $("#classType_search_select").val() == null ? '' : $("#classType_search_select").val(),
                updown: $("#updownType_search_select").val() == null ? '' : $("#updownType_search_select").val(),
                group_segment_item:$("#sandyType_search_select").val()== null ? '' : $("#sandyType_search_select").val(),
            };

            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: paramsSy,
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'LINE_CODE',
            title: '线路编号'
        }, {
            field: 'UPDOWN',
            title: '上下行',
            formatter:function (index,row) {
                if(row.UPDOWN=="0"){
                    return "上行"
                }else if(row.UPDOWN=="1"){
                    return "下行"
                }
            }
        },  {
            field: 'SEG_SEGMENT_NAME',
            title: '站区间'
        }, {
            field: 'SANDY_TUNNEL_POSITION_VALUE',
            title: '与隧道位置'
        },{
           field:"SANDY_ID",
            title:"土层编号"
        },{
            field:"SOIL_LEVEL_VALUE",
            title:"土层"
        }, {
            field: 'START_MILEAGE',
            title: '起始里程'
        }, {
            field: 'END_MILEAGE',
            title: '结束里程'
        },
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter:addSandyBtn,
                events:operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
        },
    };

    $MB.initTable('data_sandy_table', settings);
    initSandylineInfo();
    // //线路下拉框
    // loadSelectData(stationData, 'stations', 'line', 'search');

}();

function searchSy() {
    $MB.refreshTable('data_sandy_table');
}

function refresh() {
    $sandySearchForm[0].reset();
    $MB.refreshTable('data_sandy_table');
}

function initSandylineInfo() {
    // edit页面的线和站
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
                segmentSyData = data.data;
                // loadingSandyLine("lineType_search_select", segmentSyData,"LINE_UUID","LINE_NAME",true);
            }
        }
    });
    // edit页面与隧道位置和土层
    $.ajax({
        type:"get",
        url:ctx+"/module/data/sandy/getDict",
        data:{},
        dataType:'json',
        async:false,
        success:function (data) {
            if(data.code==200){
                sandyEditData = data.data;
            }
        }
    });
    // 土层页面的线和区间查询
    $.ajax({
        type: "get",
        url: ctx + "/module/data/sandy/getSandySelect",
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.code == '200') {
                data.data.sort(sortLine);
                sandyChangeLine = data.data;
                var htmlLine = "<option value=''>--请选择--</option>";

                $.each(sandyChangeLine,function (i,n) {
                    htmlLine+="<option value='"+n.LINE_UUID+"'>"+n.LINE_NAME+"</option>"
                });
                $("#lineType_search_select").html(htmlLine);
            }
        }
    });
}
function loadEmphasisSandy(lineIdd,updownId,id) {
    var lineId = $("#"+lineIdd+" option:selected").attr("data-code");
    var updown = $("#"+updownId).val();
    if (lineId == "" || updown == "") {
        return;
    }
    $.each(segmentSyData, function (i, n) {
        if (n.LINE_UUID == lineId) {
            loadingSandyLine(id,n[updown],"ID","SEGMENT_NAME",true);
        }
    });
}

function loadingSandyLine(id, data,key,value,firstB) {
    var obj = $("#" + id);
    obj.empty();
    var str ="";
    if(firstB){
        str += "<option value=''>--请选择--</option>";
    }
    $.each(data, function (i, n) {
        str += "<option value='" + n[key] + "' >" + n[value] + "</option>";
    });
    obj.append(str);
}
$("#lineType_search_select").change(function () {
    loadEmphasisSandyList("lineType_search_select","updownType_search_select","sandyType_search_select")
});
$("#updownType_search_select").change(function () {
    loadEmphasisSandyList("lineType_search_select","updownType_search_select","sandyType_search_select")
});
function loadEmphasisSandyList(lineIdd,updownId,id) {
    var lineId = $("#"+lineIdd+" option:selected").val();
    var updown = $("#"+updownId).val();
    if (lineId == "" || updown == "") {
        return;
    }
    $.each(sandyChangeLine, function (i, n) {
        if (n.LINE_UUID == lineId) {
            loadingSandyLine(id,n[updown],"ID","SEGMENT_NAME",true);
        }
    });
}
// 多选删除
function deleteStations(){
    var resData = $("#data_sandy_table").bootstrapTable("getAllSelections");
    var arr = [];
    $.each(resData,function (i,n) {
        arr.push("'"+n.UUID+"'");
    })
    var arrRes = arr.join(',');
    var id = {
        id:arrRes
    };
    deleteSandy(id)
}

function deleteSandy(id) {
    $.ajax({
        url: ctx + '/module/data/sandy/delete',
        data: id,
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            //alert(JSON.stringify(response.code));
            if (response.code == 200) {//执行成功关闭模态框
                //重新刷新表格数据
                $MB.refreshTable('data_sandy_table');
            } else {//其他异常
                alert("code:" + response.code + " message:" + response.message);
            }
        }
    });
}
//对象排序
function sortLine(a, b) {
    return parseInt(a.LINE_CODE) - parseInt(b.LINE_CODE);
}
