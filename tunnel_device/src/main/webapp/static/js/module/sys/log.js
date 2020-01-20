var $logSearchForm = $("#sys_log_search_form");
;!function () {

    $(".time-select").datepicker({
        autoclose: true,
        todayHighlight: true,
        /*汉化*/
        language: "zh-CN",
        /*日期格式*/
        format: "yyyy-mm-dd"
        , todayBtn: 'linked', clearBtn: true
    });
    var tableMethod = {
        //多行内容一行显示  点击就显示多行
        showMore: function (obj) {
            if ($(obj).css('white-space') == 'normal') {
                $(obj).css('white-space', 'nowrap')
            } else {
                $(obj).css('white-space', 'normal')
            }
        }
    };


    var settings = {
        url: ctx + "/module/sys/log/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $logSearchForm.find("input[name='keyWord']").val().trim(),
                    sCreateTime: $logSearchForm.find("input[name='sCreateTime']").val().trim(),
                    eCreateTime: $logSearchForm.find("input[name='eCreateTime']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'operateIp',
            title: '访问ip'
        }, {
            field: 'createTime',
            title: '创建时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'userName',
            title: '用户名'
        }, {
            class: 'txt',
            field: 'method',
            title: '方法'
        }, {
            class: 'txt',
            field: 'params',
            title: '参数'
        }, {
            field: 'time',
            title: '耗时'
        }, {
            field: 'location',
            title: '定位'
        }],
        onLoadSuccess: function (result, res, data) {
            $('.txt').bind('click', function () {
                tableMethod.showMore(this);
            })

        },
    };

    $MB.initTable('sys_log_table', settings);
}();

function search() {
    $MB.refreshTable('sys_log_table');
}

function refresh() {
    $logSearchForm[0].reset();
    $MB.refreshTable('sys_log_table');
}

function deleteLogs() {
    var selected = $("#sys_log_table").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的日志！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的日志？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + '/module/sys/log/batchDelete', {"ids": ids}, function (r) {
            if (r.code === 200) {
                $MB.n_success("删除成功");
                refresh();
            } else {
                $MB.n_danger(r.message);
            }
        }, 'json');
    });
}

function deleteAllLogs() {
    $MB.confirm({
        text: "确定清空所有日志？",
        confirmButtonText: "确定"
    }, function () {
        /*var postSettings = {
            url: ctx + '/module/sys/log/batchDelete',
            data: JSON.stringify({ids: "-1"}),
            successFun: function (r) {
                if (r.code === 200) {
                    $MB.n_success("删除成功");
                    refresh();
                } else {
                    $MB.n_danger(r.message);
                }
            }
        };
        postAjax(postSettings);*/
        $.post(ctx + '/module/sys/log/batchDelete', {"ids": "-1"}, function (r) {
            if (r.code === 200) {
                $MB.n_success("删除成功");
                refresh();
            } else {
                $MB.n_danger(r.message);
            }
        }, 'json');
    });
}
