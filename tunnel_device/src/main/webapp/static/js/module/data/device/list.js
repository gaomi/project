var $deviceTable = $("#data_device_table");
var $deviceSearchForm = $("#data_device_search_form");
;!function () {
function addBtnAlty() {
    return['<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>']
};
    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.id + "";
            editRow = row;
            openEditPage('data_device', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {

        }
    };
    var settings = {
        url: ctx + "/module/data/device/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $deviceSearchForm.find("input[name='keyWord']").val().trim(),
                    line: $("#lineType_search_select").val() == null ? '' : $("#lineType_search_select").val(),
                    //classType: $("#classType_search_select").val() == null ? '' : $("#classType_search_select").val(),
                    updown: $("#updownType_search_select").val() == null ? '' : $("#updownType_search_select").val()
                },
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'LINE_ID',
            title: '线路编号'
        }, {
            field: 'HIGH_LOW',
            title: '上下行',
            formatter: function (v, r) {
                if (r.HIGH_LOW === 'U') {
                    return '<span>上行</span>'
                }
                if (r.HIGH_LOW === 'D') {
                    return '<span>下行</span>'
                }
            }
        }, {
            field: 'text',
            title: '设备名称'
        }, {
            field: 'START_STATION_NAME',
            title: '起始站'
        }, {
            field: 'END_STATION_NAME',
            title: '结束站'
        }, {
            field: 'START_DUCT_CODE',
            title: '起始环号'
        }, {
            field: 'END_DUCT_CODE',
            title: '结束环号'
        }, {
            field: 'START_MILEAGE_CODE',
            title: '起始里程'
        }, {
            field: 'END_MILEAGE_CODE',
            title: '结束里程'
        }, {
            field: 'START_MILEAGE_FLAG',
            title: '里程标'
        },
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter:addBtnAlty,
                events:operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
        },
    };

    $MB.initTable('data_device_table', settings);

    //线路下拉框
    loadSelectData(stationData, 'stations', 'line', 'search');

}();

function search() {
    $MB.refreshTable('data_device_table');
}

function refresh() {
    $deviceSearchForm[0].reset();
    $MB.refreshTable('data_device_table');
}


