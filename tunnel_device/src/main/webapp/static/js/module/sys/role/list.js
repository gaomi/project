;!function () {
    var $roleSearchForm = $("#sys_role_search_form");

    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.id+"";
            editRow = row;
            openEditPage('sys_role', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            row['objId'] = row.id;
            editRow = row;
            deleteRoles(row);
        }

    };
    var settings = {
        url: ctx + "/module/sys/role/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $roleSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            field: 'id',
            visible: false
        }, {
            field: 'name',
            title: '名称'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 1) {
                    return '<span class="badge bg-green">有效</span>';
                }
                if (value === 0) {
                    return '<span class="badge bg-yellow">冻结</span>';
                }
            }
        }, {
            field: 'remark',
            title: '备注'
        },
            {
                field: 'operate',
                title: '操作',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中增加按钮
                align: 'center', width: 400
            }],
        onLoadSuccess: function (result, res, data) {

        }
    };

    $MB.initTable('sys_role_table', settings);
}();

function search() {
    $MB.refreshTable('sys_role_table');
}

function refresh() {
    debugger;
    $("#sys_role_search_form")[0].reset();
    $MB.refreshTable('sys_role_table');
}


function deleteRoles(rows) {
    var selected = [];
    if (rows != null && rows != undefined) {
        selected.push(rows);
    } else {
        selected = $("#sys_role_table").bootstrapTable('getSelections');
    }
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的角色！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].objId;
        if (i !== (selected_length - 1)) ids += ",";
        // if (userName === selected[i].username) contain = true;//避免删除自己的角色
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function (isConfirm) {
        if (isConfirm.value) {
            $.post(ctx + '/module/sys/role/delete', {"ids": ids}, function (r) {
                if (r.code === 200) {
                    $MB.n_success("删除成功");
                    refresh();
                } else {
                    $MB.n_danger("删除失败");
                }
            }, 'json');
        }
    });
}

