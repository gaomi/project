;!function () {
    var $sys_dept_edit_form = $('#sys_dept_edit_form');

    if (editType) {//编辑
        var nodeByParam = out_deptTree.getNodeByParam("id", editType.pid, null);
        editRow['parentId'] = editType.pid;
        editRow['parentDeptName'] = nodeByParam.text;
        editRow['deptName'] = editType.text;
        loadForm(editRow, 'sys_dept');
    } else {//新增
        debugger;
        //TODO 请求父节点的数据
        // editTreeNode;
        // editParentTreeNode;
        var _seq = -1;
        $.each(editParentTreeNode.children, function (i, r) {
            if (r.id === editTreeNode.id) {
            } else if (r.attributes.seq >= _seq) {
                _seq = r.attributes.seq;
            }
        });
        var formData = {deptName: editTreeNode.text, parentDeptName: editParentTreeNode.text, seq: _seq + 1, parentId: editTreeNode.pid};
        $(formData).serialize();
        $sys_dept_edit_form.setForm(formData);
    }
    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        var validator = $sys_dept_edit_form.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "/module/sys/dept/add", $sys_dept_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("添加成功");
                        $MB.refreshTable("sys_user_table");
                        editTreeNode = "";
                        editParentTreeNode = "";
                    } else $MB.n_danger(r.message);
                }, 'json');
            }
            if (name === "update") {
                $.post(ctx + "/module/sys/dept/update", $sys_dept_edit_form.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $MB.n_success("修改成功");
                        $MB.refreshTable("sys_user_table");
                        editTreeNode = "";
                        editParentTreeNode = "";
                    } else $MB.n_danger(r.message);
                }, 'json');
            }
        }
    });
}();

