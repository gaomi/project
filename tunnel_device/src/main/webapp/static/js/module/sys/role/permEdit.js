var $sys_role_edit_form = $('#sys_role_edit_form');
var zTree = '';
;!$(function () {
    //编辑
    if (editType) {
        $.ajax({
            url: ctx + '/module/sys/role/detail',
            data: {id: editRow.objId},
            type: 'POST',
            success: function (response) {
                if (response.code === 200) {
                    $('#sys_role_edit_form').setForm(response.data);
                    //加载树的数据
                    editRow = response.data;
                    loadMenuTree('editFormMenuTree', response.data.id);
                } else {
                    $MB.n_danger("数据加载失败");
                }
            }
        });

    }
    $("input[name='status']").change(function () {
    });

    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        var validator = $sys_role_edit_form.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "/module/sys/role/add", $sys_role_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("添加成功");
                        $MB.refreshTable("sys_role_table");
                    } else $MB.n_danger(r.message);
                }, 'json');
            }
            if (name === "update") {
                getMenu(); //获取菜单id
                $.post(ctx + "/module/sys/role/update", $sys_role_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("修改成功");
                        $MB.refreshTable("sys_role_table");
                    } else $MB.n_danger(r.message);
                }, 'json');
            }
        }
    });

});


function loadMenuTree(id, d) {
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,//是否显示节点之间的连线
            fontCss: {'color': 'black', 'font-weight': 'bold'},
            selectedMulti: true,
            showTitle: true
        },
        check: {
            //chkboxType: { "Y": "ps", "N": "ps" },
            //chkboxType: {"Y": "", "N": ""},
            chkStyle: "checkbox",//复选框类型
            enable: true //每个节点上是否显示 CheckBox
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
            // onRightClick: OnRightClick,
            // beforeRename: beforeRename,
            // onCheck: onCheck
        }
    };
    $.post(ctx + '/module/sys/role/getRoleTree', {"id": d}, function (r) {
        $.fn.zTree.init($("#" + id), setting, r.data);
        zTree = $.fn.zTree.getZTreeObj(id);
        //zTree.cancelSelectedNode();
        //选中已有节点
        // debugger;
        // zTree.checkAllNodes();
        // $.each(d, function (i, row) {
        //     var node = zTree.getNodesByParam("id", row);
        //     zTree.checkNode(node, true, true);
        // });


        zTree.expandAll(true);
    }, 'json');


}

function updateRole() {
    var selected = $("#roleTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的角色！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个角色！');
        return;
    }
    var roleId = selected[0].roleId;
    $.post(ctx + "role/getRole", {"roleId": roleId}, function (r) {
        if (r.code === 0) {
            var $form = $('#role-add');
            var $menuTree = $('#menuTree');
            $form.modal();
            var role = r.msg;
            $("#role-add-modal-title").html('修改角色');
            $form.find("input[name='roleName']").val(role.roleName);
            $form.find("input[name='oldRoleName']").val(role.roleName);
            $form.find("input[name='roleId']").val(role.roleId);
            $form.find("input[name='remark']").val(role.remark);
            var menuArr = [];
            for (var i = 0; i < role.menuIds.length; i++) {
                menuArr.push(role.menuIds[i]);
            }
            $menuTree.jstree('select_node', menuArr, true);
            $menuTree.jstree().close_all();
            $("#role-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    }, 'json');
}

function getMenu() {
    var treeData = zTree.getCheckedNodes();
    var menuArr = [];
    for (var i = 0; i < treeData.length; i++) {
        menuArr.push(treeData[i].id);
    }
    $("[name='menuId']").val(menuArr);

}