var $eamWorkOrderSearchForm = $('#eam_workOrder_search_form');
;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;查看</button>',
            '<button type="button" class="btn-primary btn-xs paoding_btn_check" style="margin-right:15px;" paoding-modal-size="1000_600" paoding_opt="WorkOrderCheck"><i class="fa fa-pencil-square-o" ></i>&nbsp;任务审核</button>',
            '<button type="button" class="btn-default btn-xs paoding_btn_update" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;驳回</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            openEditPage('eam_workOrder', editRow, _clickBtn);
        }, 'click .paoding_btn_check': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            //触发check页面菜单点击事件。
            // var checkMenu = $('ul.sidebar-menu').children('li.active').find('li').get(1);
            // checkMenu.click();
            openEditPage('eam_workOrder', editRow, _clickBtn);
            //审核页面加载
            //  $('#my-box-widget').boxWidget('toggle');

        }
    };
    var settings = {
        url: ctx + "/module/eam/workOrder/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $eamWorkOrderSearchForm.find("input[name='keyWord']").val().trim(),
                    status: '2'
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'LINENO',
            title: '线路编号'
        }, {
            field: 'START_STATION_NAME',
            title: '开始站'
        }, {
            field: 'END_STATION_NAME',
            title: '结束站'
        }, {
            field: 'HIGHLOW',
            title: '上下行'
        }, {
            field: 'COMPANY_WHDW',
            title: '部门'
        }, {
            field: 'REGISTER_STATION_NAME',
            title: '登记车站'
        }, {
            field: 'PLANSTARTTIME',
            title: '任务开始时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'PLANENDTIME',
            title: '任务结束时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'STATUS_T',
            title: '状态'
        },
            {
                field: '', title: '操作', align: 'center',
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
            $('div.overlay').remove();
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

/**
 * 关闭modal
 */
function closeModal() {
    $MB.closeAndRestModal("commonModal");
}