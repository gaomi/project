var $sys_role_edit_form = $('#sys_role_edit_form');
var validator;
;!$(function () {
    validateRule();
    //编辑
    if (editType) {
        $.ajax({
            url: ctx + '/module/sys/role/detail',
            data: {id: editRow.objId},
            type: 'POST',
            dataType: 'json',
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
    } else {
        loadMenuTree('editFormMenuTree', '-1');
    }
    $("input[name='status']").change(function () {
    });

    function editFormSubmit(url, type) {
        getMenu(); //获取菜单id
        debugger;
        $.ajax({
            url: url,
            data: $sys_role_edit_form.serialize(),
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                if (response.code === 200) {
                    closeModal();
                    $MB.n_success(type + "成功");
                    $MB.refreshTable("sys_role_table");
                } else $MB.n_danger(response.message);
            }
        });
    }

    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        var validator = $sys_role_edit_form.validate();
        var flag = validator.form();
        if (flag) {
            var url = '';
            if (!editType) {
                url = ctx + "/module/sys/role/add";
                editFormSubmit(url, "添加");
            } else {
                url = ctx + "/module/sys/role/update";
                editFormSubmit(url, "修改");
            }
        }
    });

});


function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $sys_role_edit_form.validate({
        rules: {
            roleName: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "role/checkRoleName",
                    type: "post",
                    dataType: "json",
                    data: {
                        roleName: function () {
                            return $("input[name='roleName']").val().trim();
                        },
                        oldRoleName: function () {
                            return $("input[name='oldRoleName']").val().trim();
                        }
                    }
                }
            },
            remark: {
                maxlength: 50
            },
            menuId: {
                required: true
            }
        },
        messages: {
            roleName: {
                required: icon + "请输入角色名称",
                minlength: icon + "角色名称长度3到10个字符",
                remote: icon + "该角色名已经存在"
            },
            remark: icon + "角色描述不能超过50个字符",
            menuId: icon + "请选择相应菜单权限"
        }
    });
}

/**
 * 选中的菜单值
 */
function getMenu() {
    var treeData = zTree.getCheckedNodes();
    var menuArr = [];
    for (var i = 0; i < treeData.length; i++) {
        menuArr.push(treeData[i].id);
    }
    $("[name='menuId']").val(menuArr);

}


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
    debugger;
    $.ajax({
        url: ctx + '/module/sys/role/getRoleTree',
        data: {"id": d+""},
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            $.fn.zTree.init($("#" + id), setting, response.data);
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
        }
    });


}