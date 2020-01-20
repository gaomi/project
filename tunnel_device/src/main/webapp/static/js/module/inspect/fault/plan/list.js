var faultDict;
;!function () {
    getDictData();
    /*页面内容更换*/
    $(document).on('click', '.more_search', function () {
        $('#more_search_form').addClass('more_search_form');
        $('#more_search_form').removeClass('more_search_form');
        debugger;
    });

    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-small paoding_btn_edit" style="margin-right:15px;"  paoding-modal-size="1000_600" paoding_opt="PalnEdit" ><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            openEditPage('fault_plan', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            deleteUsers(row);
        }
    };
    var settings = {
        url: ctx + "/module/fault/plan/list",

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'PLAN_NO',
            title: '计划编号'
        }, {
            field: 'PLAN_T',
            title: '计划类型'
        }, {
            field: 'WORK_T',
            title: '工作类型'
        }, {
            field: 'STATUS_T',
            title: '状态'
        }, {
            field: 'LINE_NAME',
            title: '线路'
        }, {
            field: 'END_STATION_NAME',
            title: '任务开始站'
        }, {
            field: 'START_STATION_NAME',
            title: '任务结束站'
        }, {
            field: 'UPDOWN2',
            title: '上下行'
        }, {
            field: 'START_TIME',
            title: '开始时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'END_TIME',
            title: '结束时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'CREATE_TIME',
            title: '创建时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'COMPANY_WHDW',
            title: '维护单位'
        }, {
            field: 'remark',
            title: '备注'
        }, {
            field: 'remark',
            title: '备注'
        },
            {
                field: 'operate',
                title: '操作',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中增加按钮
                align: 'center', width: 180
            }],
        onLoadSuccess: function (result, res, data) {
            debugger;
            // if (result.code == 20000) {
            //
            //
            // }

        },
    };

    $MB.initTable('fault_plan_table', settings);
}();


function openPlanEdit(str, flag, btn) {
    var row = $("#fault_plan_table").bootstrapTable('getSelections');
    if (row != null && row != undefined) {
        row['objId'] = row.UUID;
        editRow = row;
        debugger;
        openEditPage('fault_plan', editRow, btn);
    } else {
        $MB.n_warning('请勾选需要编辑的任务！');
        return;
    }


}

/***
 * 数据字典
 */
function getDictData() {
    $.ajax({
        url: ctx + '/module/fault/plan/dict',
        type: 'post',
        data: {},
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                faultDict = data.data;
                $.each(faultDict.major, function (i, r) {
                    r['id'] = r.dictKey;
                    r['text'] = r.dictKey + '-' + r.dictValue;
                });
                $.each(faultDict.plan_type, function (i, r) {
                    r['id'] = r.dictKey;
                    r['text'] = r.dictKey + '-' + r.dictValue;
                });
                $.each(faultDict.work_type, function (i, r) {
                    r['id'] = r.dictKey;
                    r['text'] = r.dictKey + '-' + r.dictValue;
                });
            } else {
                console.log(data.message)
            }
        }
    })
}
