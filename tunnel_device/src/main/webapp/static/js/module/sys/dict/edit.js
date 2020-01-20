var $sys_dict_edit_form = $('#sys_dict_edit_form');
var validator;
;!$(function () {
    // validateRule();
    function valiform() {
        // 表单验证
        return $sys_dict_edit_form.validate({
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
        debugger;
        $.ajax({
            url: ctx + '/module/sys/dict/detail',
            data: {id: editRow.objId},
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                debugger;
                if (response.code === 200) {
                    $sys_dict_edit_form.setForm(response.data);
                    var zTree = $.fn.zTree.getZTreeObj("editFormDictTree");
                    var node = zTree.getNodeByParam("id", response.data.parentId);
                    zTree.cancelSelectedNode();//先取消所有的选中状态
                    zTree.selectNode(node, true);//将指定ID的节点选中
                    zTree.setting.callback.onClick('', 'editFormDictTree', node);
                    $("select[name='type']").val(response.data.type);
                    // $("select[name='parentMenu']").multipleSelect('setSelects', response.data.roleIds);
                } else {
                    $MB.n_danger("数据加载失败");
                }
            }
        });

    } else if (editRow.objId === -1) {
        debugger;
        loadForm(editRow, 'sys_dict');
    }
    $("input[name='status']").change(function () {
    });

    $("#commonModal .paoding_btn_save").click(function () {
        debugger;
        if (!valiform().form()) {
            $("input.has-error").parent('div').addClass('has-error');
            $("textarea.error").prev().css({"color": "red"})
        }

        var name = $(this).attr("name");
        var validator = $sys_dict_edit_form.validate();
        var flag = validator.form();
        if (flag) {
            var url = '';
            if (!editType) {
                url = ctx + "/module/sys/dict/add";
                editFormSubmit(url, "添加");
            } else {
                url = ctx + "/module/sys/dict/update";
                editFormSubmit(url, "修改");
            }
        }

    });

});


function editFormSubmit(url, type) {
    debugger;
   // getPidMenu(); //获取菜单id
    // getMenuLevel();
    $.ajax({
        url: url,
        data: $sys_dict_edit_form.serialize(),
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            if (response.code === 200) {
                closeModal();
                $MB.n_success(type + "成功");
                $MB.refreshTable("sys_dict_table");
            } else $MB.n_danger(response.message);
        }
    });
}

/**
 * 选中的菜单值
 */
function getPidMenu() {
    var treeData = zTree.getCheckedNodes();
    var parentId = '';
    for (var i = 0; i < treeData.length; i++) {
        parentId += '.' + treeData[i].id;
    }
    $("[name='parentId']").val(parentId);

}

/**
 * 选中的菜单层级
 */
function getMenuLevel() {
    $("[name='type']").val($("#levelType_search_select").val());

}


function showDictTree() {
    var deptObj = $("#parentMenuTree");
    var deptOffset = $("#parentMenuTree").offset();
    $("#dictTreeDiv").css({left: "15px", top: deptOffset.height + deptObj.outerHeight() + "px"}).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
    var zTree = $.fn.zTree.getZTreeObj("editFormDictTree");

    zTree.expandAll(true);
}

var dictTreeData = '';

//loadMenuTree('editFormDictTree');

function loadMenuTree(id) {
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,//是否显示节点之间的连线
            fontCss: {'color': 'black', 'font-weight': 'bold'},
            selectedMulti: true,
            showTitle: true
        },
        // check: {
        //     //chkboxType: { "Y": "ps", "N": "ps" },
        //     //chkboxType: {"Y": "", "N": ""},
        //     enable: true,//每个节点上是否显示 CheckBox
        //     // chkStyle: "radio",  //单选框
        //     radioType: "all"   //对所有节点设置单选
        // },
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
            onClick: onClick
            // onRightClick: OnRightClick,
            // beforeRename: beforeRename,
            //onCheck: onCheck
        }
    };

    $.ajax({
        url: ctx + '/module/sys/dict/tree',
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            $.fn.zTree.init($("#" + id), setting, response.data);
            zTree = $.fn.zTree.getZTreeObj(id);
        }
    });


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
    var zTree = $.fn.zTree.getZTreeObj("editFormDictTree"),
        nodes = zTree.getSelectedNodes(),
        v = "",
        dictIdArr = [];
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].text + ",";
        dictIdArr.push(nodes[i].id);
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    var menuObj = $("#parentMenuTree");
    var menuId = $("#parentId");
    menuObj.attr("value", v);
    menuId.val(dictIdArr[0]);
    hideMenu();
}

function hideMenu() {
    $("#dictTreeDiv").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "dictTreeDiv" || $(event.target).parents("#dictTreeDiv").length > 0)) {
        hideMenu();
    }
}
