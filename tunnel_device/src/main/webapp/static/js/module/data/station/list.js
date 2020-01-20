var $stationTable = $("#data_station_table");
var $stationSearchForm = $("#data_station_search_form");
;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            // '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
        ].join('');


        /*
        *
        * function (value, row, index) {
                            var details = '<div  class="updates_bt"  onclick=detailsData(\'' + row.projectid + '\')>' +
                                '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                            // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                            //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                            return details;
                        }
        * */
    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.uuid + "";
            editRow = row;
            openEditPage('data_station', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            row['objId'] = row.uuid;
            editRow = row;
            deleteRoles(row);
        }

    };
    var settings = {
        url: ctx + "/module/data/station/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $stationSearchForm.find("input[name='keyWord']").val().trim(),
                    line: $("#lineType_search_select").val() == null ? '' : $("#lineType_search_select").val(),
                    classType: $("#classType_search_select").val() == null ? '' : $("#classType_search_select").val()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'lineCode', width: 120,
            title: '线路编号'
        }, {
            field: 'stationCode', width: 120,
            title: '站编号'
        }, {
            field: 'stationName',
            title: '站名称'
        }, {
            field: 'isTunnel', width: 120,
            title: '位置',
            formatter: function (v, r) {

                if (r.isTunnel === '1') {
                    return '隧道';
                    // return '<span class="badge bg-green">隧道</span>'
                } else if(r.isTunnel === '0') {
                    return '高架/桥梁';
                }
            }
        }, {
            field: 'classType', width: 120,
            title: '站类型',
            formatter: function (v, r) {
                if (r.classType === '1') {
                    return '车站';
                    // return '<span class="badge bg-blue">车站</span>'
                }
                if (r.classType === '2') {
                    return '风井';
                    // return '<span class="badge bg-green">风井</span>'
                }
                if (r.classType === '3') {
                    return '旁通道';
                    // return '<span class="badge bg-red">旁通道</span>'
                }
                if (r.classType === '4') {
                    return '起止';
                    // return '<span class="badge bg-yellow">起止</span>'
                }
            }
        },
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {

        },
    };

    $MB.initTable('data_station_table', settings);

    //线路下拉框
    loadSelectData(stationData, 'stations', 'line', 'search');
    //站类型
    loadSelectData(dictData, '98', 'class', 'search');
}();

function search() {
    $MB.refreshTable('data_station_table');
}

function refresh() {
    $stationSearchForm[0].reset();
    $MB.refreshTable('data_station_table');
}


