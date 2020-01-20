var deptData = '';
var out_deptTree, rMenu;
var editTreeNode, editParentTreeNode;
;!function () {
    var $userSearchForm = $("#sys_user_search_form");

    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="800_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.USERID;
            editRow = row;
            openEditPage('sys_user', editRow, _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            row['objId'] = row.USERID;
            editRow = row;
            deleteUsers(row);
        }
    };
    var settings = {
        url: ctx + "/module/sys/user/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $userSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'USERID',
            visible: false
        }, {
            field: 'USER_NAME',
            title: '用户名'
        }, {
            field: 'TRUE_NAME',
            title: '姓名'
        }, {
            field: 'DEPTNAME',
            title: '部门'
        },
        //     {
        //     field: 'EMAIL',
        //     title: '邮箱'
        // },
            {
            field: 'MOBILE',
            title: '手机'
        }, {
            field: 'SEX',
            title: '性别',
            formatter: function (value, row, index) {
                if (value === 1) return '男';
                else if (value === 0) return '女';
                else return '保密';
            }
        },
        //     {
        //     field: 'EMPLOYEE_NO',
        //     title: '创建时间',
        //     // formatter:function (value,row,index) {
        //     //        return timestampToTime(value);
        //     // }
        // },
            {
            field: 'STATUS',
            title: '账号状态',
            formatter: function (value, row, index) {
                if (value === 1) {
                    return '<span class="badge bg-green">有效</span>';
                }
                if (value === 0) {
                    return '<span class="badge bg-yellow">冻结</span>';
                }
            }
        },
            {
                field: 'operate',
                title: '操作',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中增加按钮
                align: 'center', width: 180
            }],
        onLoadSuccess: function (result, res, data) {
            //debugger;
            // if (result.code == 20000) {
            //
            //
            // }

        },
    };
    $MB.initTable('sys_user_table', settings);
    loadDeptTree('sys_dept_tree');

    var fold = true;
    $('#treeControl').on('click', function (e) {
        if (fold) {
            $('#treeControl').text('全部展开');
            //$('#fold img').attr('src','../../../image/open.png');
            out_deptTree.expandAll(false);
            fold = false
        } else {
            $('#treeControl').text('全部折叠');
            //$('#fold img').attr('src','../../../image/fold.png');
            out_deptTree.expandAll(true);
            fold = true
        }
    })

}();

function search() {
    $MB.refreshTable('sys_user_table');
}

function refresh() {
    $("#sys_user_search_form")[0].reset();
    $MB.refreshTable('sys_user_table');
}

