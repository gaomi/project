var $job_info_edit_form = $('#job_info_edit_form');
var validator;
;!$(function () {
    // validateRule();
    //编辑
    if (editType) {
        loadForm(editRow, 'job_info');
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'job_info');
    }
    $("input[name='status']").change(function () {
    });

    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        // var validator = $job_info_edit_form.validate();
        // var flag = validator.form();
        // if (flag) {
            var url = '';
            if (!editType) {
                url = ctx + "/module/job/info/add";
                editFormSubmit(url, "添加");
            } else {
                url = ctx + "/module/job/info/update";
                editFormSubmit(url, "修改");
            }
        // }

    });

});

function editFormSubmit(url, type) {
    $.ajax({
        url: url,
        data: $job_info_edit_form.serialize(),
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            if (response.code === 200) {
                closeModal();
                $MB.n_success(type + "成功");
                $MB.refreshTable("job_info_table");
            } else $MB.n_danger(response.message);
        }
    });
}