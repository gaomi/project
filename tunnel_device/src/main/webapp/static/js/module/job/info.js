// $('#commonModal').on('shown.bs.modal',function(e){
//         dataEcho('job_info_detail_form',editRow);
//     });
;!function () {
    function addFunctionAlty(value, row) {
        var statusBtn = '<button type="button"';
        if (row.status == 1) {//1是正常
            statusBtn += ' class="btn-dark btn-xs paoding_btn_play" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Play"><i class="glyphicon glyphicon-play" ></i>&nbsp;开始';
        } else if (row.status == 0) {//0是暂停
            statusBtn += 'class="btn-success btn-xs paoding_btn_pause" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Pause"><i class="glyphicon glyphicon-pause" ></i>&nbsp;暂停';
        }
        statusBtn += '</button>';
        return [statusBtn,
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_play': function (e, value, row, index) {
            $.ajax({
                url: ctx + '/module/job/info/resume',
                data: {jobIds: row.jobId},
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    if (response.code == 200) {//执行成功关闭模态框
                        //重新刷新表格数据
                        console.log($(e.target));
                        $MB.refreshTable('job_info_table');
                    } else {//其他异常
                        alert("code:" + response.code + " message:" + response.message);
                    }
                }
            });
            // $(o_this).parent().html('<div title="暂停任务" onclick="pauseTask(this,\'' + jobId + '\')"><span class="glyphicon glyphicon-pause"></span></div>');
        },
        'click .paoding_btn_pause': function (e, value, row, index) {
            //暂停任务
            $.ajax({
                url: ctx + '/module/job/info/pause',
                data: {jobIds: row.jobId},
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    //alert(JSON.stringify(response.code));
                    if (response.code == 200) {//执行成功关闭模态框
                        //重新刷新表格数据
                        $MB.refreshTable('job_info_table');
                    } else {//其他异常
                        alert("code:" + response.code + " message:" + response.message);
                    }
                }
            });
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            //删除任务
            $MB.confirm({
                    text: "确定删除记录？",
                    confirmButtonText: "确定删除"
                }, function () {
                $.ajax({
                    url: ctx + '/module/job/info/delete',
                    data: {id: row.jobId},
                    type: 'POST',
                    dataType: 'json',
                    success: function (response) {
                        //alert(JSON.stringify(response.code));
                        if (response.code == 200) {//执行成功关闭模态框
                            //重新刷新表格数据
                            $MB.refreshTable('job_info_table');
                        } else {//其他异常
                            alert("code:" + response.code + " message:" + response.message);
                        }
                    }
                });
            });
        },
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.id + "";
            editRow = row;
            editRow.objId = row.jobId;
            openEditPage('job_info', editRow, _clickBtn);

        }

    };
    var settings = {
        url: ctx + "/module/job/info/list",

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params:{keyWord:$("input[name=keyWord]").val().trim()}
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'jobId',
            visible: false
        }, {
            field: 'beanName',
            title: '类名称'
        }, {
            field: 'createTime',
            title: '创建时间',
            formatter: function (value, row, index) {
                //return new Date(parseInt(value)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                return timestampToTime(value);
            }
        }, {
            field: 'status',
            title: '状态'
        }, {
            field: 'cronExpression',
            title: 'cron表达式'
        }, {
            field: 'methodName',
            title: '方法名'
        }, {
            field: 'params',
            title: '参数'
        }, {
            field: 'remark',
            title: '备注'
        },
            /*  {
                  field: '', title: '详情', align: 'center', width: 180,
                  formatter: function (value, row, index) {
                      var htmlText = '<div class="rowOptionBtn">';
                      if (row.status == 0) {//0是正常
                          htmlText += '<div title="暂停任务" onclick="pauseTask(this,\'' + row.jobId + '\')"><span class="glyphicon glyphicon-pause"></span>&nbsp;&nbsp;暂停&nbsp;</div>';
                      } else if (row.status == 1) {//0是暂停
                          htmlText += '<div title="启动任务" onclick="runTask(this,\'' + row.jobId + '\')"><span class="glyphicon glyphicon-play"></span>&nbsp;&nbsp;启动&nbsp;</div>';
                      }
                      htmlText += '</div>'
                      //console.log(row.jobId);
                      //var details = '<div  class="updates_bt"  onclick=detailsData(\'' + row.projectid + '\')>' +
                      var details = htmlText +
                          '<div  class="updates_bt rowOptionBtn" onclick=detailsData(\'' + row.jobId +'\')>' +
                          '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情&nbsp;' +
                          '</div>' +
                          '<div class="deletes_bt rowOptionBtn" onclick="deleteJob(\'' + row.jobId + '\')">' +
                          '<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;删除&nbsp;' +
                          '</div>';
                      // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                      //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                      return details;
                  }
              }],*/
            {
                field: 'operate',
                title: '操作',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中增加按钮
                align: 'center', width: 250
            }],
        onLoadSuccess: function (result, res, data) {
            //debugger;
            // if (result.code == 20000) {
            //
            //
            // }

        },
    };

    $MB.initTable('job_info_table', settings);
}();