function deleteUsers(rows) {
    var selected = [];
    if (rows != null && rows != undefined) {
        selected.push(rows);
    } else {
        selected = $("#sys_user_table").bootstrapTable('getSelections');
    }
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].objId;
        if (i !== (selected_length - 1)) ids += ",";
        if (currentUser === selected[i].USER_NAME) contain = true;
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
            $.post(ctx + '/module/sys/user/delete', {"ids": ids}, function (r) {
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

/*加载部门树*/
function loadDeptTree(id) {
    var setting = {     //此处根据自己需要进行配置
        view: {
            dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
            showLine: true,//是否显示节点之间的连线
            fontCss: {'color': 'black', 'font-weight': 'bold'},//字体样式函数
            selectedMulti: false,//设置是否允许同时选中多个节点
            showTitle: true,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        check: {
            //chkboxType: { "Y": "ps", "N": "ps" },
            //chkboxType: {"Y": "", "N": ""},
            //chkStyle: "checkbox",//复选框类型
            enable: false //每个节点上是否显示 CheckBox
        },
        edit: {
            enable: true,
            editNameSelectAll: true,
            removeTitle: '删除',
            renameTitle: '重命名'
        },
        data: {
            key: {
                checked: "checked",
                children: "children",
                isParent: "hasChildren",
                name: "text",
                title: "text",
                url: "url"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: null
            }
        },
        callback: {
            // onClick: onDesignTreeClick,
            //onRightClick: OnRightClick,
            // beforeRename: beforeRename,
            // onCheck: onCheck
            beforeRemove: beforeRemove,//点击删除时触发，用来提示用户是否确定删除（可以根据返回值 true|false 确定是否可以删除）
            beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
            beforeRename: beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求(也是根据返回值 true|false 确定是否可以编辑完成)
            onRemove: onRemove,//(beforeRemove返回true之后可以进行onRemove)删除节点后触发，用户后台操作
            onRename: onRename,//编辑后触发，用于操作后台
            beforeDrag: beforeDrag,//用户禁止拖动节点
            onClick: clickNode//点击节点触发的事件
        }
    };
    $.ajax({
        url: ctx + '/module/sys/dept/tree',
        data: {"ids": 0},
        type: 'POST',
        dataType: 'json',
        success: function (r) {
            deptData = r.data;
            $.fn.zTree.init($("#" + id), setting, r.data);
            out_deptTree = $.fn.zTree.getZTreeObj(id);
            out_deptTree.expandAll(true);
            rMenu = $("#rMenu");
        }
    });
}

/**
 * 添加节点
 * @param obj
 */
function addZTreeNode(obj) {
    var treeObj = $.fn.zTree.getZTreeObj("regionZTree");
    var parentZNode = treeObj.getSelectedNodes(); //获取父节点
    var newNode = obj;
    newNode.nodeFlg = 1;  // 可以自定义节点标识
    newNode = treeObj.addNodes(parentZNode[0], newNode, true);
}

/**
 * 修改子节点
 * @param obj
 */
function editZTreeNode(obj) {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes();
    for (var i = 0; i < nodes.length; i++) {
        nodes[i].name = obj;
        zTree.updateNode(nodes[i]);
    }
}

/**
 *  删除子节点 --选中节点
 * @param obj
 */
function removeZTreeNodeBySelect() {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes(); //获取选中节点
    for (var i = 0; i < nodes.length; i++) {
        zTree.removeNode(nodes[i]);
    }
}

/**
 *  删除子节点 --勾选节点
 * @param obj
 */
function removeZTreeNodeByChecked() {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getCheckedNodes(true); //获取勾选节点
    for (var i = 0; i < nodes.length; i++) {
        zTree.removeNode(nodes[i]);
    }
}

/**
 *  根据节点id 批量删除子节点
 * @param obj
 */
function removeZTreeNodebPi(obj) {
    var idnodes = obj.split(",");
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes();
    for (var i = 0; i < nodes.length; i++) {
        var nodes = zTree.getNodeByParam("id", nodes[i]);
        zTree.removeNode(nodes);
    }
}

/**
 * 选择节点
 * @param obj
 */
function selectzTreeNode(obj) {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var node = zTree.getNodeByParam("id", obj);
    if (node != null) {
        zTree.selectNode(node, true);//指定选中ID的节点
    }
}


function zTreeOnExpand(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodes = zTree.getNodes();
    //所有节点都是展开状态
    open = isOpen(nodes, 0);
    //有节点折叠
    if (open) {
        $('#fold span').text('全部折叠');
        $('#fold img').attr('src', '../../../image/fold.png');
        fold = true
    }
}

function zTreeOnCollapse(event, treeId, treeNode) {
    //所有节点都是折叠状态
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodes = zTree.getNodes();
    var val = isOpen(nodes, 1);
    //有节点折叠
    if (!val) {
        $('#fold span').text('全部展开');
        $('#fold img').attr('src', '../../../image/open.png');
        fold = false
    }
    open = true;
}

function isOpen(nodes, type) {
    for (var i = 0; i < nodes.length; i++) {
        if (type) {//折叠操作
            //只要有一个节点为折叠状态，返回false
            if (open) {
                if (nodes[i].children != undefined) {
                    open = nodes[i].open;
                    if (!open) {
                        return open
                    } else {
                        isOpen(nodes[i].children)//遍历直节点
                    }
                }
            } else {
                return open
            }
        } else {
            if (open) {
                return open
            } else {
                if (nodes[i].children != undefined) {
                    open = nodes[i].open;
                    if (open) {
                        return open
                    } else {
                        isOpen(nodes[i].children)
                    }
                }
            }
        }
    }
}

function zTreeOnCheck(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodes();
    var nodes = zTree.transformToArray(node).length;
    var checkNode = zTree.getCheckedNodes().length;
    if (nodes == checkNode) {
        $('#all').prop('checked', true)
    } else {
        $('#all').prop('checked', false)
    }
}

$('#all').on('click', function (e) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodes();
    var nodes = zTree.transformToArray(node);
    var checkNode = zTree.getCheckedNodes().length;
    //已选中的节点数小于总数 - 全选
    if (checkNode < nodes.length) {
        $('#all').prop('checked', true)
        zTree.checkAllNodes(true);
    } else {
        zTree.checkAllNodes(false);
    }
});

/**
 * 树的右键操作
 * @param event
 * @param treeId
 * @param treeNode
 * @constructor
 */

function OnRightClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        out_deptTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    } else if (treeNode && !treeNode.noR) {
        out_deptTree.selectNode(treeNode);
        showRMenu("node", event.clientX, event.clientY);
    }
}

