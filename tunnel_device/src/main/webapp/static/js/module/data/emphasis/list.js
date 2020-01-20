var $emphasisTable = $("#data_emphasis_table");
var $emphasisSearchForm = $("#data_emphasis_search_form");
var emphasis = {segmentData:null,selectOption:null};
;!function () {
    function addFunctionAlty(value, row) {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_del': function (e, value, row, index) {
            $MB.confirm({
                text: "确定删除记录？",
                confirmButtonText: "确定删除"
            }, function () {
       var id = {
           id:row.id
       };
             deleteEmphasis(id)
            });

        },
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.uuid + "";
            editRow = row;
            openEditPage('data_emphasis', editRow, _clickBtn);

        }

    };

    var settings = {
        url: ctx + "/module/data/emphasis/list",
        queryParams: function (params) {
            var param={
                duct: $emphasisSearchForm.find("input[name='duct']").val().trim(),
                updown: $("#updownType_search_select").val() == null ? '' : $("#updownType_search_select").val(),
                group_segment:$("#segmentType_search_select").val() == null ? '' : $("#segmentType_search_select").val(),
                lineUuid: $("#emphasisType_search_select").val() == null ? '' : $("#emphasisType_search_select").val()
            };

            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: param
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'ductCode',
            title: '环号'
        }, {
            field: 'maintainTech',
            title: '大修工艺'
        }, {
            field: 'segmentName',
            title: '区间'
        }, {
            field: 'updown',
            title: '上下行',
            formatter: function (value, row, index) {
                if (value === '0') {
                    return "上行";
                } else if (value === '1') {
                    return "下行";
                } else {
                    return "上下行";
                }
            }
        }, {
            field: 'operateCompany',
            title: '施工单位'
        }, {
            field: 'operateTime',
            title: '实施时间'
        }, {
            field: 'operate',
            title: '操作',
            events: operateEvents,//给按钮注册事件
            formatter: addFunctionAlty,//表格中增加按钮
            align: 'center', width: 250
        }
        ],
        onLoadSuccess: function (result, res, data) {

        }
    };

    $MB.initTable('data_emphasis_table', settings);
    initEmphasislineInfo();
}();

function search() {
    $MB.refreshTable('data_emphasis_table');
}

function refresh() {
    $emphasisSearchForm[0].reset();
    $MB.refreshTable('data_emphasis_table');
}

var toUpdatePorject = true;

function updataAllPorject() {
    if (toUpdatePorject) {
        toUpdatePorject = false;
        $.ajax({
            type: "POST",
            url: ctx + "/module/api/emphasis/GetProjectInfo",
            dataType: 'json',
            success: function (data) {
                toUpdatePorject = true;
                if (data.code == 200) {
                    $MB.n_success("更新成功");
                } else {
                    $MB.n_danger("更新失败");
                }
            }
        });
    } else {
        $MB.n_info("努力更新中,请稍等。。。");
    }
}

//对象排序
function sortLine(a, b) {
    return parseInt(a.LINE_CODE) - parseInt(b.LINE_CODE);
}

function initEmphasislineInfo() {
    $.ajax({
        type: "get",
        url: ctx + "/module/data/emphasis/getEmphasisSelect",
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.code == '200') {
                emphasis.selectOption=data.data;
                data.data.sort(sortLine);
                //加载线路下拉框
                loadingEmphasisLine("emphasisType_search_select", data.data,"LINE_UUID","LINE_NAME",true);
            }
        }
    });
}

/***
 *
 * @param id
 * @param data
 * @param key
 * @param value
 * @param firstB 是否添加第一个默认
 */
function loadingEmphasisLine(id, data,key,value,firstB) {
    var obj = $("#" + id);
    obj.empty();
    var str ="";
    if(firstB){
        str += "<option value=\"\">--请选择--</option>";
    }
    $.each(data, function (i, n) {
        str += "<option value='" + n[key] + "'>" + n[value] + "</option>";
    });
    obj.append(str);
}



//线路下拉框触发事件
$("#emphasisType_search_select").change(function () {
    loadEmphasisSegment("emphasisType_search_select","updownType_search_select","segmentType_search_select",emphasis.selectOption);
});
//上下行下拉框触发事件
$("#updownType_search_select").change(function () {
    loadEmphasisSegment("emphasisType_search_select","updownType_search_select","segmentType_search_select",emphasis.selectOption);
});

function loadEmphasisSegment(lineId,updownId,id,data) {
    var lineId = $("#"+lineId).val();
    var updown = $("#"+updownId).val();
    if (lineId == "" || updown == "") {
        return;
    }
    $.each(data, function (i, n) {
        if (n.LINE_UUID == lineId) {
                loadingEmphasisLine(id,n[updown],"ID","SEGMENT_NAME",true);
        }
    });
}

// 多选删除数据
function deleteStations(){
    var resData = $("#data_emphasis_table").bootstrapTable("getAllSelections");
    var arr = [];
   $.each(resData,function (i,n) {
       arr.push("'"+n.uuid+"'");
   })
    var arrRes = arr.join(',');
  var id = {
      id:arrRes
  };
    deleteEmphasis(id)
}
function deleteEmphasis(id){
    $.ajax({
        url: ctx + '/module/data/emphasis/delete',
        data: id,
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            //alert(JSON.stringify(response.code));
            if (response.code == 200) {//执行成功关闭模态框
                //重新刷新表格数据
                $MB.refreshTable('data_emphasis_table');
            } else {//其他异常
                alert("code:" + response.code + " message:" + response.message);
            }
        }
    });
}
