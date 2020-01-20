var $sys_user_edit_form = $('#sys_user_edit_form');
var roleData = '';
var $rolesSelect = $sys_user_edit_form.find("select[name='rolesSelect']");
var $roles = $sys_user_edit_form.find("input[name='roles']");
;!$(function () {
    // $("[data-toggle='popover']").popover();
    function valiform() {
        // 表单验证
        return $sys_user_edit_form.validate({
            debug: true,
            rules: {
                userName: {
                    required: true
                },
                trueName: {
                    required: true
                },
                password: {
                    required: true
                },
                deptName: {
                    required: true
                },
                dept_ids: {
                    required: true
                }
            },
            messages: {
                userName: {
                    required: "用户名不能为空"
                },
                trueName: {
                    required: "姓名不能为空"
                },
                password: {
                    required: "密码不能为空"
                },
                deptName: {
                    required: "部门不能为空"
                },
                dept_ids: {
                    required: ""
                }
            },
            errorClass: "has-error",
            errorElement: "em",
            success: function (label) {
                label.text(" ") //清空错误提示消息
                    .addClass("success");
            }
        })
    }

    //编辑
    if (editType) {
        // loadForm(editRow, 'sys_user');
        $.ajax({
            url: ctx + '/module/sys/user/detail',
            data: {id: editRow.objId},
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                if (response.code === 200) {
                    $sys_user_edit_form.setForm(response.data);
                    var zTree = $.fn.zTree.getZTreeObj("editFormDeptTree");
                    var node = zTree.getNodeByParam("id", response.data.deptId);
                    zTree.cancelSelectedNode();//先取消所有的选中状态
                    zTree.selectNode(node, true);//将指定ID的节点选中
                    zTree.setting.callback.onClick('','editFormDeptTree',node);//触发ztree点击事件
                    getRoleData();
                    $("select[name='rolesSelect']").multipleSelect('setSelects', response.data.roleIds);
                } else {
                    $MB.n_danger("数据加载失败");
                }
            }
        });

    } else {
        getRoleData();
    }

    $("input[name='status']").change(function () {
    });

    $("#commonModal .paoding_btn_save").click(function () {
        if (!valiform().form()) {
            $("input.has-error").parent('div').addClass('has-error');
            $("textarea.error").prev().css({"color": "red"})
        }

        var name = $(this).attr("name");
        var validator = $sys_user_edit_form.validate();
        var flag = validator.form();
        if (flag) {
            if (!editType) {
                $.post(ctx + "/module/sys/user/add", $sys_user_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("添加成功");
                        $MB.refreshTable("sys_user_table");
                    } else $MB.n_danger(r.message);
                }, 'json');
            } else {
                $.post(ctx + "/module/sys/user/update", $sys_user_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("修改成功");
                        $MB.refreshTable("sys_user_table");
                    } else $MB.n_danger(r.message);
                }, 'json');
            }
        }
    });


});

function initDeptTree() {
    //加载树
    var setting = {
        view: {
            dblClickExpand: false
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
            beforeClick: beforeClick,
            onClick: onClick
        }
    };
    $.fn.zTree.init($("#editFormDeptTree"), setting, deptData);
}

initDeptTree();

function initDeptTreea() {
    var cityObj = $("#dept_name");
    var deptOffset = $("#dept_name").offset();
    $("#deptTreeDiv").css({left: deptOffset.left + "px", top: deptOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    // $("body").bind("mousedown", onBodyDown);
    //传入所需要的id属性名
    // treeDiv("deptTree", "deptTreeDiv", "dept_name", "dept_id", deptData);
    var setting = {
        /*view: {
            dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
            showLine: true,//是否显示节点之间的连线
            fontCss: {'color': 'black', 'font-weight': 'bold'},//字体样式函数
            selectedMulti: true,//设置是否允许同时选中多个节点
            showTitle: true
        },
        check: {
            //chkboxType: { "Y": "ps", "N": "ps" },
            //chkboxType: {"Y": "", "N": ""},
            //chkStyle: "checkbox",//复选框类型
            enable: false //每个节点上是否显示 CheckBox
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
        //回调
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }*/
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };
    $.fn.zTree.init($("#editFormDeptTree"), setting, deptData);


    showTree("deptTreeDiv");


}

/**
 * 部门选择
 *
 */
//节点点击事件
function zTreeOnClick(event, treeId, treeNode) {
    $('#dept_name').val(treeNode.name);
    $('#dept_ids').val(treeNode.id);
    hideTree();
}


//下拉框显示 隐藏
function showTree(id) {

    var $treeDiv = $('#' + id);
    if ($treeDiv.css('display') === 'none') {
        $treeDiv.css('display', 'block');
    } else {
        $treeDiv.css('display', 'none');
    }

    $("body").bind("mousedown", onBodyDownByActionType);

}

function hideTree(id) {
    $('#' + id).css('display', 'none');
    $("body").unbind("mousedown", onBodyDownByActionType);
    return false;
}

//区域外点击事件
function onBodyDownByActionType(event) {
    if (event.target.id.indexOf('treeDemo') === -1) {
        if (event.target.id !== 'selectDevType') {
            hideTree();
        }
    }
}

/*
*
*
* */
function getRoleData() {
    /*$.post(ctx + '/module/sys/role/list', {"id": '-1'}, function (r) {
        roleData = r.data;
        roleSelect(roleData);
    });*/
    var _roledata = {id: '-1'};
    $.ajax({
        url: ctx + '/module/sys/role/list',
        data: JSON.stringify(_roledata),
        dataType: 'json',
        contentType: "application/json",
        async: false,
        method: 'post',
        success: function (response) {
            if (response.code === 200) {
                roleData = response.data;
                roleSelect(response.data.rows);
            } else {//其他异常
                $MB.n_danger(response.message);
            }
        }
    })
    ;
}

/**
 * 角色选择
 */
function roleSelect(d) {
    var data = d;
    var option = "";
    for (var i = 0; i < data.length; i++) {
        option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>"
    }
    $rolesSelect.html("").append(option);
    // $rolesSelect.selectpicker({
    //     'selectedText':'cat',
    //     'noneSelectedText':'请选择',
    //     'deselectAllText':'全不选',
    //     'selectAllText': '全选',
    // });
    var options = {
        selectAllText: '所有角色',
        allSelected: '所有角色',
        width: '100%',
        onClose: function () {
            $roles.val($rolesSelect.val());
            //validator.element("input[name='roles']");
        }
    };
    $rolesSelect.multipleSelect(options);
}


/**
 * ztree官方实例
 **/

function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    //if (!check) alert("只能选择城市...");
    return check;
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("editFormDeptTree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        deptIdArr = [];
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].text + ",";
        deptIdArr.push(nodes[i].id);
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    var deptObj = $("#dept_name");
    var deptId = $("#deptId");
    deptObj.attr("value", v);
    deptId.attr("value", deptIdArr);
    hideMenu();
}

function showDeptTree() {
    var deptObj = $("#dept_name");
    var deptOffset = $("#dept_name").offset();
    $("#deptTreeDiv").css({left: "15px", top: deptOffset.height + deptObj.outerHeight() + "px"}).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
    var zTree = $.fn.zTree.getZTreeObj("editFormDeptTree");
    zTree.expandAll(true);
}

function hideMenu() {
    $("#deptTreeDiv").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "deptTreeDiv" || $(event.target).parents("#deptTreeDiv").length > 0)) {
        hideMenu();
    }
}