function showRMenu(type, x, y) {

    $("#rMenu ul").show();
    if (type == "root") {
        $("#m_del").hide();
    } else {
        $("#m_del").show();
        $("#m_edit").show();
    }
    //设置菜单的位置
    y += document.body.scrollTop;
    x += document.body.scrollLeft;

    rMenu.css({"top": y + "px", "left": x + "px", "visibility": "visible"});
    //获取菜单并设置菜单的位置
    $("body").bind("mousedown", onBodyMouseDown);
}

function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({"visibility": "hidden"});
        //$("#contextMenu").stop().fadeOut(200);//获取菜单停止动画，进行隐藏使用淡出效果
    }
}

var addCount = 1;

function addTreeNode() {
    hideRMenu();
    var newNode = {name: "增加" + (addCount++)};
    if (out_deptTree.getSelectedNodes()[0]) {
        newNode.checked = out_deptTree.getSelectedNodes()[0].checked;
        out_deptTree.addNodes(out_deptTree.getSelectedNodes()[0], newNode);
    } else {
        out_deptTree.addNodes(null, newNode);
    }
}

function removeTreeNode() {
    hideRMenu();
    var nodes = out_deptTree.getSelectedNodes();
    if (nodes && nodes.length > 0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg) == true) {
                out_deptTree.removeNode(nodes[0]);
            }
        } else {
            out_deptTree.removeNode(nodes[0]);
        }
    }
}

/*!树的右键操作结束*/

/*树节点的按钮操作*/
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加子节点' onfocus='this.blur();' paoding-modal-size='500_600' paoding_opt='Edit'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function (e) {
        var _clickBtn = e.currentTarget;
        //在这里向后台发送请求保存一个新建的叶子节点，父id为treeNode.id,让后将下面的100+newCount换成返回的id
        // alert("开始添加节点");
        var number = 100 + newCount;
        out_deptTree.addNodes(treeNode, {id: number, pId: treeNode.id, text: "新建节点" + (newCount++)});

        var node = out_deptTree.getNodeByParam("id", number, treeNode);
        node['objId'] = node.id;
        editTreeNode = node;
        editParentTreeNode = treeNode;
        openEditPage('sys_dept', 'add', _clickBtn);
        return false;
    });
}

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
}

function clickNode(e, treeId, treeNode) {
    if (treeNode.id == 11) {
        location.href = treeNode.url;
    } else {
        alert("节点名称：" + treeNode.name + "\n节点id：" + treeNode.id);
    }
}

function beforeRemove(e, treeId, treeNode) {
    return confirm("你确定要删除吗？");
}

function onRemove(e, treeId, treeNode) {
    if (treeNode.isParent) {
        var childNodes = zTree.removeChildNodes(treeNode);
        var paramsArray = new Array();
        for (var i = 0; i < childNodes.length; i++) {
            paramsArray.push(childNodes[i].id);
        }
        alert("删除父节点的id为：" + treeNode.id + "\r\n他的孩子节点有：" + paramsArray.join(","));
        return;
    }
    alert("你点击要删除的节点的名称为：" + treeNode.text + "\r\n" + "节点id为：" + treeNode.id);
}

function beforeEditName(treeId, treeNode) {
    /* if(treeNode.isParent){
        alert("不准编辑非叶子节点！");
        return false;
    } */
    return true;
}

function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length < 3) {
        alert("名称不能少于3个字符！");
        return false;
    }
    return true;
}

function onRename(e, treeId, treeNode, isCancel) {
    alert("修改节点的id为：" + treeNode.id + "\n修改后的名称为：" + treeNode.text);
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

var newCount = 1;
/*!树节点的按钮操作*/