var $pwdEditform = $('#user_pwd_edit_form');
;!function () {
    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        if (name === "update") {
            $.post(ctx + "/module/sys/user/updatePWD", $pwdEditform.serialize(), function (r) {
                if (r.code === 200) {
                    closeModal();
                    $MB.n_success("修改成功");
                    window.location.reload();
                } else {
                    $MB.n_danger(r.message);
                }
            }, 'json');
        }
    });
}();
