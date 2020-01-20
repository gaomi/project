var dataWord = '';
// 跟新数据传的uuid
var dictUuid;
// 类型设置的pid
var dataNum = "";
;!function () {
    getDictTree();

    function addBtnAll() {
        return ['<button type="button"  class="dictUpt btn-primary btn-xs paoding_btn_edit" paoding-modal-size="600_600" paoding_opt="Edit" style="margin-right:15px;"><i class="fa fa-pencil-square-o"></i>&nbsp;更改</button>' +
        '<button type="button" class="dictDel btn-danger btn-xs paoding_btn_del" ><i class="fa fa-trash-o"></i>&nbsp;删除</button>'].join('');
    };
    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.uuid + "";
            dictUuid = row.uuid;
            editRow = row;
            openEditPage('data_dict', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            $MB.confirm({
                text: "确定删除吗",
                confirmButtonText: "确定"
            }, function () {
                $.ajax({
                    url: ctx + '/module/data/dict/delete',
                    data: {id: row.uuid},
                    type: 'POST',
                    dataType: 'json',
                    success: function (response) {
                        //alert(JSON.stringify(response.code));
                        if (response.code == 200) {//执行成功关闭模态框
                            $("#commonModal").modal("hide");
                            //重新刷新表格数据
                            $MB.refreshTable('data_dict_table');
                        } else {//其他异常
                            $MB.n_danger("code:" + response.code + " message:" + response.message);
                        }
                    }
                });
            })
        }
    };
    var settings = {
        url: ctx + "/module/data/dict/list",

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {"flag": 'leaf', "pid": dataNum, "keyWord": dataWord}
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'uuid',
            visible: false
        }, {
            field: 'dictKey',
            title: '键'
        }, {
            field: 'dictValue',
            title: '值'
        }, {
            field: 'pid',
            title: '父id'
        }, {
            field: 'remark',
            title: '备注'
        }, {
            title: '操作',
            events: operateEvents,
            formatter: addBtnAll,
            width: 180,
            align: "center"
        }],

        // onLoadSuccess: function (result, res, data) {
        // debugger;
        // if (result.code == 20000) {
        //
        //
        // }


        // },
    };
    $MB.initTable('data_dict_table', settings);


}();

// 点击添加按钮
function addDictOpen(_this) {
    if (dataNum != "") {
        openEditPage('data_dict', 'add', _this)
    } else {
        $MB.n_info("请先选择数据")
    }

}

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

/**加载左侧树*/
function getDictTree() {
    $.ajax({
        url: ctx + '/module/data/dict/tree',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            var treeHtml = "";
            if (data.code == 200) {
                $.each(data.data, function (i, n) {
                    if (n.hasChild == 1) {
                        treeHtml += "<div class=\"card\"> <div class='dict_frist_warpper'><a class=\"collapsed dict_frist_text\"" +
                            "data-toggle=\"collapse\" hasChild='" + n.hasChild + "'  href='#collapse" + i + "'>" + n.fDictValue +
                            "<span class=\"pull-dict\" data-rotate='_true'> <i class=\"fa fa-angle-left pull-right\"></i></span></a>" +
                            "</div><div id='collapse" + i + "' class=\"collapse\" data-parent=\"#accordion\">"
                    } else {
                        treeHtml += "<div class=\"card\"> <div class='dict_frist_warpper'><a class=\"collapsed dict_frist_text\"" +
                            "data-toggle=\"collapse\" hasChild='" + n.hasChild + "' data-id='" + n.fUuid + "' href='#collapse" + i + "'>" +
                            n.fDictValue + "</a></div><div id='collapse" + i + "' class=\"collapse\" data-parent=\"#accordion\">"
                    }
                    $.each(n.childList, function (j, m) {
                        treeHtml += " <div class=\"card-body item_dict\" data-id='" + m.uuid + "' >" + m.dictValue + "</div>"
                    });
                    treeHtml += "</div></div>"
                });
                $("#accordion").html(treeHtml)
            }
            // if (response.code == 200) {
            //     var treeHtml = '';
            //     $.each(response.data, function (i, r) {
            //         treeHtml += '<button type="button" class="btn btn-default btn-block" style="text-align: left;" data-dictId="' + r.uuid + '" onclick="searchByPid(\'' + r.uuid + '\');">' + (r.forModule ? r.forModule : '无') + ' : ' + r.dictValue + '</button>';
            //     });
            //
            //     $('#data_dict_tree').html(treeHtml);
            // } else {//其他异常
            //     $MB.n_danger(response.message);
            // }
        }
    });
}


function search() {
    dataWord = $('input[name=keyWord]').val().trim();
    $MB.refreshTable('data_dict_table');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

// 点击左侧树添加选中状态
$(document).on("click", ".dict_frist_text", function () {
    var dicthas = $(this).attr("hasChild");
    if (dicthas == 0) {
        $(".dict_frist_text").removeClass("tree_dict_box");
        $(".item_dict").removeClass("tree_dict_child");
        $(this).addClass("tree_dict_box");
        dataNum = $(this).attr("data-id");
        $MB.refreshTable("data_dict_table");
    } else if (dicthas == 1) {
        var childRotae = $(this).find("span").attr("data-rotate");
        if (childRotae == "_true") {
            $(this).find("i").css({transform: "rotate(-90deg)", "transform-origin": "right,center", transition: "all .5s"});
            $(this).find("span").attr("data-rotate", "_false");
        } else if (childRotae == "_false") {
            $(this).find("i").css({transform: "rotate(0deg)", "transform-origin": "right,center", transition: "all .5s"});
            $(this).find("span").attr("data-rotate", "_true");
        }
    }
});
$(document).on("click", ".item_dict", function () {
    $(".item_dict").removeClass("tree_dict_child");
    $(".dict_frist_text").removeClass("tree_dict_box");
    $(this).addClass("tree_dict_child");
    $(this).parent().parent().find(".dict_frist_text").addClass("tree_dict_box");
    dataNum = $(this).attr("data-id");
    $MB.refreshTable("data_dict_table");
});
