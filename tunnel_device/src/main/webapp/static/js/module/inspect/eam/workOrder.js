var $eamWorkOrderSearchForm = $('#eam_workOrder_search_form');
;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_update" style="margin-right:15px;" ><i class="fa fa-pencil-square-o" ></i>&nbsp;任务审核</button>',
            '<button type="button" class="btn-default btn-xs paoding_btn_update" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;驳回</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_update': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            debugger;
            $MB.confirm({
                text: "确定重置[" + row.workOrdername + "]的密码？",
                confirmButtonText: "确定"
            }, function () {
                $.post(ctx + '/module/eam/workOrder/resetPassWord', {"id": row.uuid}, function (r) {
                    if (r.code === 200) {
                        $MB.n_success("更新成功");
                        refresh();
                    } else {
                        $MB.n_danger(r.message);
                    }
                }, 'json');
            });
        }
    };
    var settings = {
        url: ctx + "/module/eam/workOrder/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $eamWorkOrderSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'uuid',
            visible: false
        }, {
            field: 'lineno',
            title: '线路编号'
        }, {
            field: 'startsta',
            title: '开始站'
        }, {
            field: 'endsta',
            title: '结束站'
        }, {
            field: 'highlow',
            title: '上下行'
        }, {
            field: 'deptid',
            title: '部门'
        }, {
            field: 'registersta',
            title: '登记车站'
        }, {
            field: 'planstarttime',
            title: '任务开始时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'planendtime',
            title: '任务结束时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'status',
            title: '状态'
        },
            {
                field: '', title: '操作', align: 'center',
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
        }
    };

    $MB.initTable('eam_workOrder_table', settings);

}();

function search() {
    $MB.refreshTable('eam_workOrder_table');
}

function refresh() {
    $eamWorkOrderSearchForm[0].reset();
    $MB.refreshTable('eam_workOrder_table');
}
