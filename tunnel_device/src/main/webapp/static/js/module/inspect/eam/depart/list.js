var $eamPersonSearchForm = $('#eam_depart_search_form');
;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_update" style="margin-right:15px;" ><i class="fa fa-pencil-square-o" ></i>&nbsp;重置密码</button>',
            '<button type="button" class="btn-default btn-xs paoding_btn_update" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;禁用</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_update': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            $MB.confirm({
                text: "确定重置[" + row.departname + "]的密码？",
                confirmButtonText: "确定"
            }, function () {
                $.post(ctx + '/module/eam/depart/resetPassWord', {"id": row.uuid}, function (r) {
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
        url: ctx + "/module/eam/depart/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $eamPersonSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'departid',
            title: '人员编号', width: 200
        }, {
            field: 'departname',
            title: '人员名称'
        }, {
            field: 'insertDate',
            title: '更新日期', width: 300,
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'status',
            title: '状态', width: 200, formatter: function (value, row, index) {
                if (value === 1) return '<span class="badge bg-green">有效</span>';
                if (value === 0) return '<span class="badge badge-success">无效</span>';
            }
        },
            {
                field: '', title: '详情', align: 'center',
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
        }
    };

    $MB.initTable('eam_depart_table', settings);

}();

function search() {
    $MB.refreshTable('eam_depart_table');
}

function refresh() {
    $eamPersonSearchForm[0].reset();
    $MB.refreshTable('eam_depart_table');
}
function getAllFromEam() {
    $.post(ctx + '/module/eam/depart/getAllFromEam', {}, function (r) {
        if (r.code === 200) {
            $MB.n_success("更新成功");
            refresh();
        } else {
            $MB.n_danger(r.message);
        }
    }, 'json');

}
