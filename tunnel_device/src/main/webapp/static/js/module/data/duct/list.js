var $ductTable = $("#data_duct_table");
var $ductSearchForm = $("#data_duct_search_form");
var segmentData = {};
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

                $.ajax({
                    url: ctx + '/module/data/duct/delete',
                    data: {id: row.uuid},
                    type: 'POST',
                    dataType: 'json',
                    success: function (response) {
                        //alert(JSON.stringify(response.code));
                        if (response.code == 200) {//执行成功关闭模态框
                            //重新刷新表格数据
                            $MB.refreshTable('data_duct_table');
                        } else {//其他异常
                            alert("code:" + response.code + " message:" + response.message);
                        }
                    }
                });
            });

        },
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.uuid + "";
            editRow = row;
            openEditPage('data_duct', editRow, _clickBtn);

        }

    };

    var settings = {
        url: ctx + "/module/data/duct/list",
        queryParams: function (params) {
            var param=new Object();
            var segmentValue = $("#segmentType_search_select option:selected").attr("data-group");
            var lineUuid = $("#lineType_search_select").val();
            var ductCode = $ductSearchForm.find("input[name='ductCode']").val().trim();
            if(lineUuid.length > 0){
                param.lineUuid = lineUuid;
            }
            if(ductCode.length > 0){
                param.ductCode = ductCode;
            }
            if( segmentValue != ''){
                param.group_segment = JSON.parse(segmentValue);
            }
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: param
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'DUCT_CODE',
            title: '环号'
        }, {
            field: 'SEGMENT_NAME',
            title: '区间'
        }, {
            field: 'MILEAGE_CODE',
            title: '里程'

        }, {
            field: 'SEQ',
            title: '排序'
        }
        ],
        onLoadSuccess: function (result, res, data) {

        }
    };

    $MB.initTable('data_duct_table', settings);
    initEmphasislineInfo();
}();

function search() {
    $MB.refreshTable('data_duct_table');
}

function refresh() {
    $ductSearchForm[0].reset();
    $MB.refreshTable('data_duct_table');
}

var toUpdatePorject = true;

//对象排序
function sortLine(a, b) {
    return parseInt(a.LINE_CODE) - parseInt(b.LINE_CODE);
}

function initEmphasislineInfo() {
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
                segmentData = data.data;
                //加载线路下拉框
                loadingEmphasisLine("lineType_search_select", segmentData,"LINE_UUID","LINE_NAME",true);
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
        str += "<option value=\"\" data-group=\"\"" +
            ">--请选择--</option>";
    }
    $.each(data, function (i, n) {
        str += "<option value='" + n[key] + "' data-group='"+JSON.stringify(n.UUID_LIST)+"'>" + n[value] + "</option>";
    });
    obj.append(str);
}

//线路下拉框触发事件
$("#emphasisType_search_select").change(function () {
    loadEmphasisSegment("lineType_search_select","updownType_search_select","segmentType_search_select");
});
//上下行下拉框触发事件
$("#updownType_search_select").change(function () {
    loadEmphasisSegment("lineType_search_select","updownType_search_select","segmentType_search_select");
});

function loadEmphasisSegment(lineId,updownId,id) {
    var lineId = $("#"+lineId).val();
    var updown = $("#"+updownId).val();
    if (lineId == "" || updown == "") {
        return;
    }
    $.each(segmentData, function (i, n) {
        if (n.LINE_UUID == lineId) {
            loadingEmphasisLine(id,n[updown],"ID","SEGMENT_NAME",true);
        }
    });
}



