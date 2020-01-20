var clickId = '';//左侧菜单点击后的id;
var pDict = "";
;!function () {
    function addFunctionAlty(value, row) {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_del': function (e, value, row, index) {
            //删除任务
            $.ajax({
                url: ctx + '/module/sys/dict/delete',
                data: {id: row.jobId},
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    //alert(JSON.stringify(response.code));
                    if (response.code == 200) {//执行成功关闭模态框
                        //重新刷新表格数据
                        $MB.refreshTable('sys_dict_table');
                    } else {//其他异常
                        alert("code:" + response.code + " message:" + response.message);
                    }
                }
            });

        },
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.id + "";
            editRow = row;
            editRow.objId = row.jobId;
            openEditPage('sys_dict', editRow, _clickBtn);

        }

    };

    var $dictSearchForm = $("#sys_dict_search_form");
    var settings = {
        url: ctx + "/module/sys/dict/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    id: (clickId !== null && clickId !== undefined) ? clickId : '',
                    keyWord: $dictSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'key',
            title: '键'
        }, {
            field: 'value',
            title: '值'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 1) {
                    return '<span class="badge bg-green">有效</span>';
                }
                if (value === 0) {
                    return '<span class="badge badge-warning">无效</span>';
                }
            }
        }, {
            field: 'tableName',
            title: '表名'
        }, {
            field: 'fieldName',
            title: '字段'
        }, {
            field: 'operate',
            title: '操作',
            events: operateEvents,//给按钮注册事件
            formatter: addFunctionAlty,//表格中增加按钮
            align: 'center', width: 120
        }],
        onLoadSuccess: function (result, res, data) {
            //debugger;
            // if (result.code == 20000) {
            //
            //
            // }

        }
    };
    $MB.initTable('sys_dict_table', settings);
}();
var dict_tree_json = [
    {
        text: "Parent 1",
        nodes: [
            {
                text: "Child 1",
                nodes: [
                    {
                        text: "Grandchild 1"
                    },
                    {
                        text: "Grandchild 2"
                    }
                ]
            },
            {
                text: "Child 2"
            }
        ]
    },
    {
        text: "Parent 2"
    },
    {
        text: "Node 4",
        icon: "glyphicon glyphicon-stop",
        selectedIcon: "glyphicon glyphicon-stop",
        color: "#000000",
        backColor: "#FFFFFF",
        href: "#node-1",
        selectable: true,
        state: {
            checked: true,
            expanded: true,
            selected: true
        },
        tags: ['available'],
        nodes: [{
            text: "Child 2"
        }, {
            text: "Child 2"
        }]
    },
    {
        text: "Parent 4"
    },
    {
        text: "Node 1",
        icon: "glyphicon glyphicon-stop",
        selectedIcon: "glyphicon glyphicon-stop",
        color: "#000000",
        backColor: "#FFFFFF",
        href: "#node-1",
        selectable: true,
        state: {
            checked: true,
            disabled: false,
            expanded: true,
            selected: true
        },
        tags: ['available'],
        nodes: [{
            text: "Child 2"
        }, {
            text: "Child 2"
        }]
    }
];

/**加载左侧父选项*/
function getDictParent() {
    $.ajax({
        url: ctx + '/module/sys/dict/parentList',
        data: {flag: "top"},
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            if (response.code == 200) {
                var treeHtml = '';
                $.each(response.data, function (i, r) {
                    treeHtml += '<button type="button" class="btn btn-default btn-block" style="text-align: left;" data-dictId="' + r.id + '" onclick="searchByPid(\'' + r.id + '\');">' + r.tableName + ' : ' + r.name + '</button>';
                });
                pDict = response.data;
                $('#sys_dict_tree').html(treeHtml);
            } else {
                $MB.n_danger(response.message);
            }
        }
    });
}

getDictParent();

function searchByPid(id) {
    clickId = id;
    search();
    clickId = '';
}

function search() {
    $MB.refreshTable('sys_dict_table');
}

function refresh() {
    $("#sys_dict_search_form")[0].reset();
    $MB.refreshTable('sys_dict_table');
}


function deleteDicts() {
    var selected = $("#sys_dict_table").bootstrapTable('getSelections');
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

