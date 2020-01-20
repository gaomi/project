var $lineTable = $("#data_line_table");
var $lineSearchForm = $("#data_line_search_form");
;!function () {
function addBtnAll() {
    return ["<button type=\"button\"  class=\"dictUpt btn-primary btn-xs paoding_btn_edit\" paoding-modal-size=\"600_600\" paoding_opt=\"Edit\" style=\"margin-right:15px;\"><i class=\"fa fa-pencil-square-o\"></i>&nbsp;编辑</button>"]
}
var operateEvents = {
    "click .paoding_btn_edit":function (e, value, row, index) {
        var _clickBtn = e.currentTarget;
        row['objId'] = row.uuid + "";
        editRow = row;
        openEditPage('data_line', editRow, _clickBtn);
    }
};
    var settings = {
        url: ctx + "/module/data/line/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $lineSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'lineCode',
            title: '线路编号'
        }, {
            field: 'lineName',
            title: '线路名称'
        }, {
            field: 'remark',
            title: '线路颜色',
            formatter: function (v, row) {
                return '<span class="label" style="background-color:' + row.remark + ';">' + row.remark + '</span>';
            }
        },/*,
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter: function (value, row, index) {
                    var details = '<div  class="updates_bt"  onclick=detailsData(\'' + row.projectid + '\')>' +
                        '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                    // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                    //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                    return details;
                }
            }*/
            {
                title: '操作',
                events: operateEvents,
                formatter: addBtnAll,
                width: 180,
                align: "center"
            }],
        onLoadSuccess: function (result, res, data) {

        }
    };

    $MB.initTable('data_line_table', settings);
}();

function search() {
    $MB.refreshTable('data_line_table');
}

function refresh() {
    $lineSearchForm[0].reset();
    $MB.refreshTable('data_line_table');
}


