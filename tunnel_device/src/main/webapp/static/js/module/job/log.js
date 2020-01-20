var $logSearchForm = $("#job_log_search_form");
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
        url: ctx + "/module/job/log/list",
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
            field: 'logId',
            visible: false
        }, {
            field: 'beanName',
            title: '类名称'
        }, {
            field: 'createTime',
            title: '创建时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'error',
            title: '错误信息'
        }, {
            field: 'status',
            title: '状态'
        }, {
            field: 'methodName',
            title: '方法名称'
        }, {
            field: 'times',
            title: '耗时'
        }],
        onLoadSuccess: function (result, res, data) {
            $('.txt').bind('click', function () {
                tableMethod.showMore(this);
            })
        },
    };

    $MB.initTable('job_log_table', settings);
}();

function search() {
    $MB.refreshTable('job_log_table');
}

function refresh() {
    $(".job-log-table-form")[0].reset();
    $MB.refreshTable('job_log_table');
}

function deleteJobAllLogs(){
    $.ajax({
        type: "POST",
        url: ctx + "/module/job/log/deleteJobAllLogs",
        dataType:'json',
        success: function (data) {
            toUpdatePerson=true;
            if (data.code == 200) {
                $MB.n_success("删除成功");
                $(".job-log-table-form")[0].reset();
                $MB.refreshTable('job_log_table');
            } else {
                $MB.n_danger("删除失败");
            }
        }
    });
}

function deleteJobLogs(){
    var selected = $("#job_log_table").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的日志！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].logId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的日志？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + '/module/job/log/delete', {"ids": ids}, function (r) {
            if (r.code === 200) {
                $MB.n_success("删除成功");
                $MB.refreshTable('job_log_table');
            } else {
                $MB.n_danger(r.message);
            }
        }, 'json');
    });
}



