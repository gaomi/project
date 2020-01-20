var $segmentTable = $("#data_segment_table");
var $segmentSearchForm = $("#data_segment_search_form");
;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            // '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
        ].join('');

    }
    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID + "";
            editRow = row;
            openEditPage('data_segment', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            row['objId'] = row.id;
            editRow = row;
            deleteRoles(row);
        }

    };
    var settings = {
        url: ctx + "/module/data/segment/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $segmentSearchForm.find("input[name='keyWord']").val().trim(),
                    line: $("#lineType_search_select").val() == null ? '' : $("#lineType_search_select").val(),
                    //classType: $("#classType_search_select").val() == null ? '' : $("#classType_search_select").val(),
                    updown: $("#updownType_search_select").val() == null ? '' : $("#updownType_search_select").val()
                },
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'LINE_NAME', width: 120,
            title: '线路编号'
        }, {
            field: 'UPDOWN', width: 120,
            title: '上下行',
            formatter: function (v, r) {
                if (r.UPDOWN === '0') {
                    return '<span>上行</span>'
                }
                if (r.UPDOWN === '1') {
                    return '<span>下行</span>'
                }
            }
        }, {
            field: 'START_STATION_NAME',
            title: '起始站'
        }, {
            field: 'END_STATION_NAME',
            title: '结束站'
        }, {
            field: 'START_DUCT_CODE',width: 120,
            title: '起始环号'
        }, {
            field: 'END_DUCT_CODE',width: 120,
            title: '结束环号'
        }, {
            field: 'START_MILEAGE_CODE',
            title: '起始里程'
        }, {
            field: 'END_MILEAGE_CODE',
            title: '结束里程'
        },
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {

        },
    };

    $MB.initTable('data_segment_table', settings);

    //线路下拉框
    loadSelectData(stationData, 'stations', 'line', 'search');

}();

function search() {
    $MB.refreshTable('data_segment_table');
}

function refresh() {
    $segmentSearchForm[0].reset();
    $MB.refreshTable('data_segment_table');
}