function search() {
    $MB.refreshTable('job_info_table');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function dataEcho(id, data) {
    debugger;
    var target = $('#' + id + " [name]");
    $.each(target, function (i, n) {
        var name = $(n).attr("name");
        $(n).val(data[name])
    });
}

//查询任务详情
function detailsData(jobId) {
    debugger;
    var rows = $("#job_info_table").bootstrapTable('getSelections');
    //判断是否选中行
    if (!rows || rows.length == 0) {
        alert("请先选中一行！");
        return;
    }
    //打开motal
    openEditPage('job_info', rows, 800, 600);

}

//删除任务
function deleteJob(jobId) {
    if (window.confirm('您确定删除此条任务吗')) {
        $.ajax({
            url: ctx + '/module/job/info/delete',
            data: {id: jobId},
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                if (response.code == 200) {//执行成功关闭模态框
                    //重新刷新表格数据
                    $MB.refreshTable('job_info_table');
                } else {//其他异常
                    $MB.n_danger(response.message);
                }
            }
        });
    }
}

//添加任务
function addJob() {
    //每次添加任务清空表单
    //document.getElementById("addTask").reset();
    $.ajax({
        url: ctx + '/module/job/info/add',
        data: {
            beanName: $("#beanName2").val(),
            status: $("#status2").val(),
            cronExpression: $("#cronExpression2").val(),
            methodName: $("#methodName2").val(),
            params: $("#params2").val(),
            remark: $("#remark2").val()

        },
        type: 'POST',
        success: function (response) {
            //alert(JSON.stringify(response));
            if (response.code == 200) {//执行成功关闭模态框
                $("#jobModal_add").modal('hide');
                //重新刷新表格数据
                $MB.refreshTable('job_info_table');
            } else {//其他异常
                alert("code:" + response.code + " message:" + response.message);
            }
        }
    });
}

//更新任务详情信息
function updateJob() {
    //alert(window.jobId);
    $.ajax({
        url: ctx + '/module/job/info/update',
        data: {
            jobId: window.jobId,
            beanName: $("#beanName").val(),
            status: $("#status").val(),
            cronExpression: $("#cronExpression").val(),
            methodName: $("#methodName").val(),
            params: $("#params").val(),
            remark: $("#remark").val()

        },
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            //alert(JSON.stringify(response.code));
            if (response.code == 200) {//执行成功关闭模态框
                $("#myModal").modal('hide');
                //重新刷新表格数据
                $MB.refreshTable('job_info_table');
            } else {//其他异常
                alert("code:" + response.code + " message:" + response.message);
            }
        }
    });
}


//恢复任务
function runTask(o_this, jobId) {

}


function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'user/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        }, 'json');
    });
}